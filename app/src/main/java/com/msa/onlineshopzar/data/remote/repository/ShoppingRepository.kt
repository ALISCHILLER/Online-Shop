package com.msa.onlineshopzar.data.remote.repository

import com.msa.onlineshopzar.data.local.dao.OrderDao
import com.msa.onlineshopzar.data.local.entity.OrderEntity
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingRepository@Inject constructor(
    private val orderDao: OrderDao
) {


    val getAllOrder: Flow<List<OrderEntity>> = orderDao.getAll()
    fun getOrder(code: Int): Flow<OrderEntity> {
        return orderDao.getOrder(code.toString())
    }
}