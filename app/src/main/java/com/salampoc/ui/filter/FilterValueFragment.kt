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
import androidx.appcompat.widget.AppCompatRadioButton
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
    private lateinit var filterItemList: List<NewFilterModel.FilterModelItem.Item>

    private lateinit var filter_value_container: LinearLayout

    private lateinit var filterModelItem: NewFilterModel.FilterModelItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_filter_value, container, false)
        filter_value_container = view.findViewById(R.id.filter_value_container)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterModelItem = args.currentFilterItem
        val filterTitle = args.filterTitle

        Log.d("TAG", filterModelItem.toString())

        inflateLayout(filterModelItem)
    }

    private fun inflateLayout(filterModelItem: NewFilterModel.FilterModelItem) {
        filterItemList = filterModelItem.items as List<NewFilterModel.FilterModelItem.Item>
        val type = filterModelItem.type
        val typeId = filterModelItem.typeId
        val title = filterModelItem.filterTitle
        inflateFilterValues(type, typeId, filterItemList, title)

    }

    private fun inflateFilterValues(
        type: String?,
        typeId: String?,
        filterItemList: List<NewFilterModel.FilterModelItem.Item>,
        title: String?
    ) {
        when (type) {
            "CheckBox" -> {
                inflateValuesWithCheckBox(type, typeId, filterItemList, title)
            }
            "RadioButton" -> {
                inflateValuesWithRadioButton(type, typeId, filterItemList)
            }
        }
    }

    private fun inflateValuesWithRadioButton(
        type: String,
        typeId: String?,
        filterItemList: List<NewFilterModel.FilterModelItem.Item>
    ) {
        val radioGroup = RadioGroup(activity)
        radioGroup.orientation = RadioGroup.VERTICAL


        // populating answer list with checkbox and text
        for (index in filterItemList.indices) {
            var filterRadioButton = AppCompatRadioButton(requireActivity())
            filterRadioButton.id = index
            filterRadioButton.text = filterItemList.get(index).value
            radioGroup.addView(filterRadioButton)
            filterRadioButton?.isChecked = isValuePresent(filterItemList[index])
            // OnClickListener of checkBox
            filterRadioButton.setOnCheckedChangeListener(object :
                CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
                    if (isChecked) {
                        Toast.makeText(activity, "check "+filterRadioButton.text, Toast.LENGTH_SHORT).show()
                        FilterActivity.filteredList.add(
                            NewFilterModel.FilterModelItem.Item(
                                filterItemList.get(index).filterParameterId,
                                filterItemList.get(index).filterValueId,
                                null,
                                null,
                                filterItemList.get(index).value
                            )
                        )
                    }else{
                        FilterActivity.filteredList.remove(
                            NewFilterModel.FilterModelItem.Item(
                                filterItemList.get(index).filterParameterId,
                                filterItemList.get(index).filterValueId,
                                null,
                                null,
                                filterItemList.get(index).value
                            )
                        )
                    }
                }

            })

        }
        filter_value_container.addView(radioGroup)
    }

    private fun inflateValuesWithCheckBox(
        type: String,
        typeId: String?,
        filterItemList: List<NewFilterModel.FilterModelItem.Item>,
        title: String?
    ) {
        // Layout where you want to dynamically load checkboxes
        // populating checkbox lis

        for (index in filterItemList.indices) {
            // do something
            var checkBox = activity?.let { AppCompatCheckBox(it) }
            checkBox?.id = index
            checkBox?.text = filterItemList[index].value

            //check
            checkBox?.isChecked = isValuePresent(filterItemList[index])

            // setting click listener here
            checkBox?.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(compoundButton: CompoundButton?, isChecked: Boolean) {
                    if (isChecked) {
                        FilterActivity.filteredList.add(
                            NewFilterModel.FilterModelItem.Item(
                                filterItemList.get(index).filterParameterId,
                                filterItemList.get(index).filterValueId,
                                null,
                                null,
                                filterItemList.get(index).value
                            )
                        )

                    } else {
                        FilterActivity.filteredList.remove(
                            NewFilterModel.FilterModelItem.Item(
                                filterItemList.get(index).filterParameterId,
                                filterItemList.get(index).filterValueId,
                                null,
                                null,
                                filterItemList.get(index).value
                            )
                        )
                    }
                }

            })
            filter_value_container.addView(checkBox)
        }
    }

    //////////////////////////////////////////
    fun isValuePresent(item: NewFilterModel.FilterModelItem.Item): Boolean {
        if (FilterActivity.filteredList.size > 0) {
            return FilterActivity.filteredList.contains(item)
        }
        return false
    }
}