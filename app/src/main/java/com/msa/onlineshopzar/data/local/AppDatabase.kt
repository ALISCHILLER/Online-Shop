package com.msa.onlineshopzar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msa.onlineshopzar.data.local.dao.OrderDao
import com.msa.onlineshopzar.data.local.dao.ProductDao
import com.msa.onlineshopzar.data.local.dao.ProductGroupDao
import com.msa.onlineshopzar.data.local.dao.UserDao
import com.msa.onlineshopzar.data.local.entity.ProductGroupEntity
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import com.msa.onlineshopzar.data.local.entity.UserModelEntity


@Database(
    entities = [
        UserModelEntity::class,
        ProductModelEntity::class,
        ProductGroupEntity::class,
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun productGroupDao(): ProductGroupDao
    abstract fun orderDao(): OrderDao
}