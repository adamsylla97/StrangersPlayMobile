package com.strangersplay.single_category.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.single_category.adapter.SingleCategoryAdapter
import com.strangersplay.single_event.view.SingleEventFragment
import kotlinx.android.synthetic.main.fragment_single_category.*


class SingleCategoryFragment(private val categoryName: String) : Fragment(), SingleCategoryView {

    private val presenter = InstanceProvider.getSingleCategoryPresenter(this)
    private val categoryAdapter = SingleCategoryAdapter{onItemClicked(it)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        singleCategoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }

        presenter.fetchEvents(categoryName)

    }

    override fun updateList(events: List<com.strangersplay.single_category.model.Event>) {
        categoryAdapter.addList(events)
        categoryAdapter.notifyDataSetChanged()
    }

    private fun onItemClicked(eventId: Int){
        val singleEventFragment = SingleEventFragment(eventId)
        fragmentManager?.let{
            it.beginTransaction()
                .replace(R.id.singleCategoryFragment,singleEventFragment)
                .commit()
        }
    }



}
