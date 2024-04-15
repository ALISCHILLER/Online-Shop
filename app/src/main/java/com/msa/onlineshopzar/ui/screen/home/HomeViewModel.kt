package com.msa.onlineshopzar.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msa.onlineshopzar.data.local.entity.ProductGroupEntity
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import com.msa.onlineshopzar.data.local.entity.OrderEntity
import com.msa.onlineshopzar.data.local.entity.UserModelEntity
import com.msa.onlineshopzar.data.model.GeneralStateModel
import com.msa.onlineshopzar.data.remote.repository.HomeRepository
import com.msa.onlineshopzar.data.remote.utils.Resource
import com.msa.onlineshopzar.ui.navigation.navgraph.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navManager: NavManager,
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _state: MutableStateFlow<GeneralStateModel> = MutableStateFlow(GeneralStateModel())
    val state: StateFlow<GeneralStateModel> = _state

    private val _allTasks =
        MutableStateFlow<List<ProductModelEntity>>(emptyList())
    val allTasks: StateFlow<List<ProductModelEntity>> = _allTasks

    private val _allOrder =
        MutableStateFlow<List<OrderEntity>>(emptyList())
    val allOrder: StateFlow<List<OrderEntity>> = _allOrder


    private val _allProductGroup =
        MutableStateFlow<List<ProductGroupEntity>>(emptyList())
    val allProductGroup: StateFlow<List<ProductGroupEntity>> = _allProductGroup


    init {
        getUser()
    }
    private val _user = MutableStateFlow<UserModelEntity?>(null)
    val user: StateFlow<UserModelEntity?> = _user

     fun productCheck(){
        val productCount = homeRepository.getProductCount()
        if (productCount==0) {
            ProductRequest()
            ProductGroupRequest()
        }else{
            getAllAllOrder()
            getAllProduct()
        }
    }
    fun ProductRequest() {
        viewModelScope.launch {
            homeRepository.productRequest().onEach { response ->
                Timber.d(response.data.toString())
                when (response.status) {
                    Resource.Status.SUCCESS -> {
                        Timber.tag("HomeViewModel").d("getToken SUCCESS: ${response.data}  ")
                        val responseData = response.data
                        responseData?.let { data ->
                            if (!data.hasError) {
                                homeRepository.insertProduct(data.data)
                                getAllProduct()
                            } else
                                updateStateError(data.message)
                        }
                    }

                    Resource.Status.LOADING -> {
                        updateStateLoading()
                    }

                    Resource.Status.ERROR -> {
                        Timber.tag("HomeViewModel").e("ProductRequest ERROR: ${response.error}")
                        updateStateError(response.error?.message)
                    }

                }
            }.collect()
        }
    }
    private fun getAllProduct() {
        viewModelScope.launch {
            homeRepository.getAllProduct.collect {
                updateStateLoading(false)
                _allTasks.value = it
            }
        }
    }

    private fun getAllAllOrder() {
        viewModelScope.launch {
            homeRepository.getAllOrder.collect {
                _allOrder.value = it
            }
        }
    }
    fun ProductGroupRequest() {
        viewModelScope.launch {

            homeRepository.productGroupRequest().onEach { response ->
                Timber.d(response.data.toString())
                when (response.status) {
                    Resource.Status.SUCCESS -> {
                        Timber.tag("HomeViewModel").d("getToken SUCCESS: ${response.data}  ")
                        val responseData = response.data
                        responseData?.let { data ->
                            if (!data.hasError) {
                                homeRepository.insertProductGroup(data.data)
                                getAllProductGroup()
                            } else
                                updateStateError(data.message)
                        }
                    }

                    Resource.Status.LOADING -> {
                        updateStateLoading()
                    }

                    Resource.Status.ERROR -> {
                        Timber.tag("HomeViewModel").e("ProductRequest ERROR: ${response.error}")
                        updateStateError(response.error?.message)

                    }

                }
            }.collect()
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


    fun getUser() {
        viewModelScope.launch {
                homeRepository.getuser.collect {
                    _user.value = it
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

    fun searchProduct(search:String){
        viewModelScope.launch {
            homeRepository.searchProduct(search).collect{
                _allTasks.value = it
            }
        }
    }
    fun getOrder(code: Int): OrderEntity? {
        var order: OrderEntity? =null
        viewModelScope.launch {
            homeRepository.getOrder(code).collect{
               order = it
            }
        }
        return order
    }
    fun calculateTotalPrice(
        value1: Int,
        value2: Int,
        productModelEntity: ProductModelEntity
    ): Float {
        val valueNumber = (value2 * productModelEntity.convertFactor2) + value1
        val salePrice = valueNumber * productModelEntity.salePrice.toFloat()
       if (valueNumber >0) {
           viewModelScope.launch {
               homeRepository.insertOrder(
                   OrderEntity(
                       id = productModelEntity.id,
                       convertFactor1 = productModelEntity.convertFactor1,
                       convertFactor2 = productModelEntity.convertFactor2,
                       fullNameKala1 = productModelEntity.fullNameKala1,
                       fullNameKala2 = productModelEntity.fullNameKala2,
                       productCode = productModelEntity.productCode,
                       productGroupCode = productModelEntity.productGroupCode,
                       productName = productModelEntity.productName,
                       unit1 = productModelEntity.unit1,
                       unit2 = productModelEntity.unit2,
                       unitid1 = productModelEntity.unitid1,
                       unitid2 = productModelEntity.unitid2,
                       salePrice = productModelEntity.salePrice,
                       productImage = productModelEntity.productImage,
                       numberOrder = valueNumber,
                       numberOrder1 = value1,
                       numberOrder2 = value2,
                       unitOrder = productModelEntity.unit1,
                   )
               )
           }
       }else{
           viewModelScope.launch {
               homeRepository.deleteOrder(productModelEntity.id)
           }
       }

        return salePrice

    }
}


