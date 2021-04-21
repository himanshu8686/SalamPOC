package com.salampoc.ui.productdetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Project Name :SalamPOC
 * Created By :Himanshu sharma on 21-04-2021
 * Package : com.salampoc.ui.productdetails
 */
class ProductDetailsAdapter : FragmentPagerAdapter {

    private var totalTabs: Int
    private val productDescription: String? = null
    private val productOtherDetails: String? = null
    private val productSpecificationModelList: List<ProductSpecificationModel>? = null

    constructor(fm: FragmentManager, totalTabs: Int) : super(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        this.totalTabs = totalTabs

    }

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment{
        return when(position){
            0 -> {
                ProductDescriptionFragment()
            }
            else ->{
                ProductSpecificationsFragment()
            }
        }
    }
}