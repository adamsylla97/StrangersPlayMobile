package com.strangersplay.single_event.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.strangersplay.InstanceProvider

import com.strangersplay.R
import com.strangersplay.single_event.model.SingleEvent
import kotlinx.android.synthetic.main.fragment_single_event.*

class SingleEventFragment : Fragment(), SingleEventView {

    private val singleEventPresenter = InstanceProvider.getSingleEventPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        singleEventPresenter.displaySingleEvent()
    }

    override fun displayEvent(event: SingleEvent) {
        eventTitleTextView.text = event.eventTitle
        Glide.with(this).load(event.eventImage).into(eventImage)
        eventRatingBar.numStars = event.eventLevel
        eventContributionTextView.text = event.eventContribution.toString()
        localizationTextView.text = event.eventLocalization
    }
}
