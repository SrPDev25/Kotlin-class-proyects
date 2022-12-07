package com.example.e5mdortegamartnezdaniel

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e5mdortegamartnezdaniel.control.Especialidad
import com.example.e5mdortegamartnezdaniel.databinding.ItemEspecialidadesAltBinding.*
import com.example.e5mdortegamartnezdaniel.databinding.ActivityStartBinding

class ListenerAdapter(private val especialidades:List<Especialidad>, private val listener:Events):
        RecyclerView.Adapter<ListenerAdapter.ViewHolder>() {

    private lateinit var context: Context//????????


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = bind(view)
        fun setListener(codigo:Int){
            binding.root.setOnClickListener(){//Cuando se de clic a cualquier parte del View
                listener.shortKeyStroke(codigo)
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context//El context es una interfaz que nos da acceso a determinados recursos y clases del sistema
        val view=LayoutInflater.from(context)
            .inflate((R.layout.item_especialidades_alt), parent, false)
        return  ViewHolder(view)

    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            if (especialidades.get(position).numPlazasDisponibles!=0) {
                binding.tvName.text = especialidades.get(position).nombre
                binding.codigo.text = especialidades.get(position).codigo.toString()
                binding.nPlazas.text =
                    "Plazas: " + especialidades.get(position).numPlazasDisponibles.toString()
                //Inserta un set listener a cada uno de los "holders"
                setListener(especialidades.get(position).codigo)
            }
            //TODO eliminar el que tiene 0 empleados

        }
    }

    //La cantidad de items que se van a añadir
    override fun getItemCount() = especialidades.size


}