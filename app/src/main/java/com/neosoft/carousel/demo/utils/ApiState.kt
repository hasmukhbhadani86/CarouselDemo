package com.neosoft.carousel.demo.utils

import com.neosoft.carousel.demo.models.ViewPagerModel

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:List<ViewPagerModel>) : ApiState()
    object Empty : ApiState()
}
