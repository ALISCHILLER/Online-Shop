package com.msa.onlineshopzar.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msa.onlineshopzar.data.local.entity.ProductGroupEntity
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import com.msa.onlineshopzar.data.model.GeneralStateModel
import com.msa.onlineshopzar.data.remote.repository.HomeRepository
import com.msa.onlineshopzar.data.remote.repository.LoginRepository
import com.msa.onlineshopzar.data.remote.utils.Resource
import com.msa.onlineshopzar.ui.navigation.navgraph.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navManager: NavManager,
    private val homeRepository: HomeRepository
):ViewModel(){
    private val _state: MutableStateFlow<GeneralStateModel> = MutableStateFlow(GeneralStateModel())
    val state: StateFlow<GeneralStateModel> = _state

    private val _allTasks =
        MutableStateFlow<List<ProductModelEntity>>(emptyList())
    val allTasks: StateFlow<List<ProductModelEntity>> = _allTasks

    private val _allProductGroup =
        MutableStateFlow<List<ProductGroupEntity>>(emptyList())
    val allProductGroup: StateFlow<List<ProductGroupEntity>> = _allProductGroup
    fun ProductRequest(){
        viewModelScope.launch {
            homeRepository.productRequest().onEach { response ->
                Timber.d(response.data.toString())
                when (response.status) {
                    Resource.Status.SUCCESS-> {
                        Timber.tag("HomeViewModel").d("getToken SUCCESS: ${response.data}  " )
                        val responseData = response.data
                        responseData?.let { data ->
                            if (!data.hasError){
                                homeRepository.insertProduct(data.data)
                                getAllProduct()
                            }else
                                updateStateError(data.message)
                        }
                    }
                    Resource.Status.LOADING-> {
                        updateStateLoading()
                    }
                    Resource.Status.ERROR -> {
                        Timber.tag("HomeViewModel").e("ProductRequest ERROR: ${response.error}" )
                        updateStateError(response.error?.message)
                    }

                }
            }.collect()
        }
    }

    fun ProductGroupRequest(){
        viewModelScope.launch {
            homeRepository.productGroupRequest().onEach { response ->
                Timber.d(response.data.toString())
                when (response.status) {
                    Resource.Status.SUCCESS-> {
                        Timber.tag("HomeViewModel").d("getToken SUCCESS: ${response.data}  " )
                        val responseData = response.data
                        responseData?.let { data ->
                            if (!data.hasError){
                                homeRepository.insertProductGroup(data.data)
                                getAllProductGroup()
                            }else
                                updateStateError(data.message)
                        }
                    }
                    Resource.Status.LOADING-> {
                        updateStateLoading()
                    }
                    Resource.Status.ERROR -> {
                        Timber.tag("HomeViewModel").e("ProductRequest ERROR: ${response.error}" )
                            updateStateError(response.error?.message)

                    }

                }
            }.collect()
        }
    }
    private fun getAllProduct() {
            viewModelScope.launch {
                delay(1000)
                homeRepository.getAllProduct.collect {
                    updateStateLoading(false)
                    _allTasks.value = it
                }
            }
        }

    fun getProduct(productGroup: ProductGroupEntity) {
        viewModelScope.launch {
            if (productGroup.productGroupCode != 0)
            homeRepository.getProduct(productGroup.productGroupCode).collect {
                _allTasks.value = it
            }
            else
                homeRepository.getAllProduct.collect {
                    updateStateLoading(false)
                    _allTasks.value = it
                }
        }
    }
    private fun getAllProductGroup() {
        viewModelScope.launch {
            delay(1000)
            homeRepository.getAllProductGroup.collect {
                updateStateLoading(false)
                _allProductGroup.value = it
            }
        }
    }


    private fun updateStateLoading() {
        _state.value = _state.value.copy(isLoading = true, error = null)
    }

    private fun updateStateLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading, error = null)
    }
    private fun updateStateError(errorMessage: String?) {
        _state.value = _state.value.copy(isLoading = false, error = errorMessage)
    }

}


