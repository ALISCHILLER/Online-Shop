package com.msa.onlineshopzar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import com.msa.onlineshopzar.data.local.entity.UserModelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductModel(productModelEntity: List<ProductModelEntity>)

    @Query("DELETE FROM product")
    fun deleteProductModel()

    @Query("SELECT * FROM product")
    fun getAllProductModel(): Flow<List<ProductModelEntity>>
}
