package com.salampoc.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.salampoc.R
import com.salampoc.models.CategoryModel

/**
 * Project Name :SalamPOC
 * Created By :Himanshu sharma on 13-04-2021
 * Package : com.salampoc.adapters
 */
class CategoryAdapter(
    private val context: Context,
    private val categoryModelList: List<CategoryModel>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.category_item,
            parent,
            false
        )
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryIcon = categoryModelList[position].icon
        val categoryName = categoryModelList[position].categoryName
        categoryName?.let { holder.setCategoryName(it) }

    }

    override fun getItemCount(): Int {
        return categoryModelList.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryIcon: ImageView = itemView.findViewById(R.id.category_icon)
        private val categoryName: TextView = itemView.findViewById(R.id.tv_category)

        private fun setCategoryIcon(){
           //TODO:get category icon
        }

        fun setCategoryName(name:String){
            categoryName.text = name
        }
    }
}