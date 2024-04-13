package com.msa.onlineshopzar.data.model

import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import com.msa.onlineshopzar.data.local.entity.UserModelEntity

data class ProductModel(
    override val hasError: Boolean,
    override val message: String,
    val data:List<ProductModelEntity>
):BaseResponseAbstractModel()
