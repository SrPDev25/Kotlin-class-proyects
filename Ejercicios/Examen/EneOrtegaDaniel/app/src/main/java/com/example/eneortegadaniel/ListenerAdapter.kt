package com.example.e6mdortegadaniel

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eneortegadaniel.R
import com.example.eneortegadaniel.control.Control
import com.example.eneortegadaniel.databinding.ItemPuntosRecyclerBinding.bind

class ListenerAdapter(private val control: Control):
    RecyclerView.Adapter<ListenerAdapter.ViewHolder>()
{
    private lateinit var context: Context
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = bind(view)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val view= LayoutInflater.from(context)
            .inflate((R.layout.item_puntos_recycler), parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            var punto=control.puntos.get(position)
            binding.tipoTxt.text=control.getEstablecimiento(punto.tipo)
            binding.denominacionTxt.text=punto.denominacion
            binding.direccionTxt.text=punto.direccion
            binding.provinciaTxt.text=punto.provincia
            var provincia=punto.provincia.uppercase()
            if (provincia=="BURGOS")
                binding.seccion.setBackgroundColor(Color.parseColor("#BFFFC9"))
            else
                binding.seccion.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    override fun getItemCount(): Int {
        return control.puntos.size
    }


}