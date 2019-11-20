package com.strangersplay.add_event.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.strangersplay.InstanceProvider
import com.strangersplay.add_event.model.NewEventData
import com.strangersplay.categories.model.Category
import kotlinx.android.synthetic.main.fragment_add_event.*
import kotlinx.android.synthetic.main.fragment_single_event.*
import java.util.*
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import java.time.Clock
import kotlinx.android.synthetic.main.fragment_add_event.exitButton as exitButton1


class NewEventFragment : Fragment(), NewEventView {
    private val presenter = InstanceProvider.getNewEventPresenter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(com.strangersplay.R.layout.fragment_add_event, container, false)

    override fun finishAdding() {
        Log.i("qwerty", "exit fragemnt")
        fragmentManager?.popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exitButton.setOnClickListener {
            Log.i("qwerty", "exit fragemnt")
            fragmentManager?.popBackStack()
        }
        createEventButton.setOnClickListener {
            presenter.setNewEventInfo()
            Log.i("qwerty", "create event")
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
            creationTime = "2019-11-20T17:09:00.931Z",
            description = eventDescription.text.toString(),
            eventLocation = "location",
            eventTime = date,
            id = 0,
            image = arrayOf(""),
            level = selectedLevel,
            price = Integer.parseInt(price.text.toString()),
            title = eventTitle.text.toString(),
            userIdsList = arrayOf(0),
            userLimit = Integer.parseInt(userLimit.text.toString())
        )
    }

    override fun setupCategorySpinner(categories: List<Category>) {

        Log.i("qwerty", categories.toString())
        val arrayAdapter = ArrayAdapter(
            this.context!!,
            android.R.layout.simple_spinner_dropdown_item,
            categories.map { it.type }
        )

        categorySpinner.adapter = arrayAdapter
        categorySpinner.onItemSelectedListener = createSpinnerListener()
        Log.i("qwerty", selectedCategory)
    }

    var selectedCategory = ""
    private fun createSpinnerListener() = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            selectedCategory = p0?.getItemAtPosition(p2) as String
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
        Log.i("qwerty", selectedLevel.toString())
    }
    var date="";
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

    val dateSetListener = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(
            view: DatePicker, year: Int, monthOfYear: Int,
            dayOfMonth: Int
        ) {
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val year = (Integer.parseInt(calendar.time.year.toString()) + 1900).toString()
            val month= calendar.time.month.toString()
            val day = calendar.time.date.toString()
            Log.i("qwerty year", year)
            Log.i("qwerty month", month)
            Log.i("qwerty day", day)
            date=date+year+" "+month+" "+day
            eventTimeDay.setText(year+"/ "+month+"/ "+day)
        }
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
            calendar.set(Calendar.HOUR_OF_DAY,hours)
            calendar.set(Calendar.MINUTE,minutes)
            val hours= calendar.time.hours.toString()
            val minutes =calendar.time.minutes.toString()
            Log.i("qwerty hours", hours)
            Log.i("qwerty minutes", minutes)
            date=date+" "+hours+" "+minutes
            eventTimeHours.setText(hours+ ":" +minutes)
        }
    }
//TODO
}
