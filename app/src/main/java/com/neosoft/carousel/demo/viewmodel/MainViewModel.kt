package com.neosoft.carousel.demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosoft.carousel.demo.repository.MainRepository
import com.neosoft.carousel.demo.utils.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _dataList: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val getData get() = _dataList

    init {
        getData()
    }

    private fun getData() {
        _dataList.value = ApiState.Loading
        viewModelScope.launch {
            mainRepository.getDataSet().catch { e ->
                _dataList.value = ApiState.Failure(e)
            }.collect { data ->
                _dataList.value = ApiState.Success(data)
            }
        }
    }
}