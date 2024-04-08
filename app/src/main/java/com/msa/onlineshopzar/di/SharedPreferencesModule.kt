package com.msa.onlineshopzar.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object SharedPreferencesModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object SharedPreferencesModule {

        @Singleton
        @Provides
        fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences("your_preferences_name", Context.MODE_PRIVATE)
        }
    }
}