package com.neosoft.carousel.demo.repository

import com.neosoft.carousel.demo.R
import com.neosoft.carousel.demo.models.ItemModel
import com.neosoft.carousel.demo.models.ViewPagerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

open class MainRepository  {

    fun getDataSet() : Flow<List<ViewPagerModel>> = flow{
        emit(arrayListOf(
            ViewPagerModel(R.drawable.img1,arrayListOf(
                    ItemModel("Item 1", R.drawable.ic_img1),
                    ItemModel("Item 2", R.drawable.ic_img1),
                    ItemModel("Item 3", R.drawable.ic_img1),
                    ItemModel("Item 4", R.drawable.ic_img1),
                    ItemModel("Item 5", R.drawable.ic_img1),
                    ItemModel("Item 6", R.drawable.ic_img1),
                    ItemModel("Item 7", R.drawable.ic_img1),
                    ItemModel("Item 8", R.drawable.ic_img1),
                    ItemModel("Item 9", R.drawable.ic_img1),
                    ItemModel("Item 10", R.drawable.ic_img1)
                )),
            ViewPagerModel(R.drawable.img2,arrayListOf(
                ItemModel("Item 11", R.drawable.ic_img2),
                ItemModel("Item 12", R.drawable.ic_img2),
                ItemModel("Item 13", R.drawable.ic_img2),
                ItemModel("Item 14", R.drawable.ic_img2),
                ItemModel("Item 15", R.drawable.ic_img2),
                ItemModel("Item 16", R.drawable.ic_img2),
                ItemModel("Item 17", R.drawable.ic_img2),
                ItemModel("Item 18", R.drawable.ic_img2),
                ItemModel("Item 19", R.drawable.ic_img2),
                ItemModel("Item 20", R.drawable.ic_img2)
            )),
            ViewPagerModel(R.drawable.img3,arrayListOf(
                ItemModel("Item 21", R.drawable.ic_img3),
                ItemModel("Item 22", R.drawable.ic_img3),
                ItemModel("Item 23", R.drawable.ic_img3),
                ItemModel("Item 24", R.drawable.ic_img3),
                ItemModel("Item 25", R.drawable.ic_img3),
                ItemModel("Item 26", R.drawable.ic_img3),
                ItemModel("Item 27", R.drawable.ic_img3),
                ItemModel("Item 28", R.drawable.ic_img3),
                ItemModel("Item 29", R.drawable.ic_img3),
                ItemModel("Item 30", R.drawable.ic_img3)
            )),
            ViewPagerModel(R.drawable.img1,arrayListOf(
                ItemModel("Item 31", R.drawable.ic_img4),
                ItemModel("Item 32", R.drawable.ic_img4),
                ItemModel("Item 33", R.drawable.ic_img4),
                ItemModel("Item 34", R.drawable.ic_img4),
                ItemModel("Item 35", R.drawable.ic_img4),
                ItemModel("Item 36", R.drawable.ic_img4),
                ItemModel("Item 37", R.drawable.ic_img4),
                ItemModel("Item 38", R.drawable.ic_img4),
                ItemModel("Item 39", R.drawable.ic_img4),
                ItemModel("Item 40", R.drawable.ic_img4)
            )),
            ViewPagerModel(R.drawable.img5,arrayListOf(
                ItemModel("Item 41", R.drawable.ic_img5),
                ItemModel("Item 42", R.drawable.ic_img5),
                ItemModel("Item 43", R.drawable.ic_img5),
                ItemModel("Item 44", R.drawable.ic_img5),
                ItemModel("Item 45", R.drawable.ic_img5),
                ItemModel("Item 46", R.drawable.ic_img5),
                ItemModel("Item 47", R.drawable.ic_img5),
                ItemModel("Item 48", R.drawable.ic_img5),
                ItemModel("Item 49", R.drawable.ic_img5),
                ItemModel("Item 50", R.drawable.ic_img5)
            )),
        ))
    }.flowOn(Dispatchers.IO)
}