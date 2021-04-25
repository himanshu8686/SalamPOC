package com.salampoc.ui.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.salampoc.R
import com.salampoc.utils.AppHelper
import java.lang.reflect.Type


/**
 * A simple [Fragment] subclass.
 * Use the [FilterTitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilterTitleFragment : Fragment() {

    private lateinit var rv_filter_title:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_filter_title, container, false)

        rv_filter_title = view.findViewById(R.id.rv_filter_title)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonString = AppHelper.getJsonFromAssets(requireContext(), "filters.json")


        val gson = Gson()
        val listUserType: Type = object : TypeToken<MutableList<NewFilterModel.FilterModelItem?>?>() {}.type

        val filterList: MutableList<NewFilterModel.FilterModelItem> = gson.fromJson(jsonString, listUserType)


        for (i in filterList.indices) {
            Log.d("TAG", filterList[i].toString())
        }
        /// setting recycler view for filter title

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation= LinearLayoutManager.VERTICAL
        rv_filter_title.also {
            it.layoutManager = layoutManager
            it.adapter= FilterTitleAdapter(requireContext(),filterList)
            (it.adapter as FilterTitleAdapter).notifyDataSetChanged()
        }
        /// setting recycler view for filter title


    }


}