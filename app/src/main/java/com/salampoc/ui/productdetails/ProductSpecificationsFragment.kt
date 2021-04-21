package com.salampoc.ui.productdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salampoc.R


/**
 * A simple [Fragment] subclass.
 * Use the [ProductSpecificationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductSpecificationsFragment : Fragment() {

    private lateinit var rv_product_specification: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_product_specifications, container, false)
        rv_product_specification = view.findViewById(R.id.rv_product_specification)
        val linearLayoutManager=LinearLayoutManager(view.context)
        linearLayoutManager.orientation=LinearLayoutManager.VERTICAL
        rv_product_specification.layoutManager=linearLayoutManager

        /********* dummy list *********/
        var productSpecificationModelList: MutableList<ProductSpecificationModel> = ArrayList()
        productSpecificationModelList.add(ProductSpecificationModel(0,"General"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"RAM","8GB"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"OS","Android"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"storage","32GB"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"Camera","48MP"))
        productSpecificationModelList.add(ProductSpecificationModel(0,"Display"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"Screen","Amoled"))


        /********* dummy list *********/
        val productSpecificationsAdapter= ProductSpecificationsAdapter(productSpecificationModelList)
        rv_product_specification.adapter=productSpecificationsAdapter
        productSpecificationsAdapter.notifyDataSetChanged()
        return view
    }

}