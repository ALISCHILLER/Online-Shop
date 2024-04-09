package com.msa.onlineshopzar.data.model

import com.msa.onlineshopzar.data.remote.utils.MsaError

data class GeneralStateModel(
    val isLoading:Boolean = false,
    val error: String?=null,
)
