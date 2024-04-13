package com.msa.onlineshopzar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("ProductGroup")
data class ProductGroupEntity(
    @PrimaryKey
    val productGroupCode: Int,
    val productGroup: String,

)