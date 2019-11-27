package com.strangersplay.rateUser.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.strangersplay.R
import com.strangersplay.rateUser.adapter.AddRateAdapter
import com.strangersplay.rateUser.model.Rate
import com.strangersplay.rateUser.presenter.AddRatePresenter
import com.strangersplay.single_event.view.SingleEventFragment
import kotlinx.android.synthetic.main.fragment_rate_players.*
import kotlinx.android.synthetic.main.fragment_single_category.*

class AddRateFragment(eventId: Int): Fragment(),AddRateView{
    override fun updateList(rates: List<Rate>) {

    }

    private val rateAdapter =
        AddRateAdapter { onItemClicked(it) }
    private lateinit var presenter: AddRatePresenter

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?
    ): View{
        return inflater.inflate(R.layout.fragment_rate_players, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        singleCategoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rateAdapter
        }


        addRateButton.setOnClickListener{
            print("button clicked")
            fragmentManager?.popBackStack()
        }

    }

    private fun onItemClicked(eventId: Int){
        val singleEventFragment = SingleEventFragment.newInstance(eventId)
        fragmentManager?.let{
            it.beginTransaction().add(R.id.singleCategoryFragment, singleEventFragment).addToBackStack("newestEvent").commit()
        }
    }

}