package com.salampoc.ui.productdetails

/**
 * Project Name :SalamPOC
 * Created By :Himanshu sharma on 21-04-2021
 * Package : com.salampoc.ui.productdetails
 */
class ProductSpecificationModel {
    private var type: Int

    /**
     * specification title
     */
    private var title: String? = null
    fun getType(): Int {
        return type
    }

    fun setType(type: Int) {
        this.type = type
    }

    constructor(type: Int, title: String?) {
        this.type = type
        this.title = title
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }
    /** specification title  */
    /*** specification body  */
    private var featureName: String? = null
    private var featureValue: String? = null

    constructor(type: Int, featureName: String?, featureValue: String?) {
        this.type = type
        this.featureName = featureName
        this.featureValue = featureValue
    }

    fun getFeatureName(): String? {
        return featureName
    }

    fun setFeatureName(featureName: String?) {
        this.featureName = featureName
    }

    fun getFeatureValue(): String? {
        return featureValue
    }

    fun setFeatureValue(featureValue: String?) {
        this.featureValue = featureValue
    }

    /*** specification body  */
    companion object {
        const val SPECIFICATION_TITLE = 0
        const val SPECIFICATION_BODY = 1
    }
}
