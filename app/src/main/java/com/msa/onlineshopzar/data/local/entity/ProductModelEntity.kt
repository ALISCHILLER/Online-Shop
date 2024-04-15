package com.msa.onlineshopzar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Product")
data class ProductModelEntity(
    @PrimaryKey
    val id: String,
    val convertFactor1: Int,
    val convertFactor2: Int,
    val fullNameKala1: String?,
    val fullNameKala2: String?,
    val productCode: Int,
    val productGroupCode: Int,
    val productName: String?,
    val unit1: String?,
    val unit2: String?,
    val unitid1: String?,
    val unitid2: String?,
    val salePrice: String,
    val productImage: String?,

)