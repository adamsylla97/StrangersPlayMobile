package com.strangersplay.newest_event.presenter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.util.rangeTo
import com.mapbox.android.core.location.LocationEngine
import com.mapbox.android.core.location.LocationEngineListener
import com.mapbox.android.core.location.LocationEnginePriority
import com.mapbox.android.core.location.LocationEngineProvider
import com.strangersplay.Config
import com.strangersplay.newest_event.model.Event
import com.strangersplay.newest_event.model.NewestEventService
import com.strangersplay.newest_event.view.NewestEventView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.abs
import androidx.core.content.ContextCompat.getSystemService as syst

class NewestEventPresenter(
    private val newestEventService: NewestEventService,
    private val newestEventView: NewestEventView,
    private val locationManager: LocationManager
) : LocationListener {

    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = job + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    private var lat = 0.0
    private var lon = 0.0


    @SuppressLint("MissingPermission")
    fun displayNewestEvents() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0.1f, this)

        ioScope.launch {
            val events = newestEventService.getNewestItems()
            mainScope.launch {
                newestEventView.updateList(events)
            }
        }

    }

    fun filterList(filterOptions: FilterOptions) {
        ioScope.launch {
            val events = newestEventService.getNewestItems()
            mainScope.launch {
                when (filterOptions) {
                    FilterOptions.YOURS -> {
                        val tempEvents = mutableListOf<Event>()
                        events.forEach {
                            if (it.userIdsList.isNotEmpty()) {
                                for (x in it.userIdsList.size-1 downTo 0 step 1) {
                                    if (
                                        it.userIdsList[x].userId == Config.userToken
                                    ) tempEvents.add(it)
                                }
                            }
                        }
                        newestEventView.updateList(tempEvents)
                        Log.i("qwerty123", tempEvents.toString())
                    }
                    FilterOptions.NEARLEST -> {
                        var locationA = Location("A")
                        locationA.latitude = lat
                        locationA.longitude = lon
                        newestEventView.updateList(events.sortedBy {
                            val splittedString = it.eventLocation.split(",")
                            var locationB = Location("B")

                            locationB.latitude = splittedString[0].toDouble()
                            locationB.longitude = splittedString[1].toDouble()

                            locationA.distanceTo(locationB)
                        })
                    }
                    FilterOptions.MOREPEOPLE -> {
                        newestEventView.updateList(events.sortedByDescending {
                            it.userLimit - it.userIdsList.size
                        })
                    }
                    FilterOptions.LESSPEOPLE -> {
                        newestEventView.updateList(events.sortedBy {
                            it.userLimit - it.userIdsList.size
                        })
                    }
                    FilterOptions.HIGHERPRICE -> {
                        newestEventView.updateList(events.sortedByDescending {
                            it.price
                        })
                    }
                    FilterOptions.LOWERPRICE -> {
                        newestEventView.updateList(events.sortedBy {
                            it.price
                        })
                    }
                    FilterOptions.HIGHERLEVEL -> {
                        newestEventView.updateList(events.sortedByDescending {
                            it.level
                        })
                    }
                    FilterOptions.LOWERLEVEL -> {
                        newestEventView.updateList(events.sortedBy {
                            it.level
                        })
                    }
                }
            }
        }

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onLocationChanged(location: Location?) {
        if (location != null) {
            lat = location.latitude
            lon = location.longitude
            Log.d("localization", "$lat $lon")
        }
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }
}