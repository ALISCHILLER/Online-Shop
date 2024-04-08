package com.msa.onlineshopzar.ui.screen.login

import android.content.ContentValues.TAG
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.msa.onlineshopzar.data.remote.repository.LoginRepository
import com.msa.onlineshopzar.data.remote.utils.Resource
import com.msa.onlineshopzar.data.request.TokenRequest
import com.msa.onlineshopzar.ui.navigation.navgraph.NavInfo
import com.msa.onlineshopzar.ui.navigation.navgraph.NavManager
import com.msa.onlineshopzar.ui.navigation.navgraph.Route
import com.msa.onlineshopzar.utils.CompanionValues
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navManager: NavManager,
    private val loginRepository: LoginRepository
):ViewModel(){

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    fun navigateToHome() {
        navManager.navigate(
            NavInfo(id = Route.HomeScreen.route,
                navOption = NavOptions.Builder().setPopUpTo(Route.LoginScreen.route,
                    inclusive = true).build())
        )
    }


    fun getToken(
        username:String,
        password:String
    ){
        viewModelScope.launch {
            loginRepository.loginToken(
                TokenRequest(username,password)
            ).onEach { response ->
                Timber.d(response.data.toString())
                when (response.status) {
                    Resource.Status.SUCCESS-> {
                        Log.d("LoginViewModel", "getToken SUCCESS:${response.data} ")
                        val loginResponse = response.data
                        loginResponse?.let {
                            if (!it.hasError){
                                saveUserNameAndPassword(it.data)
                            }
                        }
                    }
                    Resource.Status.LOADING-> {

                    }
                    Resource.Status.ERROR -> {
                        Log.d("LoginViewModel" , "getToken ERROR:${response.error} ")

                    }
                    else -> {}
                }
            }.collect()
        }
    }

    fun getUserData(){
        viewModelScope.launch {
            loginRepository.getUserData().onEach { response ->
                Timber.d(response.data.toString())
                when (response.status) {
                    Resource.Status.SUCCESS-> {
                        Log.d("LoginViewModel", "getUserData SUCCESS:${response.data} ")
                        val loginResponse = response.data
                        loginResponse?.let {
                            if (!it.hasError){
                                loginRepository.insertUser(it.data.get(0))
                                navigateToHome()
                            }
                        }
                    }
                    Resource.Status.LOADING-> {}
                    Resource.Status.ERROR -> {
                        Log.d("LoginViewModel" , "getUserData ERROR:${response.error} ")
                    }
                    else -> {
                        Log.d("LoginViewModel" , "getUserData else:$response ")
                    }
                }
            }.collect()
        }
    }


    private suspend fun saveUserNameAndPassword(token: String?) {
        sharedPreferences
            .edit()
            .putString(CompanionValues.TOKEN, token)
            .apply()

        getUserData()
    }
}