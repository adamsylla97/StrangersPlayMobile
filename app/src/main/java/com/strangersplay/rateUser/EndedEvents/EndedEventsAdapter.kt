package com.strangersplay.rateUser.EndedEvents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.strangersplay.R
import com.strangersplay.add_event.model.UserIds
import com.strangersplay.newest_event.model.Event
import kotlinx.android.synthetic.main.event_item.view.*

class EndedEventsAdapter(private val listener: (List<UserIds>) -> Unit): RecyclerView.Adapter<EndedEventsAdapter.EventsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventsHolder(inflatedView, listener)
    }

    val eventsList = mutableListOf<Event>()

    fun addList(listToAdd: List<Event>){
        if(eventsList.isNotEmpty())
            eventsList.clear()
        eventsList.addAll(listToAdd)
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) {
        val item = eventsList[position]
        holder.bind(item)
    }

    class EventsHolder(private val view: View, private val itemClick: (eventId: List<UserIds>) -> Unit):
        RecyclerView.ViewHolder(view){
        fun bind(event: Event){
            view.eventTitle.text = event.title
            view.eventPrice.text = event.price.toString() + " zł"
            view.eventTime.text = event.eventTime
            view.eventRating.text = event.level.toString()+"/5"

            Glide.with(view).load("").placeholder(R.drawable.ic_cloud_queue_black_24dp).into(view.eventImage)

            view.setOnClickListener {
                itemClick(event.userIdsList)
            }
        }

    }

}