package com.msa.onlineshopzar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msa.onlineshopzar.data.local.entity.OrderEntity
import com.msa.onlineshopzar.data.local.entity.ProductGroupEntity
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(order:OrderEntity)


    @Query("DELETE FROM `order`")
    fun delete()

    // حذف یک سفارش با استفاده از شناسه
    @Query("DELETE FROM `order` WHERE id = :orderId")
    fun deleteOrder(orderId: String)
    @Query("SELECT * FROM `order`")
    fun getAll(): Flow<List<OrderEntity>>

    @Query("SELECT * FROM `order` where id =:id")
    fun getOrder(id:String): Flow<OrderEntity>

}