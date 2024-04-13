package com.msa.onlineshopzar.data.model

import com.msa.onlineshopzar.data.local.entity.ProductModelEntity

data class ProductGroupModel(
    override val hasError: Boolean,
    override val message: String,
    val data:List<ProductModelEntity>

):BaseResponseAbstractModel()
