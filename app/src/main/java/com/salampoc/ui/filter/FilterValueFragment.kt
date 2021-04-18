package com.salampoc.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.salampoc.R

/**
 * A simple [Fragment] subclass.
 * Use the [FilterValueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilterValueFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter_value, container, false)
    }

}