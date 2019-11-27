package com.strangersplay.rateUser.EndedEvents

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.newest_event.adapters.NewestEventAdapter
import com.strangersplay.newest_event.model.Event
import com.strangersplay.newest_event.view.NewestEventView
import com.strangersplay.rateUser.view.AddRateFragment
import kotlinx.android.synthetic.main.fragment_newest_event.*


class EndedEventsFragment : Fragment(), NewestEventView {

    private lateinit var presenter: EndedEventsPresenter
    private val newestEventAdapter = NewestEventAdapter{onItemClicked(it)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_newest_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = InstanceProvider.getEndedEventPresenter(this, context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager)
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
        val addRateFragement=AddRateFragment(eventId)
        fragmentManager?.let{
            fragmentManager?.beginTransaction()?.add(R.id.newestEventFragment, addRateFragement)?.addToBackStack("newestEvent")
                ?.commit()
        }
    }
}
