package com.msa.onlineshopzar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msa.onlineshopzar.data.local.dao.UserDao
import com.msa.onlineshopzar.data.local.entity.UserModelEntity


@Database(
    entities = [
        UserModelEntity::class
    ], version = 1 , exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}