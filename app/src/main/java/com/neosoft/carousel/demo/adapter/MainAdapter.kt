package com.neosoft.carousel.demo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.neosoft.carousel.demo.R
import com.neosoft.carousel.demo.databinding.ListItemBinding
import com.neosoft.carousel.demo.models.ItemModel

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var listItems = arrayListOf<ItemModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(listItems: List<ItemModel>) {
        this.listItems = listItems as ArrayList<ItemModel>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = DataBindingUtil.inflate<ListItemBinding>(LayoutInflater.from(parent.context),R.layout.list_item,parent,false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listItems[position])

    }
    override fun getItemCount(): Int {
        return listItems.size
    }
}
class MainViewHolder(private val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(item: ItemModel){
        itemBinding.itemModel = item
        itemBinding.executePendingBindings()
    }
}

