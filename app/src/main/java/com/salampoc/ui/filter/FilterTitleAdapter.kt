package com.salampoc.ui.filter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.salampoc.R
import java.lang.StringBuilder

/**
 * Project Name :SalamPOC
 * Created By :Himanshu sharma on 19-04-2021
 * Package : com.salampoc.ui.filter
 */
class FilterTitleAdapter(
    private val requireContext: Context,
    private val filterList: MutableList<NewFilterModel.FilterModelItem>
) : RecyclerView.Adapter<FilterTitleAdapter.TitleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.filter_title_item_layout,
            parent,
            false
        )
        return TitleViewHolder(view)
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        val filterModelItem = filterList.get(position)
        val filterTitle = filterList[position].filterTitle

        filterTitle?.let { holder.setData(filterModelItem, it, position) }
    }

    override fun getItemCount(): Int {
        return filterList.size
    }


    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val filterTitle: TextView = itemView.findViewById(R.id.tv_filter_title)
        private val tv_selected_values :TextView= itemView.findViewById(R.id.tv_selected_values)

        fun setData(
            filterModelItem: NewFilterModel.FilterModelItem,
            filterTitle: String,
            position: Int
        ) {
            this.filterTitle.text = filterTitle
            if( FilterActivity.filteredList.size > 0){
                val selectedValues = FilterActivity.filteredList.filter {
                    item -> item.filterParameterId!!.equals(filterModelItem.filterParameterId)
                }
                var list :MutableList<String> = ArrayList()
                for (i in selectedValues.indices){
                    selectedValues.get(i).value?.let { list.add(it) }
                }

                tv_selected_values.text =  list.joinToString(separator = ",")

            }

            itemView.setOnClickListener {
                //Log.d("TAG", filterModelItem.toString())
                //switch to value fragment with object

                val action =
                    FilterTitleFragmentDirections.actionFilterTitleFragmentToFilterValueFragment(filterModelItem,
                        filterTitle = filterTitle)
                itemView.findNavController().navigate(action)
            }
        }

    }
}