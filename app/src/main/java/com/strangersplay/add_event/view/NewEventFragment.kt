package com.strangersplay.add_event.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.add_event.model.NewEventData
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
    override fun finishAdding() { TODO()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exitButton.setOnClickListener{
            Log.i("qwerty","exit fragemnt")
            fragmentManager?.popBackStack()
        }
        presenter.setNewEventInfo()
    }


    override fun getEventData(): NewEventData {
        return NewEventData(
            category="bla",
            creationTime= Calendar.getInstance().time.toString(),
            description =eventDescription.text.toString(),
            eventLocation = "",
            eventTime = eventTime.text.toString(),
            id = 0,
            level=0,
            price = 0,
            title = eventTitle.text.toString(),
            username =""

        )
    }

}