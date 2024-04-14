package com.msa.onlineshopzar.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msa.onlineshopzar.data.local.entity.ProductGroupEntity
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductGroupDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(productGroupEntity:List<ProductGroupEntity>)

    @Query("DELETE FROM ProductGroup")
    fun delete()


    @Query("SELECT * FROM ProductGroup")
    fun getAll(): Flow<List<ProductGroupEntity>>

    @Query("INSERT OR REPLACE INTO ProductGroup (productGroupCode, productGroup) VALUES (0, 'همه')")
    suspend fun insertZeroItem()

}