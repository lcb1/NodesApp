package com.superlcb.util

import android.content.Context
import com.google.gson.Gson
import com.superlcb.entity.ItemEntity


object StoreKey{
    const val TEMP_TEXT_KEY="temp_text_key"
    const val TIME_STAMP="time_stamp"
    const val PREFERENCE_KEY="preference_key"
    const val EMPTY_ARRAY="[]"
}


object AppUtil {

    lateinit var applicationContext:Context

    val gson by lazy {
        Gson()
    }

    private val defaultPreference by lazy {
        applicationContext.getSharedPreferences(StoreKey.PREFERENCE_KEY,Context.MODE_PRIVATE)
    }

    private fun saveTimeStamp(timeStamp:Long){
        val arrayList= getTimeStamps()
        arrayList.addFirst(timeStamp)
        defaultPreference.edit().apply{
            putString(StoreKey.TIME_STAMP,arrayList.toJson())
            apply()
        }
    }




    private fun getTimeStamps():ArrayList<Long>{
        val jsonArray=defaultPreference.getString(StoreKey.TIME_STAMP,null)?:StoreKey.EMPTY_ARRAY
        return jsonArray.toArrayList()
    }

    private fun saveSimpleShowText(timeStamp: Long,simpleText:String){
        defaultPreference.edit().apply{
            putString(timeStamp.toString(),simpleText)
            apply()
        }
    }


    fun saveItemEntity(itemEntity: ItemEntity){
        saveTimeStamp(itemEntity.timestamp)
        saveSimpleShowText(itemEntity.timestamp,itemEntity.text)
    }
    fun deleteItemEntity(itemEntity: ItemEntity){
        val arrayList= getTimeStamps()
        arrayList.remove(itemEntity.timestamp)
        val newArrayListJson=arrayList.toJson()
        defaultPreference.edit().apply {
            remove(itemEntity.timestamp.toString())
            putString(StoreKey.TIME_STAMP,newArrayListJson)
            apply()
        }


    }

    fun getAllItemEntity():ArrayList<ItemEntity>{
        return getTimeStamps().map {
            val text= defaultPreference.getString(it.toString(),null) as String
            ItemEntity(it,text)
        }.toMutableList() as ArrayList<ItemEntity>
    }
    fun saveTempText(tempText:String){
        defaultPreference.edit().apply{
            putString(StoreKey.TEMP_TEXT_KEY,tempText)
            apply()
        }
    }
    fun getTempText():String{
        return defaultPreference.getString(StoreKey.TEMP_TEXT_KEY,null)?:""
    }


























}