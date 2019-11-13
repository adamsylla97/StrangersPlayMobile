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
import com.strangersplay.R
import com.strangersplay.add_event.model.NewEventData
import com.strangersplay.add_event.presenter.NewEventPresenter
import com.strangersplay.categories.model.Category
import kotlinx.android.synthetic.main.fragment_add_event.*
import kotlinx.android.synthetic.main.fragment_newest_event.*
import kotlinx.android.synthetic.main.fragment_single_event.*
import java.util.*
import kotlinx.android.synthetic.main.fragment_add_event.exitButton as exitButton1

class NewEventFragment : Fragment(), NewEventView {


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
            eventLocation = "aaa",
            eventTime = "123",
            id = 0,
            level=selectedLevel,
            price = 5,
            title = eventTitle.text.toString(),
            username ="janusz"

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
        Log.i("qwerty",selectedCategory)
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

}
