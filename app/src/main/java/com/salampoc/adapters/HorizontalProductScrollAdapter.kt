package com.salampoc.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.salampoc.R
import com.salampoc.models.HorizontalProductScrollModel
import com.salampoc.ui.productdetails.ProductDetailsActivity

/**
 * Project Name :SalamPOC
 * Created By :Himanshu sharma on 13-04-2021
 * Package : com.salampoc.adapters
 */
class HorizontalProductScrollAdapter(
    private val horizontalProductScrollModelList: List<HorizontalProductScrollModel>
) :
    RecyclerView.Adapter<HorizontalProductScrollAdapter.HorizontalProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalProductViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.horizontal_scroll_item_layout,
            parent,
            false
        )
        return HorizontalProductScrollAdapter.HorizontalProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalProductViewHolder, position: Int) {
        var horizontalProductScrollModel = horizontalProductScrollModelList.get(position)
        val imageResource = horizontalProductScrollModelList[position].productImage
        val productTitle = horizontalProductScrollModelList[position].productTitle
        val productDescription = horizontalProductScrollModelList[position].productDescription
        val productPrice = horizontalProductScrollModelList[position].productPrice

        holder.setData(imageResource,productTitle,productDescription,productPrice,position,horizontalProductScrollModel)
    }

    override fun getItemCount(): Int {
        return if (horizontalProductScrollModelList.size > 8)
            8
        else
            horizontalProductScrollModelList.size
    }

    class HorizontalProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val iv_product: ImageView = itemView.findViewById(R.id.iv_product)
        private val tv_product_title: TextView = itemView.findViewById(R.id.tv_product_title)
        private val tv_product_spec: TextView = itemView.findViewById(R.id.tv_product_spec)
        private val tv_product_price: TextView = itemView.findViewById(R.id.tv_product_price)

        fun setData(
            imageResource: Int?,
            productTitle: String?,
            productDescription: String?,
            productPrice: String?,
            position: Int,
            horizontalProductScrollModel: HorizontalProductScrollModel
        ) {
            imageResource?.let {
                iv_product.setImageResource(it) }

            productTitle?.let {
                tv_product_title.text = it
            }
            productDescription?.let {
                tv_product_spec.text = it
            }
            productPrice?.let {
                tv_product_price.text = it
            }

            itemView.setOnClickListener{
                Toast.makeText(itemView.context, "index "+position, Toast.LENGTH_SHORT).show()
                val productDetailsIntent = Intent(itemView.context,ProductDetailsActivity::class.java)
                productDetailsIntent.putExtra("PROD_DETAILS",horizontalProductScrollModel)
                itemView.context.startActivity(productDetailsIntent)
            }

        }
    }
}