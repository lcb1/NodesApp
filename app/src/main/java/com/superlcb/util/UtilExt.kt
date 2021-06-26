package com.superlcb.util

import com.google.gson.reflect.TypeToken
import com.superlcb.entity.ItemEntity
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

class UtilExt {
}

fun Long.toHuman():String{
    val calendar=Calendar.getInstance()
    calendar.time=Date(this)
    val year=calendar.get(Calendar.YEAR)
    val month=calendar.get(Calendar.MONTH)+1
    val day=calendar.get(Calendar.DAY_OF_MONTH)
    val hour=calendar.get(Calendar.HOUR_OF_DAY)
    val minute=calendar.get(Calendar.MINUTE)
    val second=calendar.get(Calendar.SECOND)
    return "${year}/${month}/${day} ${hour}:${minute}:${second}"
}
fun String.toItemEntity():ItemEntity{
    val itemEntityType=object : TypeToken<ItemEntity>(){}.type
    return AppUtil.gson.fromJson(this,itemEntityType)
}

fun Any.toJson():String{
    return AppUtil.gson.toJson(this)
}

fun String.toArrayList():ArrayList<Long>{
    val arrayListType=object :TypeToken<ArrayList<Long>>(){}.type
    return AppUtil.gson.fromJson(this,arrayListType)
}


fun<T> ArrayList<T>.addFirst(t:T){
    reverse()
    add(t)
    reverse()
}