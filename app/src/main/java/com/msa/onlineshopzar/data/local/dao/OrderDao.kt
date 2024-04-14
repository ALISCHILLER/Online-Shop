package com.msa.onlineshopzar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import com.msa.onlineshopzar.data.local.entity.TemporaryOrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(productModelEntity: TemporaryOrderEntity)

    @Query("DELETE FROM temporaryOrder")
    fun delete()

    @Query("SELECT * FROM temporaryOrder")
    fun getAll(): Flow<List<TemporaryOrderEntity>>
}