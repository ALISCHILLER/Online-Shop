package com.msa.onlineshopzar.data.remote.repository

import com.msa.onlineshopzar.data.local.dao.ProductDao
import com.msa.onlineshopzar.data.local.dao.UserDao
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import com.msa.onlineshopzar.data.model.ProductGroup
import com.msa.onlineshopzar.data.model.ProductGroupModel
import com.msa.onlineshopzar.data.model.ProductModel
import com.msa.onlineshopzar.data.model.TokenModel
import com.msa.onlineshopzar.data.remote.api.ApiService
import com.msa.onlineshopzar.data.remote.utils.MakeSafeApiCall
import com.msa.onlineshopzar.data.remote.utils.Resource
import com.msa.onlineshopzar.data.request.TokenRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: ApiService,
    private val apiManager: MakeSafeApiCall,
    private val  productDao: ProductDao
)  {

    val getAllProduct: Flow<List<ProductModelEntity>> = productDao.getAllProductModel()
    suspend fun productRequest(
    ): Flow<Resource<ProductModel?>> {
        return apiManager.makeSafeApiCall {
            withContext(Dispatchers.IO) {
                apiService.getProductData()
            }
        } as Flow<Resource<ProductModel?>>
    }


    suspend fun insertProduct(productModelEntity: List<ProductModelEntity>){
        productDao.insertProductModel(productModelEntity)
    }

    suspend fun productGroupRequest(
    ): Flow<Resource<ProductGroupModel?>> {
        return apiManager.makeSafeApiCall {
            withContext(Dispatchers.IO) {
                apiService.getProductGroupData()
            }
        } as Flow<Resource<ProductGroupModel?>>
    }
}