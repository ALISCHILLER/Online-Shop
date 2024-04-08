package com.msa.onlineshopzar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("user")
data class UserModelEntity(
    @PrimaryKey
    val customerCode:String,
    val customerName:String,
    val mobile:String,
    val brache:String,
    val city:String,
)
