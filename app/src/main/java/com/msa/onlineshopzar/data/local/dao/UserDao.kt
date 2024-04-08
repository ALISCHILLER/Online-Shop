package com.msa.onlineshopzar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msa.onlineshopzar.data.local.entity.UserModelEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserLogin(userLoginEntity: UserModelEntity)

    @Query("DELETE FROM user")
    fun deleteUserLogin()
    @Query("SELECT * FROM user")
    fun getUserLogin():UserModelEntity
}