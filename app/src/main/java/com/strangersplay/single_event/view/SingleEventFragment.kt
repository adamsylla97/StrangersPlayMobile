package com.strangersplay.single_event.view

import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mapbox.android.core.location.LocationEngine
import com.mapbox.android.core.location.LocationEngineListener
import com.mapbox.android.core.location.LocationEnginePriority
import com.mapbox.android.core.location.LocationEngineProvider
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode
import com.strangersplay.InstanceProvider

import com.strangersplay.R
import com.strangersplay.single_event.adapter.UsersInEventRecyclerViewAdapter
import com.strangersplay.single_event.model.SingleEvent
import com.strangersplay.single_event.model.UserIds
import com.strangersplay.user_profile.display.view.UserProfileFragment
import kotlinx.android.synthetic.main.fragment_add_event.view.*
import kotlinx.android.synthetic.main.fragment_single_event.*

class SingleEventFragment(private val eventId: Int) : Fragment(), SingleEventView, LocationEngineListener {

    private lateinit var mapView: MapView
    private lateinit var map: MapboxMap
    private lateinit var originLocation: Location

    private var locationEngine: LocationEngine? = null
    private var locationLayerPlugin: LocationLayerPlugin? = null

    private val singleEventPresenter = InstanceProvider.getSingleEventPresenter(this)

    private val usersAdapter = UsersInEventRecyclerViewAdapter {onItemClicked(it)}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Mapbox.getInstance(activity?.applicationContext!!, getString(R.string.access_token))
        return inflater.inflate(R.layout.fragment_single_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = view?.findViewById(R.id.singleEventMapView)!!
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapboxMap ->
            map = mapboxMap
            enableLocation()
            singleEventPresenter.displaySingleEvent()
        }

        usersInEvent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usersAdapter
        }

        joinButton.setOnClickListener {
            singleEventPresenter.joinToEvent()
            fragmentManager?.popBackStack()
        }

        exitButton.setOnClickListener {
            singleEventPresenter.leaveEvent()
            fragmentManager?.popBackStack()
        }


    }

    override fun getEventId(): Int {
        return eventId
    }

    override fun updateList(users: List<UserIds>) {
        usersAdapter.addList(users)
        usersAdapter.notifyDataSetChanged()
    }

    override fun displayEvent(event: SingleEvent) {
        eventTitleTextView.text = event.title
        Glide.with(this).load("").placeholder(R.drawable.ic_cloud_queue_black_24dp).into(eventImage)
        eventRatingBar.numStars = event.level
        eventContributionTextView.text = event.price.toString()
        if(event.eventLocation != "null,null") {
            val position = event.eventLocation.split(",")
            if (position.size == 2) {
                map.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            position[0].toDouble(),
                            position[1].toDouble()
                        )
                    )
                )
            }
        }
    }

    private fun enableLocation(){
        initializeLocationEngine()
        initializeLocationLayer()
    }

    private fun onItemClicked(userId: Int){
        val userProfileFragment = UserProfileFragment.newInstance(userId)
        fragmentManager?.let{
            fragmentManager?.beginTransaction()?.add(R.id.singleEventFragment, userProfileFragment)?.addToBackStack("profile")
                ?.commit()
        }
    }

    private fun initializeLocationEngine(){
        locationEngine = LocationEngineProvider(activity?.applicationContext).obtainBestLocationEngineAvailable()
        locationEngine?.priority = LocationEnginePriority.HIGH_ACCURACY
        locationEngine?.activate()

        val lastLocation = locationEngine?.lastLocation
        if(lastLocation != null){
            originLocation = lastLocation
        }else{
            locationEngine?.addLocationEngineListener(this)
        }
    }

    private fun initializeLocationLayer(){
        locationLayerPlugin = LocationLayerPlugin(mapView, map, locationEngine)
        locationLayerPlugin?.setLocationLayerEnabled(true)
        locationLayerPlugin?.cameraMode = CameraMode.TRACKING
        locationLayerPlugin?.renderMode = RenderMode.NORMAL
    }

    private fun setCameraPosition(location: Location){
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 13.0))
    }

    override fun displayToast(msg: String) {
        Toast.makeText(this.context, msg, Toast.LENGTH_LONG).show()
    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            originLocation = location
            setCameraPosition(location)
        }
    }

    override fun onConnected() {
        locationEngine?.requestLocationUpdates()
    }

    override fun onStart() {
        super.onStart()
        if(PermissionsManager.areLocationPermissionsGranted(activity?.applicationContext)){
            locationEngine?.requestLocationUpdates()
            locationLayerPlugin?.onStart()
        }
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        locationEngine?.removeLocationUpdates()
        locationLayerPlugin?.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        singleEventPresenter.finishThreads()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        locationEngine?.deactivate()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(outState != null) {
            mapView.onSaveInstanceState(outState)
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
