package com.neosoft.carousel.demo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.neosoft.carousel.demo.R
import com.neosoft.carousel.demo.databinding.ListItemBinding
import com.neosoft.carousel.demo.databinding.ViewpageritemBinding
import com.neosoft.carousel.demo.models.ItemModel
import com.neosoft.carousel.demo.models.ViewPagerModel

class ViewPagerAdapter : RecyclerView.Adapter<SliderViewHolder>() {

    private var listItems = arrayListOf<ViewPagerModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(listItems: List<ViewPagerModel>) {
        this.listItems = listItems as ArrayList<ViewPagerModel>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = DataBindingUtil.inflate<ViewpageritemBinding>(LayoutInflater.from(parent.context),R.layout.viewpageritem,parent,false)
        return SliderViewHolder(binding)
    }
    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(listItems[position])

    }
    override fun getItemCount(): Int {
        return listItems.size
    }
}
class SliderViewHolder(private val itemBinding: ViewpageritemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(item: ViewPagerModel){
        itemBinding.viewPagerModel = item
        itemBinding.executePendingBindings()
    }
}

