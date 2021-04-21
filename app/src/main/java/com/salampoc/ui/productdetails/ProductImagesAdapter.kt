package com.salampoc.ui.productdetails

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.salampoc.R

/**
 * Project Name :SalamPOC
 * Created By :Himanshu sharma on 21-04-2021
 * Package : com.salampoc.ui.productdetails
 */
class ProductImagesAdapter() : PagerAdapter() {

    private lateinit var productImagesList: List<Int>

    constructor(productImagesList: List<Int>) : this() {
        this.productImagesList=productImagesList
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val productImage = ImageView(container.context)
        Glide.with(container.context).load(productImagesList[position])
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_home)
            ).into(productImage)
        container.addView(productImage, 0)
        return productImage
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }

    override fun getCount(): Int {
        return productImagesList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}