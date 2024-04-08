package com.msa.onlineshopzar.di.db

import android.content.Context
import androidx.room.Room
import com.msa.onlineshopzar.data.local.AppDatabase
import com.msa.onlineshopzar.data.local.dao.UserDao
import com.msa.onlineshopzar.utils.CompanionValues
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {



    @Provides
    @Singleton
    fun providerRoleDao(appDatabase: AppDatabase) : UserDao {
        return appDatabase.userDao()
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            CompanionValues.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }
}