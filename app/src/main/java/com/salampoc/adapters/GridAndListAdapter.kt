package com.salampoc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salampoc.R
import com.salampoc.ViewAllActivity
import com.salampoc.models.HomePageModel
import com.salampoc.models.HorizontalProductScrollModel

/**
 * Project Name :SalamPOC
 * Created By :Himanshu sharma on 14-04-2021
 * Package : com.salampoc.adapters
 */
class GridAndListAdapter(
    private val horizontalProductScrollModelList: MutableList<HorizontalProductScrollModel>,
    private val gridLayoutManager: GridLayoutManager
) : RecyclerView.Adapter<GridAndListAdapter.ItemViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        val spanCount = gridLayoutManager.spanCount
        return if (spanCount == ViewAllActivity.SPAN_COUNT_ONE) {
            ViewAllActivity.VIEW_BIG
        } else {
            ViewAllActivity.VIEW_SMALL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View
        if (viewType == ViewAllActivity.VIEW_BIG) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_all_row_item, parent, false)

        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_all_grid_item, parent, false)
        }

        return ItemViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val imageResource = horizontalProductScrollModelList[position].productImage
        val productTitle = horizontalProductScrollModelList[position].productTitle
        val productDescription = horizontalProductScrollModelList[position].productDescription
        val productPrice = horizontalProductScrollModelList[position].productPrice

        holder.setData(imageResource, productTitle, productDescription, productPrice, position)
    }

    override fun getItemCount(): Int {
        return horizontalProductScrollModelList.size
    }

    class ItemViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        // grid
        private lateinit var iv_product: ImageView
        private lateinit var tv_product_title: TextView
        private lateinit var tv_product_spec: TextView
        private lateinit var tv_product_price: TextView

        // list
        private lateinit var iv_row_item: ImageView
        private lateinit var tv_row_product_title: TextView
        private lateinit var tv_row_product_description: TextView
        private lateinit var tv_row_product_price: TextView

        init {
            if (viewType == ViewAllActivity.VIEW_BIG) {
                iv_row_item = itemView.findViewById(R.id.iv_row_item)
                tv_row_product_title = itemView.findViewById(R.id.tv_row_product_title)
                tv_row_product_description = itemView.findViewById(R.id.tv_row_product_description)
                tv_row_product_price = itemView.findViewById(R.id.tv_row_product_price)
            } else {
                iv_product = itemView.findViewById(R.id.iv_product)
                tv_product_title = itemView.findViewById(R.id.tv_product_title)
                tv_product_spec = itemView.findViewById(R.id.tv_product_spec)
                tv_product_price = itemView.findViewById(R.id.tv_product_price)
            }
        }

        fun setData(
            imageResource: Int?,
            productTitle: String?,
            productDescription: String?,
            productPrice: String?,
            position: Int
        ) {
            if (itemViewType == ViewAllActivity.VIEW_BIG){
                imageResource?.let {
                    iv_row_item.setImageResource(it)
                }

                productTitle?.let {
                    tv_row_product_title.text = it
                }
                productDescription?.let {
                    tv_row_product_description.text = it
                }
                productPrice?.let {
                    tv_row_product_price.text = it
                }
            }else{
                imageResource?.let {
                    iv_product.setImageResource(it)
                }

                productTitle?.let {
                    tv_product_title.text = it
                }
                productDescription?.let {
                    tv_product_spec.text = it
                }
                productPrice?.let {
                    tv_product_price.text = it
                }
            }



        }

    }

}