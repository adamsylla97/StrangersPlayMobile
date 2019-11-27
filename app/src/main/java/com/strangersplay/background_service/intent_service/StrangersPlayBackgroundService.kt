package com.strangersplay.background_service.intent_service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.room.Room
import com.strangersplay.Config
import com.strangersplay.R
import com.strangersplay.RestServiceBuilder
import com.strangersplay.background_service.database.AppDatabase
import com.strangersplay.background_service.database.UserEventsEntity
import com.strangersplay.newest_event.model.Event
import com.strangersplay.newest_event.model.NewestEventDataProvider
import com.strangersplay.newest_event.model.NewestEventService
import com.strangersplay.newest_event.model.UserIds
import com.strangersplay.newest_event.network.RestNewestEventService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class StrangersPlayBackgroundService: Service() {

    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val ioScope2 = CoroutineScope(ioContext)

    var start = System.currentTimeMillis()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        onTaskRemoved(intent)
        if(System.currentTimeMillis() - start >= Config.strangersPlayBackgroundServiceDelay) {
            Thread {
                val restService = prepereNetwork()
                ioScope.launch {
                    val events = restService.getNewestItems()
                    ioScope2.launch {
                        val eventsList = events.filter{ it.authorId == Config.userToken ||
                                it.userIdsList.contains(UserIds(Config.userToken))    }
                        val message = preperMessageForNotification(eventsList)
                        showNotification(message)
                    }
                }
            }.start()
            start = System.currentTimeMillis()
        }

        return START_STICKY
    }

    private fun preperMessageForNotification(events: List<Event>): String {
        val db = Room.databaseBuilder(applicationContext,
            AppDatabase::class.java, "StrangersPlayDatabase")
            .build()

        val stringBuilder = StringBuilder()

        for(event in events){
            val eventFromDatabase = db.userEventsDao().findEventById(event.id)
            if(eventFromDatabase != null){
                if(eventFromDatabase.userCount < event.userIdsList.size){
                    stringBuilder.append("${event.title}: someone joined to event\n")
                }else if(eventFromDatabase.userCount > event.userIdsList.size){
                    stringBuilder.append("${event.title}: someone left event\n")
                }
                else{
                    stringBuilder.append("${event.title}: No change\n")
                }
            }
        }

        db.userEventsDao().deleteAll()

        for(event in events){
            db.userEventsDao().insert(UserEventsEntity(event.id, event.title, event.userIdsList.size))
        }

        return stringBuilder.toString()
    }

    private fun prepereNetwork(): NewestEventService {
        val rest = RestServiceBuilder.build(RestNewestEventService::class.java)
        val dataProvider = NewestEventDataProvider(rest)
        return NewestEventService(dataProvider)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showNotification(message: String){
        if(message != "") {
            val name = getString(R.string.channel_name)
            val descriptionText = "StrangersPlayService"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel("StrengersPlayServiceChannel", name, importance).apply {
                    description = descriptionText
                }

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            val notification =
                NotificationCompat.Builder(applicationContext, "StrengersPlayServiceChannel")
                    .setContentTitle("StrangersPlay info update")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setStyle(NotificationCompat.BigTextStyle()
                        .bigText(message))
                    .build()

            notificationManager.notify(1, notification)
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        var restartServiceIntent = Intent(applicationContext, this::class.java)
        restartServiceIntent.setPackage(packageName)
        startService(restartServiceIntent)
        super.onTaskRemoved(rootIntent)
    }
}