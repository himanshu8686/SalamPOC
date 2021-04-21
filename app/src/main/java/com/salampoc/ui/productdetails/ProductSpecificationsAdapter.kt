package com.salampoc.ui.productdetails

import android.content.Context
import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.salampoc.R

/**
 * Project Name :SalamPOC
 * Created By :Himanshu sharma on 21-04-2021
 * Package : com.salampoc.ui.productdetails
 */
class ProductSpecificationsAdapter() :
    RecyclerView.Adapter<ProductSpecificationsAdapter.ProductSpecViewHolder>() {
    private lateinit var productSpecificationModelList: List<ProductSpecificationModel>

    constructor(productSpecificationModelList: List<ProductSpecificationModel>) : this() {
        this.productSpecificationModelList = productSpecificationModelList
    }


    class ProductSpecViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var title: TextView

        fun setTitle(title: String?) {
            this.title = (itemView as TextView)
            this.title.setText(title)
        }

        fun setFeatures(featureTitle: String?, featureDetails: String?) {
            var tv_feature_name: TextView = itemView.findViewById(R.id.tv_feature_name)
            var tv_feature_value: TextView = itemView.findViewById(R.id.tv_feature_value)
            tv_feature_name.text = featureTitle
            tv_feature_value.text = featureDetails
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSpecViewHolder {
        when (viewType) {
            ProductSpecificationModel.SPECIFICATION_TITLE -> {
                val title = TextView(parent.context)
                title.setTypeface(null, Typeface.BOLD)
                title.textSize = 16f
                title.setTextColor(ContextCompat.getColor(parent.context, R.color.material_yellow))
                val params = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(
                    setDp(16, parent.context), setDp(16, parent.context),
                    setDp(16, parent.context), setDp(8, parent.context)
                )
                title.layoutParams = params
                return ProductSpecViewHolder(title)
            }
            else -> {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.product_specification_item_layout, parent, false)
                return ProductSpecViewHolder(view)
            }
        }
    }

    private fun setDp(dp: Int, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    override fun getItemViewType(position: Int): Int {
        return when (productSpecificationModelList[position].getType()) {
            0 -> {
                ProductSpecificationModel.SPECIFICATION_TITLE
            }
            1 -> {
                ProductSpecificationModel.SPECIFICATION_BODY
            }
            else -> -1
        }
    }

    override fun onBindViewHolder(holder: ProductSpecViewHolder, position: Int) {
        when (productSpecificationModelList[position].getType()) {
            ProductSpecificationModel.SPECIFICATION_TITLE -> {
                val title = productSpecificationModelList[position].getTitle()
                holder.setTitle(title)
            }
            ProductSpecificationModel.SPECIFICATION_BODY -> {
                val featureTitle = productSpecificationModelList[position].getFeatureName()
                val featureDetails = productSpecificationModelList[position].getFeatureValue()
                holder.setFeatures(featureTitle, featureDetails)
            }
        }
    }


    override fun getItemCount(): Int {
        return productSpecificationModelList.size
    }
}