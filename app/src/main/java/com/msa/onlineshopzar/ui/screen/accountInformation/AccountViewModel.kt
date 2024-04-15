package com.msa.onlineshopzar.ui.screen.accountInformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msa.onlineshopzar.data.local.entity.UserModelEntity
import com.msa.onlineshopzar.data.remote.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AccountViewModel @Inject constructor(
    private val homeRepository: HomeRepository
):ViewModel(){

    init {
        getUser()
    }

    private val _user = MutableStateFlow<UserModelEntity?>(null)
    val user: StateFlow<UserModelEntity?> = _user
    fun getUser() {
        viewModelScope.launch {
            homeRepository.getuser.collect {
                _user.value = it
            }
        }
    }
}