package com.strangersplay.single_event.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.strangersplay.R
import com.strangersplay.single_event.model.UserIds
import kotlinx.android.synthetic.main.user_in_event_item.view.*

class UsersInEventRecyclerViewAdapter(private val listener: (Int) -> Unit) : RecyclerView.Adapter<UsersInEventRecyclerViewAdapter.UsersInEventHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersInEventHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_in_event_item, parent, false)
        return UsersInEventHolder(
            inflatedView,
            listener
        )
    }

    val usersInEvent = mutableListOf<UserIds>()

    fun addList(listToAdd: List<UserIds>) {
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

    class UsersInEventHolder(val view: View, private val itemClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun bind(user: UserIds) {
            view.userInEventUsername.text = user.username
            view.setOnClickListener {
                itemClick(user.userId)
            }
        }
    }

}