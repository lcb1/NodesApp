package com.superlcb.viewmodel

import android.content.ClipData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.superlcb.adapter.ItemAdapter
import com.superlcb.entity.ItemEntity
import com.superlcb.listener.ItemListener
import com.superlcb.util.AppUtil
import com.superlcb.util.addFirst

class MainViewModel:ViewModel() {

    private val itemList:ArrayList<ItemEntity> = ArrayList()
    val mldItemList :MutableLiveData<ArrayList<ItemEntity>> by lazy {
        MutableLiveData()
    }

    fun getItemByPosition(position:Int):ItemEntity{
        return itemList[position]
    }
    fun isContainer(text:String):ItemEntity?{
        return itemList.find { it.text==text }
    }


    fun postItemEntity(itemEntity: ItemEntity){
        AppUtil.saveItemEntity(itemEntity)
        itemList.addFirst(itemEntity)
        notifyDataChanged()
    }


    fun deleteItemEntity(itemEntity: ItemEntity){
        AppUtil.deleteItemEntity(itemEntity)
        itemList.remove(itemEntity)
        notifyDataChanged()
    }


    private fun notifyDataChanged(){
        mldItemList.postValue(itemList)
    }

    fun postItemEntityList(itemEntityList:ArrayList<ItemEntity>){
        itemList.addAll(itemEntityList)
        notifyDataChanged()
    }

    fun setRecycleViewAdapter(recyclerView: RecyclerView,listener: ItemListener?=null){
        recyclerView.adapter=ItemAdapter(itemList,listener)
    }

}