package com.neosoft.carousel.demo.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.neosoft.carousel.demo.R
import com.neosoft.carousel.demo.adapter.MainAdapter
import com.neosoft.carousel.demo.adapter.ViewPagerAdapter
import com.neosoft.carousel.demo.databinding.ActivityMainBinding
import com.neosoft.carousel.demo.models.ItemModel
import com.neosoft.carousel.demo.models.ViewPagerModel
import com.neosoft.carousel.demo.repository.MainRepository
import com.neosoft.carousel.demo.utils.*
import com.neosoft.carousel.demo.viewmodel.MainViewModel
import com.neosoft.carousel.demo.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.name
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private var dataset = arrayListOf<ViewPagerModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(MainRepository()))[MainViewModel::class.java]

        initViewpager()
        initRecyclerview()
        myObserver()
        setViewPagerListener()
    }

    private fun initViewpager() {
        viewPagerAdapter = ViewPagerAdapter()
        binding.viewPager2.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = viewPagerAdapter
            addItemDecoration(HorizontalMarginItemDecoration(
                this@MainActivity,
                R.dimen.viewpager_current_item_horizontal_margin
            ))
            setCarouselEffects()

            //To display pager indicator
            TabLayoutMediator(binding.tbIndicator, binding.viewPager2) { _, _ ->

            }.attach()
        }

    }

    private fun initRecyclerview() {
        mainAdapter = MainAdapter()
        binding.rvItems.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
            isNestedScrollingEnabled = true
        }
    }

    private fun myObserver() {
        lifecycleScope.launchWhenStarted{
            mainViewModel.getData.collect{
                when(it)
                {
                    is ApiState.Loading->{
                        Toast.makeText(this@MainActivity,getString(R.string.str_loading),Toast.LENGTH_SHORT).show()
                    }
                    is ApiState.Success->{
                        dataset.addAll(it.data)
                        viewPagerAdapter.setItemList(dataset)
                        mainAdapter.setItemList(dataset[0].listItems)//Set First DataSet to the Item list
                        setDataToFilter(dataset[0].listItems)// set data list for the search
                    }
                    is ApiState.Failure->{
                        Toast.makeText(this@MainActivity,it.msg.toString(),Toast.LENGTH_LONG).show()
                    }
                    else -> {}
                }
            }
        }

    }

    private fun setViewPagerListener() {
        binding.viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d(TAG, "onPageSelected: $position")

                val listItemData = dataset[position].listItems
                showNoDataFound(listItemData)
                mainAdapter.setItemList(listItemData)
                //Set current dataset for filter.
                setDataToFilter(listItemData)
                //Clear search while pager swipe for next item.
                resetSearch()
            }
        })
    }

    @SuppressLint("DiscouragedApi")
    private fun setDataToFilter(alItems: List<ItemModel>) {
        val itemList = arrayListOf<ItemModel>()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(query: String): Boolean {
                itemList.clear()
                if (alItems.isNotEmpty()) {
                    val data = alItems.filter { item: ItemModel ->
                        item.name.lowercase().contains(query.lowercase())
                    }
                    itemList.addAll(data)
                }
                showNoDataFound(itemList)
                return false
            }

        })

        //Display close button on search bar
        val closeButtonId = resources.getIdentifier("android:id/search_close_btn", null, null)
        val closeButtonImage = binding.searchView.findViewById(closeButtonId) as ImageView
        closeButtonImage.setImageResource(R.drawable.ic_close)

        closeButtonImage.setOnClickListener {
            resetSearch()
            closeButtonImage.visibility = View.GONE
            mainAdapter.setItemList(alItems)
        }
    }

    private fun showNoDataFound(itemList: List<ItemModel>) {
        if (itemList.isEmpty()) {
            binding.tvNoData.toVisible()
            binding.rvItems.toGone()
        } else {
            binding.tvNoData.toGone()
            binding.rvItems.toVisible()
            mainAdapter.setItemList(itemList)
        }
    }

    private fun resetSearch() {
        binding.tvNoData.toGone()
        binding.rvItems.toVisible()
        binding.searchView.setQuery("", true)
        binding.searchView.clearFocus()
    }
}