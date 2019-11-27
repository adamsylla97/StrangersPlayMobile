package com.strangersplay.rateUser.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.add_event.model.UserIds
import com.strangersplay.rateUser.adapter.AddRateAdapter
import com.strangersplay.rateUser.model.Rate
import com.strangersplay.rateUser.presenter.AddRatePresenter
import com.strangersplay.single_event.view.SingleEventFragment
import kotlinx.android.synthetic.main.fragment_rate_players.*
import kotlinx.android.synthetic.main.fragment_single_category.*

class AddRateFragment(private val users: MutableList<UserIds>): Fragment(),AddRateView{
    override fun updateList(rates: List<Rate>) {

    }

    private val rateAdapter = AddRateAdapter { onItemClicked(it) }
    private val presenter: AddRatePresenter = InstanceProvider.getAddRatePresenter(this)

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?
    ): View{
        return inflater.inflate(R.layout.fragment_rate_players, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addRateRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rateAdapter
        }

        rateAdapter.addList(users)

    }

    private fun onItemClicked(pair: Pair<Rate, Int>){
        presenter.sendRates(pair.first)
        val value = users.find {
            it.id == pair.second
        }

        Log.i("supertest 12 12", "${pair.second}  $value")
        if(value != null){
            users.remove(value)
        }
        rateAdapter.addList(users.toList())
    }

}