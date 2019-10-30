package com.strangersplay.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.strangersplay.R
import com.strangersplay.categories.model.Category

class CategoryAdapter(private val listener: (Category) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CategoriesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.caterory_item, parent, false)
        return CategoriesHolder(inflatedView, listener)
    }

    val categoriesList = mutableListOf<Category>()

    fun addList(listToAdd: List<Category>) {
        if (categoriesList.isNotEmpty())
            categoriesList.clear()
        categoriesList.addAll(listToAdd)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        val item = categoriesList[position]
        holder.bind(item)
    }

    class CategoriesHolder(private val view: View, private val itemClick: (Category) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun bind(category: Category) {

        }

    }

}