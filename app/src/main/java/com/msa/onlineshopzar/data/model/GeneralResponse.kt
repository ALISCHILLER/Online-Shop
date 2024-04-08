package com.msa.onlineshopzar.data.model

data class GeneralResponse<T>(
    val hasError : Boolean,
    val message : String,
    val data: T?
)