package com.example.e5mdortegamartnezdaniel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e5mdortegamartnezdaniel.databinding.ActivityStartBinding

class ListenerAdapter(private val listener:Events):
        RecyclerView.Adapter<ListenerAdapter.ViewHolder>() {

    private lateinit var context: Context//????????


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ActivityStartBinding.bind(view)//Inflate the activity
        fun setListener(){
            binding.nombre.setOnClickListener(){//Conecta la interface con la activity
                listener.shortKeyStroke()
            }
            binding.root.setOnLongClickListener(){
                listener.longKeyStroke()
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context//El context es una interfaz que nos da acceso a determinados recursos y clases del sistema
        val view=LayoutInflater.from(context)
            .inflate((R.layout.activity_start), parent, false)
        return  ViewHolder(view)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {

        }
    }


    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}