package com.example.nodesapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Test

import org.junit.Assert.*
import java.io.File
import java.io.FileInputStream
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    companion object{
        private const val projectHomePath="/Users/admin/AndroidStudioProjects"
        private const val projectName="NodesApp"
        private const val rawDirPath="app/src/main/res/raw"
        private const val mockFileName="mock_data.properties"
        private const val mockFilepath="${projectHomePath}/${projectName}/${rawDirPath}/${mockFileName}"
    }

    fun String.readAll():String{
        val file= File(this)
        return if (file.exists()&&file.isFile){
            file.readText()
        }else{
            ""
         }
    }
    private val gson by lazy {
        GsonBuilder()
            .setPrettyPrinting()
            .create()
    }



    data class TestGsonEntity(val firstName:String?=null,val secondName:String?=null)

    @Test
    fun testReadAll(){
        println(mockFilepath.readAll())
    }
    @Test
    fun testTestGsonEntity(){
        println(TestGsonEntity())
    }

    private val prop by lazy {
        Properties().apply {
            load(FileInputStream(mockFilepath))
        }
    }



    @Test
    fun testProperties(){
        val prop=Properties()
        val mockInput=FileInputStream(mockFilepath)
        prop.load(mockInput)
        println(prop.getProperty("timestamp"))
    }


    @Test
    fun testGson(){
        val intArrayType= object :TypeToken<IntArray>(){}.type
        val intArrayJson=prop.getProperty("timestamp")
        val intArray=gson.fromJson<IntArray>(intArrayJson,intArrayType)
        intArray.forEach(::println)
    }

    @Test
    fun testTimeStamp(){
        println(System.currentTimeMillis())
    }

    fun Calendar.toHuman():String{
        val year=this.get(Calendar.YEAR)
        val month=this.get(Calendar.MONTH)+1
        val day=this.get(Calendar.DAY_OF_MONTH)
        val hour=this.get(Calendar.HOUR_OF_DAY)
        val min=this.get(Calendar.MINUTE)
        val second=this.get(Calendar.SECOND)
        return "${year}/${month}/${day} ${hour}:${min}:${second}"
    }

    @Test
    fun testDate(){
        val calendar=Calendar.getInstance()
        calendar.time= Date(1114101190905)
        println(calendar.toHuman())
    }


}