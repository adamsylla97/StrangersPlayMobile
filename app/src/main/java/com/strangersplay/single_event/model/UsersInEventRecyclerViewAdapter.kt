package com.strangersplay.single_event.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.strangersplay.R
import kotlinx.android.synthetic.main.user_in_event_item.view.*

class UsersInEventRecyclerViewAdapter(private val listener: (String) -> Unit) : RecyclerView.Adapter<UsersInEventRecyclerViewAdapter.UsersInEventHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersInEventHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_in_event_item, parent, false)
        return UsersInEventHolder(inflatedView, listener)
    }

    val usersInEvent = mutableListOf<String>()

    fun addList(listToAdd: List<String>) {
        if (usersInEvent.isNotEmpty())
            usersInEvent.clear()
        usersInEvent.addAll(listToAdd)
    }

    override fun getItemCount(): Int {
        return usersInEvent.size
    }

    override fun onBindViewHolder(holder: UsersInEventHolder, position: Int) {
        val item = usersInEvent[position]
        holder.bind(item)
    }

    class UsersInEventHolder(val view: View, private val itemClick: (String) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun bind(user: String) {
            view.userInEventUsername.text = user
            view.setOnClickListener {

            }
        }
    }

}