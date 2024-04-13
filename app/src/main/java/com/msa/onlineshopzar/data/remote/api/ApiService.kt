package com.msa.onlineshopzar.data.remote.api

import com.msa.onlineshopzar.data.local.entity.UserModelEntity
import com.msa.onlineshopzar.data.model.ProductGroupModel
import com.msa.onlineshopzar.data.model.ProductModel
import com.msa.onlineshopzar.data.model.TokenModel
import com.msa.onlineshopzar.data.model.UserModel
import com.msa.onlineshopzar.data.request.TokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {



//    @GET("user-data")
//    suspend fun fetchUserData(): Response<?>

    @POST("/api/v1/EshopUser/login-EshopUser")
    suspend fun getToken(
        @Body tokenRequest: TokenRequest
    ): Response<TokenModel?>

    @GET("/api/v1/EshopUser/Information-EshopUser")
    suspend fun getUserData(): Response<UserModel?>


    @GET("api/v1/EshopKala/Information-EshopKala")
    suspend fun getProductData(): Response<ProductModel?>

    @GET("/api/v1/EshopKala/Information-EshopKalaGroup")
    suspend fun getProductGroupData(): Response<ProductGroupModel?>
}