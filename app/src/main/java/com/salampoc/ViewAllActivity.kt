package com.salampoc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import com.salampoc.adapters.GridAndListAdapter
import com.salampoc.models.HorizontalProductScrollModel
import com.salampoc.ui.filter.FilterActivity
import java.util.ArrayList

class ViewAllActivity : AppCompatActivity() {

    companion object {
        const val SPAN_COUNT_ONE = 1
        const val SPAN_COUNT_TWO = 2

        const val VIEW_SMALL=1
        const val VIEW_BIG=2
    }

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var gridAndListAdapter: GridAndListAdapter
    private lateinit var rv_universal:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)
        val toolbar: Toolbar = findViewById(R.id.v_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = intent.getStringExtra("title")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        //// temp list of horizontal layout
        val horizontalProductScrollModelList: MutableList<HorizontalProductScrollModel> = ArrayList()
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"One Plus","SD 865","Rs. 55,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Motorola","SD 765","Rs. 45,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Huawei","SD 665","Rs. 35,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Redmi","SD 565","Rs. 25,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Iqoo","SD 465","Rs. 15,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Micromax","SD 365","Rs. 5,000"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.oneplus,"Nokia","SD 265","Rs. 3,000"))
        //// temp list of horizontal layout


//        val rv_view_all_horizontal:RecyclerView= findViewById(R.id.rv_view_all_horizontal)
//        rv_view_all_horizontal.visibility= View.VISIBLE
//
//        val linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.orientation=LinearLayoutManager.VERTICAL
//        rv_view_all_horizontal.layoutManager = linearLayoutManager

//        val gv_view_all_grid:GridView = findViewById(R.id.gv_view_all_grid)
//        gv_view_all_grid.visibility=View.VISIBLE
//        val gridProductViewAdapter=GridProductViewAdapter(horizontalProductScrollModelList)
//        gv_view_all_grid.adapter=gridProductViewAdapter

        gridLayoutManager = GridLayoutManager(this, SPAN_COUNT_ONE)
        gridAndListAdapter = GridAndListAdapter(horizontalProductScrollModelList,gridLayoutManager)
        rv_universal = findViewById(R.id.rv_universal)
        rv_universal.adapter = gridAndListAdapter
        rv_universal.layoutManager=gridLayoutManager


        val btn_toggle_group:MaterialButtonToggleGroup = findViewById(R.id.btn_toggle_group)
        btn_toggle_group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked)
            {
                when(checkedId){
                    R.id.btn_list -> {
                        Toast.makeText(this, "list selected", Toast.LENGTH_SHORT).show()
                        switchLayout("LIST")
                    }
                    R.id.btn_grid -> {
                        Toast.makeText(this, "grid selected", Toast.LENGTH_SHORT)
                            .show()
                        switchLayout("GRID")
                    }


                }
            }
            else{
                if (group.checkedButtonId == View.NO_ID){
                    Toast.makeText(this, "nothing selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun switchLayout(s: String) {
        if (s == "LIST"){
            gridLayoutManager.spanCount = SPAN_COUNT_ONE
        }
        else if (s == "GRID"){
            gridLayoutManager.spanCount = SPAN_COUNT_TWO
        }
        gridAndListAdapter.notifyItemRangeChanged(0,gridAndListAdapter.itemCount)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            R.id.action_filter -> {
                val intent = Intent(this, FilterActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.view_all_menu, menu)
        return true
    }
}