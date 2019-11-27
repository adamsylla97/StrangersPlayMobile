package com.strangersplay.rateUser.EndedEvents

import android.annotation.SuppressLint
import android.location.LocationManager
import android.util.Log
import com.strangersplay.Config
import com.strangersplay.newest_event.model.Event
import com.strangersplay.newest_event.model.NewestEventService
import com.strangersplay.newest_event.view.NewestEventView
import kotlinx.android.synthetic.main.fragment_add_event.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*





class EndedEventsPresenter(
    private val newestEventService: NewestEventService,
    private val newestEventView: NewestEventView,
    private val locationManager: LocationManager
) {

    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = job + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    private var lat = 0.0
    private var lon = 0.0


    @SuppressLint("MissingPermission")
    fun displayNewestEvents() {
        ioScope.launch {
            val events = newestEventService.getNewestItems()
            mainScope.launch {
                val tempEventsList = mutableListOf<Event>()
                events.forEach {
                    if (it.userIdsList.isNotEmpty()) {
                        for (x in it.userIdsList.size - 1 downTo 0 step 1) {
                            if (
                                it.userIdsList[x].userId == Config.userToken
                            )
                                if(isDatePassed(it.eventTime))tempEventsList.add(it)
                        }
                    }
                }
                tempEventsList.sortedBy { it.id }.filter { isDatePassed(it.eventTime) }
                newestEventView.updateList(tempEventsList)
            }
        }

    }


    fun isDatePassed(eventTime: String): Boolean {
        var list = eventTime.split("-", " ", ":")
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val date= df.format(Calendar.getInstance().time)

        var list2=date.split("-"," ",";")
        Integer.parseInt(list[0])
        if(Integer.parseInt(list[0])-Integer.parseInt(list2[0])<0)return true
        if(Integer.parseInt(list[1])-Integer.parseInt(list2[1])<0)return true
        if(Integer.parseInt(list[2])-Integer.parseInt(list2[2])<0)return true
        return false
    }
}