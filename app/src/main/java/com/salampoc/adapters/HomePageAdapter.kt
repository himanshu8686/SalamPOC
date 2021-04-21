package com.salampoc.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salampoc.R
import com.salampoc.ViewAllActivity
import com.salampoc.models.HomePageModel
import com.salampoc.models.HorizontalProductScrollModel
import com.salampoc.ui.productdetails.ProductDetailsActivity

/**
 * Project Name :SalamPOC
 * Created By :Himanshu sharma on 13-04-2021
 * Package : com.salampoc.adapters
 */

lateinit var recycledViewPool: RecyclerView.RecycledViewPool

class HomePageAdapter(
    private val context: Context,
    private val homePageModelList: List<HomePageModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        recycledViewPool = RecyclerView.RecycledViewPool()
    }

    override fun getItemViewType(position: Int): Int {
        return when (homePageModelList[position].getType()) {
            1 -> HomePageModel.HORIZONTAL_PRODUCT_VIEW
            2 -> HomePageModel.GRID_PRODUCT_VIEW
            else -> -1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HomePageModel.HORIZONTAL_PRODUCT_VIEW) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.horizontal_scroll_layout, parent, false)
            HorizontalProductViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_product_layout, parent, false)
            GridProductViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (homePageModelList[position].getType()) {
            HomePageModel.HORIZONTAL_PRODUCT_VIEW -> {
                val title = homePageModelList[position].getTitle()
                val horizontalProductScrollModelList =
                    homePageModelList[position].getHorizontalProductScrollModelList()
                (holder as HorizontalProductViewHolder).setHorizontalProductLayout(
                    title,
                    horizontalProductScrollModelList
                )
            }
            HomePageModel.GRID_PRODUCT_VIEW -> {
                val title = homePageModelList[position].getTitle()
                val horizontalProductScrollModelList =
                    homePageModelList[position].getHorizontalProductScrollModelList()
                horizontalProductScrollModelList?.let {
                    (holder as GridProductViewHolder).setGridProductLayout(
                        title,
                        it
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return homePageModelList.size
    }


    /**
     *  Horizontal product view class
     */
    class HorizontalProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var horizontalLayoutTitle: TextView =
            itemView.findViewById(R.id.tv_horizontal_title)
        private var btn_horizontal_viewAll: Button =
            itemView.findViewById(R.id.btn_horizontal_viewAll)
        private var horizontalRecyclerView: RecyclerView =
            itemView.findViewById(R.id.horizontal_recycler_view)

        init {
            horizontalRecyclerView.setRecycledViewPool(recycledViewPool)
        }

        fun setHorizontalProductLayout(
            title: String?,
            horizontalProductScrollModelList: List<HorizontalProductScrollModel>?
        ) {
            horizontalLayoutTitle.text = title
            if (horizontalProductScrollModelList != null) {
                if (horizontalProductScrollModelList.size > 8) {
                    btn_horizontal_viewAll.visibility = View.VISIBLE

                    btn_horizontal_viewAll.setOnClickListener {
                        val intent = Intent(itemView.context, ViewAllActivity::class.java)
                        intent.putExtra("title", title)
                        itemView.context.startActivity(intent)
                    }
                } else {
                    btn_horizontal_viewAll.visibility = View.INVISIBLE
                }
            }

            val layoutManager = LinearLayoutManager(itemView.context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            horizontalRecyclerView.also {
                it.layoutManager = layoutManager
                it.adapter = horizontalProductScrollModelList?.let { list ->
                    HorizontalProductScrollAdapter(
                        list
                    )
                }
                (it.adapter as HorizontalProductScrollAdapter).notifyDataSetChanged()
            }
        }

    }


    /**
     *  Grid product view class
     */
    class GridProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tv_grid_title: TextView = itemView.findViewById(R.id.tv_grid_title)
        private var grid_btn_viewAll: Button = itemView.findViewById(R.id.grid_btn_viewAll)
        private var grid_product_layout: GridLayout = itemView.findViewById(R.id.grid_product_layout)

        fun setGridProductLayout(
            title: String?,
            horizontalProductScrollModelList: List<HorizontalProductScrollModel>
        ) {
            tv_grid_title.text = title

            for(index in 0..3){
                val productImage: ImageView = grid_product_layout.getChildAt(index).findViewById(R.id.iv_product)
                val productTitle : TextView = grid_product_layout.getChildAt(index).findViewById(R.id.tv_product_title)
                val productDesc : TextView = grid_product_layout.getChildAt(index).findViewById(R.id.tv_product_spec)
                val productPrice : TextView = grid_product_layout.getChildAt(index).findViewById(R.id.tv_product_price)

                horizontalProductScrollModelList[index].productImage?.let {
                    productImage.setImageResource(
                        it
                    )
                }
                productTitle.text=horizontalProductScrollModelList[index].productTitle
                productDesc.text=horizontalProductScrollModelList[index].productDescription
                productPrice.text=horizontalProductScrollModelList[index].productPrice
                grid_product_layout.getChildAt(index).setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )

                val horizontalProductScrollModel =horizontalProductScrollModelList[index]

                // add click  listener to product
                grid_product_layout.getChildAt(index).setOnClickListener{
                    val productDetailsIntent = Intent(itemView.context, ProductDetailsActivity::class.java)
                    productDetailsIntent.putExtra("PROD_DETAILS",horizontalProductScrollModel)
                    itemView.context.startActivity(productDetailsIntent)
                }

            }

            grid_btn_viewAll.setOnClickListener {
                val intent = Intent(itemView.context, ViewAllActivity::class.java)
                intent.putExtra("title", title)
                itemView.context.startActivity(intent)
            }
        }
    }


}