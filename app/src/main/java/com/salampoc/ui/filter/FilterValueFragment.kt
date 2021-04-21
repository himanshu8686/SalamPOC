package com.salampoc.ui.filter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.salampoc.R

/**
 * A simple [Fragment] subclass.
 * Use the [FilterValueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilterValueFragment : Fragment() {

    private val args: FilterValueFragmentArgs by navArgs()
    private lateinit var filterItemList: List<FilterModel.FilterModelItem.Item>

    private lateinit var filter_value_container: LinearLayout

    private lateinit var filterModelItem: FilterModel.FilterModelItem

    companion object {
        const val MY_PREFERENCES="MyPref"
    }


    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_filter_value, container, false)
        filter_value_container = view.findViewById(R.id.filter_value_container)
        sharedPreferences = requireActivity().getSharedPreferences(MY_PREFERENCES,Context.MODE_PRIVATE)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterModelItem = args.currentFilterItem
        val filterTitle = args.filterTitle

        Log.d("TAG", filterModelItem.toString())

        inflateLayout(filterModelItem)
    }

    private fun inflateLayout(filterModelItem: FilterModel.FilterModelItem) {
        filterItemList = filterModelItem.items as List<FilterModel.FilterModelItem.Item>
        val type = filterModelItem.type
        val typeId = filterModelItem.typeId
        val title = filterModelItem.filterTitle
        inflateFilterValues(type, typeId, filterItemList, title)

    }

    private fun inflateFilterValues(
        type: String?,
        typeId: String?,
        filterItemList: List<FilterModel.FilterModelItem.Item>,
        title: String?
    ) {
        when (type) {
            "CheckBox" -> {
                inflateValuesWithCheckBox(type, typeId, filterItemList, title)
            }
            "RadioButton" -> {
                // inflateValuesWithRadioButton(type, typeId, filterItemList)
            }
        }
    }

    private fun loadChecked(){

    }

    private fun inflateValuesWithCheckBox(
        type: String,
        typeId: String?,
        filterItemList: List<FilterModel.FilterModelItem.Item>,
        title: String?
    ) {
        // Layout where you want to dynamically load checkboxes
        //TODO: load checkedList
        loadChecked()

        // populating checkbox list
        var index: Int = 0
        while (index < filterItemList.size) {
            // do something
            var checkBox = activity?.let { AppCompatCheckBox(it) }
            checkBox?.id = index
            checkBox?.text = filterItemList[index].value
           // check_container.addView(checkBox)
            // setting click listener here
            checkBox?.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(compoundButton: CompoundButton?, isChecked: Boolean) {
                    if (isChecked) {
                        Toast.makeText(activity, ""+checkBox.text+checkBox.id, Toast.LENGTH_SHORT).show()
                    }
                }

            })
            filter_value_container.addView(checkBox)
            index++
        }

    }

}
//
//if (FilterActivity.filteredList.size > 0) {
//    //checkig
//
//    for (i in 0..filterItemList.size-1) {
//        for (j in 0..FilterActivity.filteredList.size-1) {
//            if (filterItemList[i].value == FilterActivity.filteredList[j].value){
//                Log.d("TAG","==>"+filterItemList[i].value)
//            }
//        }
//    }
//}