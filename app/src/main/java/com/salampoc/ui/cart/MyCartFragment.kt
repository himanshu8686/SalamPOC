package com.salampoc.ui.cart

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.salampoc.R

class MyCartFragment : Fragment() {

    private lateinit var myCartViewModel: MyCartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myCartViewModel =
            ViewModelProvider(this).get(MyCartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_my_cart, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        myCartViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}