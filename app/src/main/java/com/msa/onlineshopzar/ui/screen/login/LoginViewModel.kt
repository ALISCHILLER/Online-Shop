package com.msa.onlineshopzar.ui.screen.login

import android.content.ContentValues.TAG
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.msa.onlineshopzar.data.model.GeneralStateModel
import com.msa.onlineshopzar.data.remote.repository.LoginRepository
import com.msa.onlineshopzar.data.remote.utils.Resource
import com.msa.onlineshopzar.data.request.TokenRequest
import com.msa.onlineshopzar.ui.navigation.navgraph.NavInfo
import com.msa.onlineshopzar.ui.navigation.navgraph.NavManager
import com.msa.onlineshopzar.ui.navigation.navgraph.Route
import com.msa.onlineshopzar.utils.CompanionValues
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navManager: NavManager,
    private val loginRepository: LoginRepository
):ViewModel(){


    private val _loginState: MutableStateFlow<GeneralStateModel> = MutableStateFlow(GeneralStateModel())
    val loginState: StateFlow<GeneralStateModel> = _loginState
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
                        Timber.tag("LoginViewModel").d("getToken SUCCESS: ${response.data}  " )
                        val loginResponse = response.data
                        loginResponse?.let {
                            if (!it.hasError){
                                saveUserNameAndPassword(it.data)
                            }
                        }
                    }
                    Resource.Status.LOADING-> {
                        _loginState.update { it.copy(isLoading = true) }
                    }
                    Resource.Status.ERROR -> {
                        Timber.tag("LoginViewModel").d("getToken ERROR: ${response.error}" )
                        val loginResponse = response.data
                        loginResponse?.also {error ->
                                _loginState.update { it.copy(isLoading = false, error = error.message) }
                        }.let {
                            _loginState.update { it.copy(isLoading = false, error = response.error?.message) }
                        }
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
                        Timber.tag("LoginViewModel").d("getUserData SUCCESS: ${response.data}" )
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
                        Timber.tag("LoginViewModel").d("getUserData ERROR: ${response.error}" )
                    }
                    else -> {}
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