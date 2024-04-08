package com.msa.onlineshopzar.data.model

import com.msa.onlineshopzar.data.local.entity.UserModelEntity

data class UserModel(
    override val hasError: Boolean,
    override val message: String,
    val data:List<UserModelEntity>
):BaseResponseAbstractModel()
