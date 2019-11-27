package com.strangersplay.joined_events.view


import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.strangersplay.Config
import com.strangersplay.InstanceProvider

import com.strangersplay.R
import com.strangersplay.joined_events.presenter.JoinedEventPresenter
import com.strangersplay.newest_event.adapters.NewestEventAdapter
import com.strangersplay.newest_event.model.Event
import com.strangersplay.newest_event.model.UserIds
import com.strangersplay.newest_event.presenter.FilterOptions
import com.strangersplay.single_event.view.SingleEventFragment
import kotlinx.android.synthetic.main.fragment_joined_event.*

class JoinedEventFragment : Fragment(), JoinedEventView {

    private lateinit var presenter: JoinedEventPresenter
    private val newestEventAdapter = NewestEventAdapter{onItemClicked(it)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_joined_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = InstanceProvider.getJoinedEventPresenter(this, context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager)
        joinedEventRecycleView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newestEventAdapter
        }
        presenter.displayNewestEvents()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.filter_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nearlest->presenter.filterList(FilterOptions.NEARLEST)
            R.id.morePeople->presenter.filterList(FilterOptions.MOREPEOPLE)
            R.id.lessPeople->presenter.filterList(FilterOptions.LESSPEOPLE)
            R.id.higherPrice->presenter.filterList(FilterOptions.HIGHERPRICE)
            R.id.lowerPrice->presenter.filterList(FilterOptions.LOWERPRICE)

        }
        return true
    }

    override fun updateList(events: List<Event>) {
        val eventsList = events.filter{ it.authorId == Config.userToken ||
                it.userIdsList.contains(com.strangersplay.add_event.model.UserIds(Config.userToken))    }
        newestEventAdapter.addList(eventsList)
        newestEventAdapter.notifyDataSetChanged()
    }

    private fun onItemClicked(eventId: Int){
        var singleEventFragment = SingleEventFragment.newInstance(eventId)
        fragmentManager?.let{
            fragmentManager?.beginTransaction()?.add(R.id.joinedEventFragment, singleEventFragment)?.addToBackStack("joinedEvent")
                ?.commit()
        }
    }

}
