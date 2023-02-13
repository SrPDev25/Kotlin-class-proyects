package com.example.ortegadaniel.tool

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ortegadaniel.databinding.ItemCategoriaBinding.bind
import com.example.ortegadaniel.Control.Categoria
import com.example.ortegadaniel.R


class ListenerAdapterCards(private val categorias: List<Categoria>, private val cont: Context, private val listener: Events) :
    RecyclerView.Adapter<ListenerAdapterCards.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = bind(view)
        fun setListener(codigo: Int) {
            binding.root.setOnClickListener() {
                listener.shortClick(codigo)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context =
            parent.context//    El context es una interfaz que nos da acceso a determinados recursos y clases del sistema
        val view = LayoutInflater.from(context)
            .inflate((R.layout.item_categoria), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val categoria = categorias.get(position)
            binding.denominacionTxt.text = categoria.denominacion.toString()
            Glide.with(binding.root)
                .load(categoria.imagen)
                .into(binding.image)
            //Inserta un set listener a cada uno de los "holders"
            setListener(categoria.codigo)
        }
    }

    override fun getItemCount(): Int {
        return categorias.size
    }


}