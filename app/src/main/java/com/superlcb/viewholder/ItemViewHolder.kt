package com.superlcb.viewholder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nodesapp.R
import com.superlcb.entity.ItemEntity
import com.superlcb.listener.ItemListener
import com.superlcb.util.toHuman

class ItemViewHolder(private val view:View,private val listener: ItemListener?=null): RecyclerView.ViewHolder(view) {
    var position:Int?=null
    var itemEntity:ItemEntity?=null
    val timeStampTextView by lazy {
        view.findViewById<TextView>(R.id.timestamp_text_view)
    }
    val simpleShowTextView by lazy {
        view.findViewById<TextView>(R.id.simple_show_text_view).apply {
            setOnClickListener {
                listener?.let {  listener->
                    itemEntity?.let { itemEntity ->
                        listener.onSimpleShowTextClicked(itemEntity)
                    }
                }
            }
        }
    }

    val deleteButtonView by lazy {
        val btn=view.findViewById<Button>(R.id.text_delete_button_view)
        btn?.setOnClickListener {
            itemEntity?.let { itemEntity->
                listener?.onDeleteButtonClicked(itemEntity)
            }
        }
        btn
    }



    fun  bindData(itemEntity: ItemEntity,position:Int){
        timeStampTextView.text=itemEntity.timestamp.toHuman()
        simpleShowTextView.text=itemEntity.text
        deleteButtonView
        this.position=position
        this.itemEntity=itemEntity
    }






}