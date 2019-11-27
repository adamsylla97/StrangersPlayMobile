package com.strangersplay.rateUser.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.strangersplay.R
import com.strangersplay.rateUser.model.Rate
import kotlinx.android.synthetic.main.event_item.view.*

class AddRateAdapter(private val listener: (Int) -> Unit) :
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

    val rateList = mutableListOf<Rate>()

    fun addList(listToAdd: List<Rate>) {
        if (rateList.isNotEmpty())
            rateList.clear()
        rateList.addAll(listToAdd)
    }

    override fun getItemCount(): Int {
        return rateList.size
    }

    class RateHolder(private val view: View, private val itemClick: (rateId: Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun bind(rate: Rate) {
            view.setOnClickListener {

            }
        }

    }

}