package com.strangersplay.rateUser.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.strangersplay.R
import com.strangersplay.add_event.model.UserIds
import com.strangersplay.rateUser.model.Rate
import kotlinx.android.synthetic.main.event_item.view.*
import kotlinx.android.synthetic.main.rate_item.view.*

class AddRateAdapter(private val listener: (Pair<Rate, Int>) -> Unit) :
    RecyclerView.Adapter<AddRateAdapter.RateHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.rate_item, parent, false)
        return RateHolder(
            inflatedView,
            listener
        )
    }


    override fun onBindViewHolder(holder: RateHolder, position: Int) {
        val item = rateList[position]
        holder.bind(item)
    }

    val rateList = mutableListOf<UserIds>()

    fun addList(listToAdd: List<UserIds>) {
        if (rateList.isNotEmpty())
            rateList.clear()
        rateList.addAll(listToAdd)
    }

    override fun getItemCount(): Int {
        return rateList.size
    }

    class RateHolder(private val view: View, private val itemClick: (Pair<Rate, Int>) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun bind(rate: UserIds) {
            view.playerName.text = rate.username

            view.sendRating.setOnClickListener {
                val newRate = Rate(
                    authorUsername = view.playerName.text.toString(),
                    description = view.rateDesc.text.toString(),
                    rate = view.ratingPlayer.numStars
                )
                Log.i("supertest123","kliklem")
                itemClick(Pair<Rate, Int>(newRate, rate.userId))
            }

        }

    }

}