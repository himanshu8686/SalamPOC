package com.salampoc.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Project Name :SalamPOC
 * Created By :Himanshu sharma on 13-04-2021
 * Package : com.salampoc.models
 */
@Parcelize
class HorizontalProductScrollModel(
    var productImage: Int?,
    var productTitle: String?,
    var productDescription: String?,
    var productPrice: String?
) : Parcelable