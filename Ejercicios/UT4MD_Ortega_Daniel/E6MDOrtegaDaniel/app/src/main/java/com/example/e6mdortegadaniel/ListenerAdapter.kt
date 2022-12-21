package com.example.e6mdortegadaniel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e6mdortegadaniel.control.Vehiculo
import com.example.e6mdortegadaniel.databinding.ItemCochesRecyclerBinding.bind

class ListenerAdapter(private val vehiculos:List<Vehiculo>, private val listener: Events):
    RecyclerView.Adapter<ListenerAdapter.ViewHolder>()
{
    private lateinit var context: Context
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = bind(view)
        fun setListener(codigo:Int){
            binding.root.setOnLongClickListener(){//Cuando se de clic a cualquier parte del View
                listener.longClick(codigo)
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context//El context es una interfaz que nos da acceso a determinados recursos y clases del sistema
        val view= LayoutInflater.from(context)
            .inflate((R.layout.item_coches_recycler), parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {

                binding.matriculaTxt.text=vehiculos.get(position).matricula.toString()
                binding.modeloTxt.text = vehiculos.get(position).modelo.toString()
                binding.dniTxt.text=vehiculos.get(position).dni.toString()

                //Inserta un set listener a cada uno de los "holders"
                setListener(position)//pasa la posicion del vehiculo en la lista


        }
    }

    override fun getItemCount(): Int {
        return vehiculos.size
    }
}