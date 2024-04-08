package com.msa.onlineshopzar.data.remote.repository

import com.msa.onlineshopzar.data.local.dao.UserDao
import com.msa.onlineshopzar.data.local.entity.UserModelEntity
import com.msa.onlineshopzar.data.model.TokenModel
import com.msa.onlineshopzar.data.model.UserModel
import com.msa.onlineshopzar.data.remote.api.ApiService
import com.msa.onlineshopzar.data.remote.utils.MakeSafeApiCall
import com.msa.onlineshopzar.data.remote.utils.Resource
import com.msa.onlineshopzar.data.request.TokenRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: ApiService,
    private val apiManager: MakeSafeApiCall,
    private val userDao: UserDao
) {

    suspend fun loginToken(
      tokenRequest: TokenRequest
    ): Flow<Resource<TokenModel?>> {
        return apiManager.makeSafeApiCall {
            withContext(Dispatchers.IO) {
                apiService.getToken(
                    tokenRequest
                )
            }
        }
    }

    suspend fun getUserData(
    ): Flow<Resource<UserModel?>> {
        return apiManager.makeSafeApiCall {
            withContext(Dispatchers.IO) {
                apiService.getUserData()

            }
        }
    }



    fun insertUser(user: UserModelEntity) {
        userDao.deleteUserLogin()
        userDao.insertUserLogin(user)
    }
}