package com.strangersplay.add_event.view

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.mapbox.android.core.location.LocationEngine
import com.mapbox.android.core.location.LocationEngineListener
import com.mapbox.android.core.location.LocationEnginePriority
import com.mapbox.android.core.location.LocationEngineProvider
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.Marker
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
import com.strangersplay.add_event.model.NewEventData
import com.strangersplay.categories.model.Category
import kotlinx.android.synthetic.main.fragment_add_event.*

class NewEventFragment : Fragment(), NewEventView, LocationEngineListener, MapboxMap.OnMapClickListener {

    private lateinit var mapView: MapView
    private lateinit var map: MapboxMap
    private lateinit var originLocation: Location

    private var locationEngine: LocationEngine? = null
    private var locationLayerPlugin: LocationLayerPlugin? = null
    private var mapMarker: Marker? = null


    private val presenter=InstanceProvider.getNewEventPresenter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_event, container, false)
    override fun finishAdding() {
            fragmentManager?.popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exitButton.setOnClickListener{
            fragmentManager?.popBackStack()
        }

        Mapbox.getInstance(activity?.applicationContext!!, getString(R.string.access_token))
        mapView = view?.findViewById(R.id.mapView)!!
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapboxMap ->
            map = mapboxMap
            map.addOnMapClickListener(this)
            enableLocation()
        }

        createEventButton.setOnClickListener{
            presenter.setNewEventInfo()
        }
        presenter.setupSpinner()
        levelSpinner()
    }


    override fun getEventData(): NewEventData {
        return NewEventData(
            category=selectedCategory,
            creationTime= "123",
            description =eventDescription.text.toString(),
            eventLocation = mapMarker?.position?.latitude.toString() + "," + mapMarker?.position?.longitude.toString(),
            eventTime = "123",
            id = 0,
            level=selectedLevel,
            price = 5,
            title = eventTitle.text.toString(),
            username ="janusz"

        )
    }

    override fun setupCategorySpinner(categories: List<Category>) {

        val arrayAdapter = ArrayAdapter(
            this.context!!,
            android.R.layout.simple_spinner_dropdown_item,
            categories.map { it.type }
        )

        categorySpinner.adapter = arrayAdapter
        categorySpinner.onItemSelectedListener = createSpinnerListener()
    }

    var selectedCategory=""
    private fun createSpinnerListener()=object: AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            selectedCategory=p0?.getItemAtPosition(p2) as String
        }
    }
    var selectedLevel=0
    private fun createSpinnerListener2()=object: AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            selectedLevel=p0?.getItemAtPosition(p2) as Int
        }
    }

    fun levelSpinner(){
        val arrayAdapter2 = ArrayAdapter(
            this.context!!,
            android.R.layout.simple_spinner_dropdown_item,
            (1..5).map { it }
        )
        levelSpinner.adapter=arrayAdapter2
        levelSpinner.onItemSelectedListener=createSpinnerListener2()
        Log.i("qwerty",selectedLevel.toString())
    }

    private fun enableLocation(){
        initializeLocationEngine()
        initializeLocationLayer()
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

    override fun onMapClick(point: LatLng) {
        mapMarker?.let {
            map.removeMarker(it)
        }

        mapMarker = map.addMarker(MarkerOptions().position(point))

        Log.d("MapView", "onClick: " + point.latitude.toString() + "," + point.longitude.toString())
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

