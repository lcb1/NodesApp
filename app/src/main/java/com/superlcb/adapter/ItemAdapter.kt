package com.superlcb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nodesapp.R
import com.superlcb.entity.ItemEntity
import com.superlcb.listener.ItemListener
import com.superlcb.viewholder.ItemViewHolder

class ItemAdapter(private val itemList:MutableList<ItemEntity>,private val listener:ItemListener?=null):RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.item_layout,parent,false)
        return ItemViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(itemList[position],position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}