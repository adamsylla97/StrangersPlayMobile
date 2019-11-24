package com.strangersplay.joined_events.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.strangersplay.R
import com.strangersplay.newest_event.model.Event

class JoinedEventFragment : Fragment(), JoinedEventView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joined_event, container, false)
    }

    override fun updateList(event: Event) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
