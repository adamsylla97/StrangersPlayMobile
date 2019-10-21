package com.strangersplay.newest_event.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.strangersplay.R
import com.strangersplay.newest_event.model.Event
import kotlinx.android.synthetic.main.event_item.view.*

class NewestEventAdapter(private val listener: (Event) -> Unit): RecyclerView.Adapter<NewestEventAdapter.EventsHolder>() {
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

    class EventsHolder(private val view: View, private val itemClick: (Event) -> Unit):RecyclerView.ViewHolder(view){
        fun bind(event: Event){
            view.eventName.text = event.name
        }

    }

}