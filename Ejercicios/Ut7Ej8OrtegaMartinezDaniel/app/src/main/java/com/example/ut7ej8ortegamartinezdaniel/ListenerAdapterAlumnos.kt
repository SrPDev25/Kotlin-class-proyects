package com.example.ut7ej7ortegadaniel

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e6mdortegadaniel.Events
import com.example.ut7ej7ortegadaniel.dataBase.OperacionesDao
import com.example.ut7ej8ortegamartinezdaniel.R
import com.example.ut7ej8ortegamartinezdaniel.control.Evento
import com.example.ut7ej8ortegamartinezdaniel.databinding.ItemEventoRecyclerBinding.bind


class ListenerAdapterAlumnos(private val eventos: List<Evento>, private val usuario:Int,private val cont: Context, private val listener: Events) :
    RecyclerView.Adapter<ListenerAdapterAlumnos.ViewHolder>() {
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
            .inflate((R.layout.item_evento_recycler), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            var evento = eventos.get(position)
            var bd= OperacionesDao(cont)
            binding.txtFecha.text = evento.fecha
            binding.txtDescripcion.text = evento.descripcion
            binding.txtTitulo.text = evento.titulo
            //Inserta un set listener a cada uno de los "holders"
            setListener(evento.id)//pasa la posicion del vehiculo en la lista

            if(bd.isEventoUsuarioExist(usuario,evento.id)){
                binding.seccion.setBackgroundColor(Color.parseColor("#D1FFD6"))
            }else
                binding.seccion.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    override fun getItemCount(): Int {
        return eventos.size
    }


}