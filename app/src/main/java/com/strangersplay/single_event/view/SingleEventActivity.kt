package com.strangersplay.single_event.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.single_event.model.SingleEvent
import kotlinx.android.synthetic.main.activity_single_event.*

class SingleEventActivity : AppCompatActivity(), SingleEventView {

    private val singleEventPresenter = InstanceProvider.getSingleEventPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_event)

        singleEventPresenter.displaySingleEvent()
    }

    override fun displayEvent(event: SingleEvent) {
        eventTitleTextView.text = event.eventTitle
        Glide.with(this).load(event.eventImage).into(eventImage)
        eventRatingBar.numStars = event.eventLevel
        eventContributionTextView.text = event.eventContribution.toString()
    }
}
