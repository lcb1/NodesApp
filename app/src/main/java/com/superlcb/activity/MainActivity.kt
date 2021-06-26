package com.superlcb.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nodesapp.R
import com.superlcb.adapter.ItemAdapter
import com.superlcb.entity.ItemEntity
import com.superlcb.listener.ItemListener
import com.superlcb.util.AppUtil
import com.superlcb.viewmodel.MainViewModel
import java.sql.Timestamp

class MainActivity:AppCompatActivity() {


    private val inputTextView by lazy {
        findViewById<EditText>(R.id.input_view)
    }
    private val saveButtonView by lazy {
        findViewById<Button>(R.id.save_button_view)
    }
    private val recycleView by lazy {
        findViewById<RecyclerView>(R.id.item_recycle_view)
    }
    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG,"onCreate")


        setContentView(R.layout.main_layout)
        initView()
        observeData()
    }


    private fun getText():String{
        return inputTextView.text.toString()
    }
    private fun getTimeStamp():Long{
        return System.currentTimeMillis()
    }
    private fun getItemEntity():ItemEntity{
        return ItemEntity(getTimeStamp(),getText())
    }
    private fun onSaveButtonClicked(){

        viewModel.isContainer(getText())?.let {
            viewModel.deleteItemEntity(it)
        }
        saveData()

    }

    fun saveData(){
        val itemEntity=getItemEntity()
        viewModel.postItemEntity(itemEntity)
    }

    fun initView(){
        initRecycleView()
        initSaveButton()
        initInputView()
    }

    private fun initInputView(){
        inputTextView.setText(AppUtil.getTempText())
    }

    private fun initSaveButton(){
        saveButtonView.setOnClickListener {
            onSaveButtonClicked()
        }
    }

    private fun initRecycleView(){
        val itemList=AppUtil.getAllItemEntity()

        recycleView.layoutManager=LinearLayoutManager(this@MainActivity).apply {
            orientation=LinearLayoutManager.VERTICAL
        }
        viewModel.setRecycleViewAdapter(recycleView,ItemListenerImpl())
        viewModel.postItemEntityList(itemList)
    }
    private fun observeData(){
        viewModel.mldItemList.observe(this,{
            recycleView.adapter?.notifyDataSetChanged()
        })
    }


    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart")
    }
    companion object{
        private const val TAG="MAIN_ACTIVITY"
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun onPause() {
        super.onPause()
        AppUtil.saveTempText(getText())
        Log.d(TAG,"onPause")
    }

    override fun onResume() {
        super.onResume()
        AppUtil.saveTempText(getText())
        Log.d(TAG,"onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        AppUtil.saveTempText(getText())
        Log.d(TAG,"onDestroy")
    }



    inner class ItemListenerImpl:ItemListener{
        override fun onDeleteButtonClicked(itemEntity: ItemEntity) {
            viewModel.deleteItemEntity(itemEntity)
        }

        override fun onSimpleShowTextClicked(itemEntity: ItemEntity) {
            val nowText=getText()
            if(nowText!=itemEntity.text&&nowText.isNotEmpty()&&nowText.isNotBlank()){
                saveData()
            }
            inputTextView.setText(itemEntity.text)
            inputTextView.setSelection(itemEntity.text.length)
        }


    }


}