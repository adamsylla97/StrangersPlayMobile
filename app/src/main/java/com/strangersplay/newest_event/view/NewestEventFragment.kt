package com.strangersplay.newest_event.view

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.add_event.view.NewEventFragment
import com.strangersplay.categories.view.CategoryFragment
import com.strangersplay.newest_event.adapters.NewestEventAdapter
import com.strangersplay.newest_event.model.Event
import com.strangersplay.newest_event.presenter.FilterOptions
import com.strangersplay.newest_event.presenter.NewestEventPresenter
import com.strangersplay.single_event.view.SingleEventFragment
import kotlinx.android.synthetic.main.fragment_newest_event.*

class NewestEventFragment : Fragment(), NewestEventView {

    private lateinit var presenter: NewestEventPresenter
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

        presenter = InstanceProvider.getNewestEventPresenter(this, context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager)
        newestEventsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newestEventAdapter
        }
        addButton.setOnClickListener{
            addButton.hide()
            fragmentManager?.beginTransaction()?.add(R.id.newestEventFragment, NewEventFragment())?.addToBackStack("newestEvent")
                ?.commit()
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
        newestEventAdapter.addList(events)
        newestEventAdapter.notifyDataSetChanged()
    }

    private fun onItemClicked(eventId: Int){
        var singleEventFragment = SingleEventFragment(eventId)
        fragmentManager?.let{
            fragmentManager?.beginTransaction()?.add(R.id.newestEventFragment, singleEventFragment)?.addToBackStack("newestEvent")
                ?.commit()
//            it.beginTransaction()
//                .replace(R.id.newestEventFragment,singleEventFragment)
//                .commit()
        }
    }
}
