package com.msa.onlineshopzar.di.remoteDi


import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.msa.onlineshopzar.data.remote.api.ApiService
import com.msa.onlineshopzar.utils.CompanionValues
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "http://10.252.37.207:8282"

private const val READ_TIMEOUT = 60L
private const val CONNECT_TIMEOUT = 60L
@Module
@InstallIn(SingletonComponent::class)
object Providers {

    @Singleton
    @Provides
    fun providesGson(@ApplicationContext context: Context): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideOkHttp(@ApplicationContext context: Context): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor(context))
            .build()
    }
    private fun interceptor(@ApplicationContext context: Context) = Interceptor { chain ->
        val sharedPreferences = context.getSharedPreferences("secret_shared_prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString(CompanionValues.TOKEN, "")

        val newRequest: Request = chain.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        chain.proceed(newRequest)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("secret_shared_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
