package com.msa.onlineshopzar.data.model

data class TokenModel(
    override val hasError: Boolean,
    override val message: String,
    val data: String?
) : BaseResponseAbstractModel()