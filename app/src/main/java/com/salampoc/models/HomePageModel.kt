package com.salampoc.models

/**
 * Project Name :SalamPOC
 * Created By :Himanshu sharma on 13-04-2021
 * Package : com.salampoc.models
 */
class HomePageModel {
    private var type: Int
    private var homePageModelList: List<HomePageModel>? = null

    companion object {
        const val HORIZONTAL_PRODUCT_VIEW = 1
        const val GRID_PRODUCT_VIEW = 2
    }

    /***** home page model starts */
    constructor(type: Int, homePageModelList: List<HomePageModel>?) {
        this.type = type
        this.homePageModelList = homePageModelList
    }

    fun getType(): Int {
        return type
    }

    fun setType(type: Int) {
        this.type = type
    }

    fun getHomePageModelList(): List<HomePageModel>? {
        return homePageModelList
    }

    fun setHomePageModelList(homePageModelList: List<HomePageModel>?) {
        this.homePageModelList = homePageModelList
    }

    /***** home page model  ends*/


    /******* horizontal & grid layout starts  */

    private var horizontalProductScrollModelList: List<HorizontalProductScrollModel>? = null
    private var title: String? = null

    constructor(
        type: Int,
        title: String?,
        horizontalProductScrollModelList: List<HorizontalProductScrollModel>?
    ) {
        this.type = type
        this.title = title
        this.horizontalProductScrollModelList = horizontalProductScrollModelList
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getHorizontalProductScrollModelList(): List<HorizontalProductScrollModel>? {
        return horizontalProductScrollModelList
    }

    fun setHorizontalProductScrollModelList(horizontalProductScrollModelList: List<HorizontalProductScrollModel>?) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList
    }

    /******* horizontal & grid layout ends  */

}
