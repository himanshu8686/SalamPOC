package com.salampoc.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.salampoc.R

/**
 * A simple [Fragment] subclass.
 * Use the [FilterTitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilterTitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_filter_title, container, false)

        val title: TextView = view.findViewById(R.id.tv_filter_title)

        title.setOnClickListener {
            val action = FilterTitleFragmentDirections.actionFilterTitleFragmentToFilterValueFragment()
            findNavController().navigate(action)

        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}