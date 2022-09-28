package com.neosoft.carousel.demo.models

import androidx.annotation.DrawableRes

data class ViewPagerModel(@DrawableRes val imageUrl: Int, val listItems:List<ItemModel>)
