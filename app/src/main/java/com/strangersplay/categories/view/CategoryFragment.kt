package com.strangersplay.categories.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.categories.adapter.CategoryAdapter
import com.strangersplay.categories.model.Category
import com.strangersplay.single_category.view.SingleCategoryFragment
import com.strangersplay.single_event.view.SingleEventFragment
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoryFragment : Fragment(), CategoryView {

    private val presenter = InstanceProvider.getCategoryPresenter(this)
    private val categoryAdapter = CategoryAdapter {onItemClicked(it)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_categories, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }

        presenter.displayCategories()
    }

    private fun onItemClicked(categoryName: String){
        val singleCategoryFragment = SingleCategoryFragment(categoryName)
        fragmentManager?.let{
            it.beginTransaction()
                .replace(R.id.fragmentCategories,singleCategoryFragment)
                .commit()
        }
    }

    override fun updateList(categories: List<Category>) {
        categoryAdapter.addList(categories)
        categoryAdapter.notifyDataSetChanged()
    }

}
