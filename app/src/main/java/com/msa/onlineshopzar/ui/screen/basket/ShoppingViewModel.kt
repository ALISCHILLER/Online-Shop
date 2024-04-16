package com.msa.onlineshopzar.ui.screen.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msa.onlineshopzar.data.local.entity.OrderEntity
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import com.msa.onlineshopzar.data.remote.repository.HomeRepository
import com.msa.onlineshopzar.data.remote.repository.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: ShoppingRepository,
    private val homeRepository: HomeRepository
):ViewModel(){

    private val _allOrder=
        MutableStateFlow<List<OrderEntity>>(emptyList())
    val allOrder: StateFlow<List<OrderEntity>> = _allOrder

    init {
        getAllOrder()
    }
     fun getAllOrder() {
        viewModelScope.launch {
            repository.getAllOrder.collect {
                _allOrder.value = it
            }
        }
    }


    fun deleteOrder(orderId: String){
        viewModelScope.launch {
            homeRepository.deleteOrder(orderId)
        }
    }
    fun calculateTotalPrice(
        value1: Int,
        value2: Int,
        orderEntity: OrderEntity
    ): Float {
        val valueNumber = (value2 * orderEntity.convertFactor2) + value1
        val salePrice = valueNumber * orderEntity.salePrice.toFloat()
        if (valueNumber >0) {
            viewModelScope.launch {
                homeRepository.insertOrder(
                    OrderEntity(
                        id = orderEntity.id,
                        convertFactor1 = orderEntity.convertFactor1,
                        convertFactor2 = orderEntity.convertFactor2,
                        fullNameKala1 = orderEntity.fullNameKala1,
                        fullNameKala2 = orderEntity.fullNameKala2,
                        productCode = orderEntity.productCode,
                        productGroupCode = orderEntity.productGroupCode,
                        productName = orderEntity.productName,
                        unit1 = orderEntity.unit1,
                        unit2 = orderEntity.unit2,
                        unitid1 = orderEntity.unitid1,
                        unitid2 = orderEntity.unitid2,
                        salePrice = orderEntity.salePrice,
                        productImage = orderEntity.productImage,
                        numberOrder = valueNumber,
                        numberOrder1 = value1,
                        numberOrder2 = value2,
                        unitOrder = orderEntity.unit1,
                    )
                )
            }
        }else{
            viewModelScope.launch {
                homeRepository.deleteOrder(orderEntity.id)
            }
        }

        return salePrice

    }
}