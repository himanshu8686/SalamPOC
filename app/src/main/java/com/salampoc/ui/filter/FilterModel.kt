package com.salampoc.ui.filter


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class FilterModel : ArrayList<FilterModel.FilterModelItem>(), Parcelable {

    @Parcelize
    data class FilterModelItem(
        @SerializedName("filterId")
        var filterId: String?,
        @SerializedName("filterTitle")
        var filterTitle: String?,
        @SerializedName("items")
        var items: List<Item?>?,
        @SerializedName("type")
        var type: String?,
        @SerializedName("typeId")
        var typeId: String?
    ) : Parcelable {
        @Parcelize
        data class Item(
            @SerializedName("filterId")
            var filterId: String?,
            @SerializedName("value")
            var value: String?
        ) : Parcelable
    }
}