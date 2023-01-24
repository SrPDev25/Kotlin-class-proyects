package com.example.ut7ej7ortegadaniel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e6mdortegadaniel.Events
import com.example.ut7ej7ortegadaniel.control.Alumno
import com.example.ut7ej7ortegadaniel.databinding.ItemRecyclerBinding.bind


class ListenerAdapterAlumnos(private val alumnos:List<Alumno>, private val listener: Events):
    RecyclerView.Adapter<ListenerAdapterAlumnos.ViewHolder>()
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
            .inflate((R.layout.item_recycler), parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
                var alumno=alumnos.get(position)
                binding.codigoTxt.text=alumno.codigo.toString()
                binding.nombreTxt.text = alumno.nombre
                //Inserta un set listener a cada uno de los "holders"
                setListener(alumno.codigo)//pasa la posicion del vehiculo en la lista


        }
    }

    override fun getItemCount(): Int {
        return alumnos.size
    }


}