package com.salampoc.ui.productdetails

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.salampoc.R
import com.salampoc.models.HorizontalProductScrollModel

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var horizontalProductScrollModel:HorizontalProductScrollModel
    /** product image layout**/
    private var productImagesViewPager: ViewPager? = null
    private var viewPagerIndicatorTabLayout: TabLayout? = null
    private var tv_product_details_title: TextView? = null
    private var tv_rating_mini: TextView? = null
    private var tv_rating_count_mini: TextView? = null
    private var tv_product_details_price: TextView? = null
    private var tv_product_details_price_cut: TextView? = null
    var fb_wish_list: FloatingActionButton? = null

    /** product image layout **/

    /** description tab layout*/
    private lateinit var product_details_tab_layout: TabLayout
    private lateinit var product_details_viewpager: ViewPager
    /** description tab layout*/

    /** description only layout*/
    /** description only layout*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val toolbar: Toolbar = findViewById(R.id.pd_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Product details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        /*** get Parceleable data****/
        horizontalProductScrollModel=intent.getParcelableExtra("PROD_DETAILS")



        /** product image layout **/
        fb_wish_list = findViewById(R.id.fb_wish_list)
        productImagesViewPager = findViewById(R.id.product_images_view_pager)
        viewPagerIndicatorTabLayout = findViewById(R.id.viewpager_indicator_tablayout)
        tv_product_details_title = findViewById(R.id.tv_product_details_title)
        tv_rating_mini = findViewById(R.id.tv_rating_mini)
        tv_rating_count_mini = findViewById(R.id.tv_rating_count_mini)
        tv_product_details_price = findViewById(R.id.tv_product_details_price)
        tv_product_details_price_cut = findViewById(R.id.tv_product_details_price_cut)

        /**setting product data ***/
        tv_product_details_title?.text=horizontalProductScrollModel.productTitle
        tv_product_details_price?.text=horizontalProductScrollModel.productPrice
        /**setting product data ***/


        /** image slider**/
        var productImages : MutableList<Int> =  ArrayList()
        productImages.add(R.drawable.oneplus)
        productImages.add(R.drawable.oneplus)
        productImages.add(R.drawable.oneplus)

        val productImagesAdapter=ProductImagesAdapter(productImages)
        productImagesViewPager?.adapter=productImagesAdapter
        viewPagerIndicatorTabLayout?.setupWithViewPager(productImagesViewPager, true)
        /** image slider**/

        /** product image layout **/

        /** description tab layout*/
        product_details_viewpager = findViewById(R.id.product_details_viewpager)
        product_details_tab_layout = findViewById(R.id.product_details_tab_layout)

        product_details_viewpager.adapter = ProductDetailsAdapter(
            supportFragmentManager,
            product_details_tab_layout.tabCount
        )
        /** setting adapter for tab layout  */
        product_details_viewpager.addOnPageChangeListener(
            TabLayoutOnPageChangeListener(
                product_details_tab_layout
            )
        )

        product_details_tab_layout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                product_details_viewpager.setCurrentItem(tab.position, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        /** description tab layout*/

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_and_cart_icon, menu)

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.action_search -> {
                return true
            }
            R.id.action_cart -> {
                return false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}