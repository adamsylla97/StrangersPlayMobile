package com.strangersplay.single_event.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.strangersplay.InstanceProvider

import com.strangersplay.R
import com.strangersplay.single_event.model.SingleEvent
import com.strangersplay.single_event.model.UsersInEventRecyclerViewAdapter
import kotlinx.android.synthetic.main.event_item.view.*
import kotlinx.android.synthetic.main.fragment_single_event.*

class SingleEventFragment(private val eventId: Int) : Fragment(), SingleEventView {

    private val singleEventPresenter = InstanceProvider.getSingleEventPresenter(this)
    private val usersAdapter = UsersInEventRecyclerViewAdapter{}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        singleEventPresenter.displaySingleEvent()

        usersInEvent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usersAdapter
        }

    }

    override fun getEventId(): Int {
        return eventId
    }

    override fun updateList(users: List<String>) {
        usersAdapter.addList(users)
        usersAdapter.notifyDataSetChanged()
    }

    override fun displayEvent(event: SingleEvent) {
        eventTitleTextView.text = event.title
        Glide.with(this).load("").placeholder(R.drawable.ic_cloud_queue_black_24dp).into(eventImage)
        eventRatingBar.numStars = event.level
        eventContributionTextView.text = event.price.toString()
        localizationTextView.text = event.eventLocation
    }
}
