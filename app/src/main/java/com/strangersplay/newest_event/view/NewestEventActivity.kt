package com.strangersplay.newest_event.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.newest_event.adapters.NewestEventAdapter
import com.strangersplay.newest_event.model.Event
import com.strangersplay.newest_event.presenter.NewestEventPresenter
import kotlinx.android.synthetic.main.activity_newest_event.*

class NewestEventActivity : AppCompatActivity(), NewestEventView {

    private val presenter = InstanceProvider.getNewestEventPresenter(this)
    private val newestEventAdapter = NewestEventAdapter{}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newest_event)

        newestEventsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newestEventAdapter
        }

        presenter.displayNewestEvents()

    }

    override fun updateList(events: List<Event>) {
        newestEventAdapter.addList(events)
        newestEventAdapter.notifyDataSetChanged()
    }

}
