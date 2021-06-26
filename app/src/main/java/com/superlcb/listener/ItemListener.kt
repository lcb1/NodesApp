package com.superlcb.listener

import com.superlcb.entity.ItemEntity

interface ItemListener {


    fun onDeleteButtonClicked(itemEntity: ItemEntity)=Unit
    fun onSimpleShowTextClicked(itemEntity: ItemEntity) = Unit




}