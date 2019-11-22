package com.strangersplay.add_event.view

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mapbox.android.core.location.LocationEngine
import com.mapbox.android.core.location.LocationEngineListener
import com.mapbox.android.core.location.LocationEnginePriority
import com.mapbox.android.core.location.LocationEngineProvider
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
import com.strangersplay.categories.model.Category
import kotlinx.android.synthetic.main.fragment_add_event.*
import kotlinx.android.synthetic.main.fragment_single_event.*
import java.util.*
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.*
import androidx.navigation.Navigation
import com.strangersplay.add_event.model.NewEventData
import com.strangersplay.add_event.model.UserIds
import com.strangersplay.newest_event.view.NewestEventFragment

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
    ): View? {
        Mapbox.getInstance(activity?.applicationContext!!, getString(R.string.access_token))
        return inflater.inflate(R.layout.fragment_add_event, container, false)
    }

    override fun finishAdding() {
            fragmentManager?.popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exitAddButton.setOnClickListener{
            Log.i("qwerty","exit fragemnt")
            fragmentManager?.beginTransaction()?.replace(R.id.newestEventFragment,NewestEventFragment())?.commit()
        }
        mapView = view?.findViewById(R.id.mapView)!!
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapboxMap ->
            map = mapboxMap
            map.addOnMapClickListener(this)
            enableLocation()
        }

        createEventButton.setOnClickListener {
            if (eventTitle.text.isNullOrEmpty() || price.text.isNullOrEmpty() ||
                eventDescription.text.isNullOrEmpty()||mapMarker?.position?.latitude.toString().isNullOrEmpty()||mapMarker?.position?.longitude.toString().isNullOrEmpty()
                || years.isNullOrEmpty()||month.isNullOrEmpty()||minute.isNullOrEmpty()||hour.isNullOrEmpty()||day.isNullOrEmpty()|| userLimit.text.isNullOrEmpty()) {
                Toast.makeText(newEventFragment.context,"Please fill all fields correctly.",Toast.LENGTH_SHORT).show()
            }else{
                presenter.setNewEventInfo()
                fragmentManager?.beginTransaction()?.replace(R.id.newestEventFragment,NewestEventFragment())?.commit()
                Log.i("qwerty", "create event")}
        }
        eventTimeDay.setOnClickListener {
            Log.i("qwerty", "set day")
            presenter.setupCalender()

        }
        eventTimeHours.setOnClickListener {
            Log.i("qwerty", "set hours")
            presenter.setupClock()
        }
        presenter.setupSpinner()
        levelSpinner()
    }


    override fun getEventData(): NewEventData {
        return NewEventData(
            authorId = 0,
            category = selectedCategory,
            creationTime= "2019-11-20T17:09:00.931Z",
            description =eventDescription.text.toString(),
            eventLocation = mapMarker?.position?.latitude.toString() + "," + mapMarker?.position?.longitude.toString(),
            eventTime = "$years-$month-$day  $hour:$minute" ,
            id = 0,
            image = listOf(""),
            level = selectedLevel,
            price = Integer.parseInt(price.text.toString()),
            title = eventTitle.text.toString(),
            userIdsList = listOf(UserIds(0,0,"")),
            userLimit = Integer.parseInt(userLimit.text.toString())
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

    var selectedCategory = ""
    private fun createSpinnerListener() = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            selectedCategory = p0?.getItemAtPosition(p2) as String
            selectedCategory=p0?.getItemAtPosition(p2) as String
        }
    }

    var selectedLevel = 0
    private fun createSpinnerListener2() = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            selectedLevel = p0?.getItemAtPosition(p2) as Int
        }
    }

    fun levelSpinner() {
        val arrayAdapter2 = ArrayAdapter(
            this.context!!,
            android.R.layout.simple_spinner_dropdown_item,
            (1..5).map { it }
        )
        levelSpinner.adapter = arrayAdapter2
        levelSpinner.onItemSelectedListener = createSpinnerListener2()
    }

    var day=""
    var month=""
    var years=""
    var minute=""
    var hour=""

    var calendar = Calendar.getInstance()
    override fun setupCalender() {
        DatePickerDialog(
            this.context!!,
            dateSetListener,
            // set DatePickerDialog to point to today's date when it loads up
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
    override fun setupClock() {
        TimePickerDialog(
            this.context!!,
            timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    val timeSetListener = object : TimePickerDialog.OnTimeSetListener {
        override fun onTimeSet(
            view: TimePicker, hours: Int, minutes: Int) {
            calendar.set(Calendar.HOUR_OF_DAY,hours).toString()
            calendar.set(Calendar.MINUTE,minutes).toString()
            hour= calendar.time.hours.toString()
            minute = calendar.time.minutes.toString()
            eventTimeHours.setText(hour+":" +minute)
        }
    }
    val dateSetListener = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(
            view: DatePicker, year: Int, monthOfYear: Int,
            dayOfMonth: Int
        ) {
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            years = (Integer.parseInt(calendar.time.year.toString()) + 1900).toString()
            month= calendar.time.month.toString()
            day = calendar.time.date.toString()
            eventTimeDay.setText(years+"/"+month+"/ "+day)
        }
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


