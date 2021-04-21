package com.salampoc.customUI

import android.content.Context
import android.os.Parcelable
import androidx.appcompat.widget.AppCompatCheckBox
import com.salampoc.models.HomePageModel
import kotlinx.parcelize.Parcelize

/**
 * Project Name :SalamPOC
 * Created By :Himanshu sharma on 19-04-2021
 * Package : com.salampoc.customUI
 */
class FilterCheckBox(context: Context?) : AppCompatCheckBox(context!!) {

    //    private var filterId:String? = null
//    private var value : String? = null
//
//
//    fun setFilterCheckBoxData(filterId:String?, value:String?) {
//        this.filterId = filterId
//        this.value = value
//    }
//
//    fun getFilterId() : String? {
//        return this.filterId
//    }
//
//    fun getFilterValue():String?{
//        return this.value
//    }
    @Parcelize
    data class FilterCheckBoxData(
        var filterId: String?,
        var value: String?
    ) : Parcelable
}