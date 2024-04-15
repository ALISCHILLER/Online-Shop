package com.msa.onlineshopzar.data.remote.repository

import com.msa.onlineshopzar.data.local.dao.OrderDao
import com.msa.onlineshopzar.data.local.dao.ProductDao
import com.msa.onlineshopzar.data.local.dao.ProductGroupDao
import com.msa.onlineshopzar.data.local.dao.UserDao
import com.msa.onlineshopzar.data.local.entity.ProductGroupEntity
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import com.msa.onlineshopzar.data.local.entity.OrderEntity
import com.msa.onlineshopzar.data.local.entity.UserModelEntity
import com.msa.onlineshopzar.data.model.ProductGroupModel
import com.msa.onlineshopzar.data.model.ProductModel
import com.msa.onlineshopzar.data.remote.api.ApiService
import com.msa.onlineshopzar.data.remote.utils.MakeSafeApiCall
import com.msa.onlineshopzar.data.remote.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: ApiService,
    private val apiManager: MakeSafeApiCall,
    private val  productDao: ProductDao,
    private val productGroupDao: ProductGroupDao,
    private val orderDao: OrderDao,
    private val userDao: UserDao,

    )  {

    val getAllProduct: Flow<List<ProductModelEntity>> = productDao.getAll()
    val getuser: Flow<UserModelEntity> = userDao.getUserLogin()

    val getAllOrder: Flow<List<OrderEntity>> = orderDao.getAll()
    fun getProduct(code: Int): Flow<List<ProductModelEntity>> {
        return productDao.getProduct(code)
    }
    val getAllProductGroup: Flow<List<ProductGroupEntity>> = productGroupDao.getAll()
    suspend fun productRequest(
    ): Flow<Resource<ProductModel?>> {
        return apiManager.makeSafeApiCall {
            withContext(Dispatchers.IO) {
                apiService.getProductData()
            }
        } as Flow<Resource<ProductModel?>>
    }


    suspend fun insertProduct(productModelEntity: List<ProductModelEntity>){
        productDao.insert(productModelEntity)
    }

    suspend fun productGroupRequest(
    ): Flow<Resource<ProductGroupModel?>> {
        return apiManager.makeSafeApiCall {
            withContext(Dispatchers.IO) {
                apiService.getProductGroupData()
            }
        } as Flow<Resource<ProductGroupModel?>>
    }


    suspend fun insertProductGroup(productGroupEntity: List<ProductGroupEntity>){
        productGroupDao.insertZeroItem()
        productGroupDao.insert(productGroupEntity)
    }

    suspend fun insertOrder(orderEntity: OrderEntity){
        orderDao.insert(orderEntity)
    }

    suspend fun deleteOrder(orderId: String){
        orderDao.deleteOrder(orderId)
    }

     fun getProductCount(): Int {
        return productDao.getProductCount()
    }

    fun getOrder(code: Int): Flow<OrderEntity> {
        return orderDao.getOrder(code.toString())
    }

     fun searchProduct(search:String): Flow<List<ProductModelEntity>>{
         return productDao.searchProducts(search)
    }
}