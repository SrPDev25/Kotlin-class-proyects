package com.example.ortegadaniel.tool

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ortegadaniel.databinding.ItemProductoBinding.bind
import com.example.ortegadaniel.Control.Producto
import com.example.ortegadaniel.R


class ListenerAdapterProductos(private val productos: List<Producto>, private val cont: Context) :
    RecyclerView.Adapter<ListenerAdapterProductos.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = bind(view)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context =
            parent.context//    El context es una interfaz que nos da acceso a determinados recursos y clases del sistema
        val view = LayoutInflater.from(context)
            .inflate((R.layout.item_producto), parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val producto = productos.get(position)
            binding.denominacionTxt.text = producto.denominacion
            binding.precioTxt.text=producto.precio.toString()+"€"
            Glide.with(binding.root)
                .load(producto.imagen)
                .into(binding.image)
        }
    }

    override fun getItemCount(): Int {
        return productos.size
    }


}