package com.salampoc.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salampoc.R
import com.salampoc.adapters.CategoryAdapter
import com.salampoc.adapters.HomePageAdapter
import com.salampoc.models.CategoryModel
import com.salampoc.models.HomePageModel
import com.salampoc.models.HorizontalProductScrollModel
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var viewOfLayout: View
    private lateinit var categoryRecyclerView:RecyclerView

    // horizontal product layout //

    private lateinit var horizontalLayoutTitle:TextView
    private lateinit var btn_horizontal_viewAll:Button
    private lateinit var horizontalRecyclerView:RecyclerView

    // horizontal product layout //

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        viewOfLayout = inflater.inflate(R.layout.fragment_home, container, false)

        categoryRecyclerView = viewOfLayout.findViewById(R.id.category_recycler_view)

         // temp list
        val categoryModelList: MutableList<CategoryModel> = ArrayList()
        categoryModelList.add(CategoryModel("link", "Home"))
        categoryModelList.add(CategoryModel("link", "Men"))
        categoryModelList.add(CategoryModel("link", "Women"))
        categoryModelList.add(CategoryModel("link", "Gadgets"))
        categoryModelList.add(CategoryModel("link", "Kids"))
        // temp list


        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        categoryRecyclerView.also {
            it.layoutManager = layoutManager
            it.adapter= CategoryAdapter(requireContext(),categoryModelList)
            (it.adapter as CategoryAdapter).notifyDataSetChanged()
        }

        //// temp list of horizontal layout
        val horizontalProductScrollModelList: MutableList<HorizontalProductScrollModel> = ArrayList()
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"One Plus","SD 865","Rs. 55,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Motorola","SD 765","Rs. 45,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Huawei","SD 665","Rs. 35,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Redmi","SD 565","Rs. 25,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Iqoo","SD 465","Rs. 15,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Micromax","SD 365","Rs. 5,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Nokia","SD 265","Rs. 3,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Infinix","SD 265","Rs. 3,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Gionee","SD 265","Rs. 3,000"))
        //// temp list of horizontal layout

        ///////// Heterogenous list

        val parent_recycler_view:RecyclerView = viewOfLayout.findViewById(R.id.parent_recycler_view)
        val parentLayoutManager = LinearLayoutManager(requireContext())
        parentLayoutManager.orientation=LinearLayoutManager.VERTICAL

        val homePageModelList: MutableList<HomePageModel> = ArrayList()
        homePageModelList.add(HomePageModel(1,"# Deals of the day",horizontalProductScrollModelList))
        homePageModelList.add(HomePageModel(2,"# Trending",horizontalProductScrollModelList))
        homePageModelList.add(HomePageModel(1,"# Salam's Pick",horizontalProductScrollModelList))
        homePageModelList.add(HomePageModel(2,"# Hot offers",horizontalProductScrollModelList))
        homePageModelList.add(HomePageModel(2,"# Grab Now",horizontalProductScrollModelList))
        parent_recycler_view.also {
            it.layoutManager = parentLayoutManager
            it.adapter = HomePageAdapter(requireContext(),homePageModelList)
            (it.adapter as HomePageAdapter).notifyDataSetChanged()
        }


        ///////// Heterogenous list

        return viewOfLayout
    }




}