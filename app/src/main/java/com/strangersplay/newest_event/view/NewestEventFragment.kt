package com.strangersplay.newest_event.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.newest_event.adapters.NewestEventAdapter
import com.strangersplay.newest_event.model.Event
import com.strangersplay.single_event.view.SingleEventFragment
import kotlinx.android.synthetic.main.fragment_newest_event.*

class NewestEventFragment : Fragment(), NewestEventView {

    private val presenter = InstanceProvider.getNewestEventPresenter(this)
    private val newestEventAdapter = NewestEventAdapter{onItemClicked(it)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_newest_event, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    private fun onItemClicked(eventId: Int){
        val singleEventFragment = SingleEventFragment(eventId)
        fragmentManager?.let{
            it.beginTransaction()
                .addToBackStack("worker_item")
                .replace(R.id.newestEventFragment,singleEventFragment)
                .commit()
        }
    }

}
