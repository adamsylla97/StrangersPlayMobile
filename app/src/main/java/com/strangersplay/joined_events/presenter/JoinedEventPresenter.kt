package com.strangersplay.joined_events.presenter

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.strangersplay.joined_events.view.JoinedEventView
import com.strangersplay.newest_event.model.NewestEventService
import com.strangersplay.newest_event.presenter.FilterOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class JoinedEventPresenter(private val newestEventService: NewestEventService,
                           private val newestEventView: JoinedEventView,
                           private val locationManager: LocationManager
): LocationListener {

    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = job + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    private var lat = 0.0
    private var lon = 0.0


    @SuppressLint("MissingPermission")
    fun displayNewestEvents() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0.1f,this)

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
                        newestEventView.updateList(events)
                    }
                    FilterOptions.LESSPEOPLE -> {
                        newestEventView.updateList(events)
                    }
                    FilterOptions.HIGHERPRICE -> {
                        newestEventView.updateList(events.sortedBy {
                            it.price
                        })
                    }
                    FilterOptions.LOWERPRICE -> {
                        newestEventView.updateList(events.sortedByDescending {
                            it.price
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