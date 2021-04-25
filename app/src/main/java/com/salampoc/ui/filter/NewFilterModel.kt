package com.salampoc.ui.filter


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class NewFilterModel : ArrayList<NewFilterModel.FilterModelItem>(), Parcelable {
    @Parcelize
    data class FilterModelItem(
        @SerializedName("filterParameterId")
        var filterParameterId: String?,
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
            @SerializedName("filterParameterId")
            var filterParameterId: String?,
            @SerializedName("filterValueId")
            var filterValueId: String?,
            @SerializedName("maxValue")
            var maxValue: String?,
            @SerializedName("minValue")
            var minValue: String?,
            @SerializedName("value")
            var value: String?
        ) : Parcelable
    }
}