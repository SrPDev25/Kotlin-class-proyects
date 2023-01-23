package com.example.ut7ej7ortegadaniel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e6mdortegadaniel.Events

import com.example.ut7ej7ortegadaniel.control.Alumno
import com.example.ut7ej7ortegadaniel.control.Falta
import com.example.ut7ej7ortegadaniel.databinding.ItemFaltasBinding.bind


class ListenerAdapterFaltas(private val faltas:MutableList<Falta>, private val listener: Events):
    RecyclerView.Adapter<ListenerAdapterFaltas.ViewHolder>()
{
    private lateinit var context: Context
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = bind(view)
        fun setListener(codigo:Int){
            binding.root.setOnClickListener(){
                listener.shortClick(codigo)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context//    El context es una interfaz que nos da acceso a determinados recursos y clases del sistema
        val view= LayoutInflater.from(context)
            .inflate((R.layout.item_alumnos_recycler), parent, false)
        return  ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
                var falta=faltas.get(position)
            binding.horaTxt.text = falta.fecha+" ," +falta.hora



                if(falta.justificada==1){
                    binding.seccion.setBackgroundColor(Color.parseColor("#D1FFD6"))
                    binding.justificadaTxt.text="NO JUSTIFICADA"
                }else{
                    binding.seccion.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.justificadaTxt.text="JUSTIFICADA"
                }
                //Inserta un set listener a cada uno de los "holders"
                setListener(falta.codigo)//pasa la posicion del vehiculo en la lista


        }
    }

    override fun getItemCount(): Int {
        return faltas.size
    }


}