package com.example.ej9ortegadanielbd

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ej9ortegadanielbd.control.Cita
import com.example.ej9ortegadanielbd.databinding.ItemCitaBinding.bind
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao


class ListenerAdapterCitas(private val citas: MutableList<Cita>, private val cont: Context, private val listener: Events) :
    RecyclerView.Adapter<ListenerAdapterCitas.ViewHolder>() {
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
            .inflate((R.layout.item_cita), parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            var cita = citas.get(position)
            binding.fechasTxt.text="Cita a día ${cita.fecha}"//TODO mejorar la visualización de la fecha
            binding.horasTxt.text="a las ${cita.hora}"
            binding.nombreProfesionalTxt.text="Profesional: ${cita.nombreProfesional}"
            binding.tipoEspecialidadTxt.text=cita.nombreTipoProfesional
            //Inserta un set listener a cada uno de los "holders"
            setListener(cita.numAfiliado)
        }
    }

    override fun getItemCount(): Int {
        return citas.size
    }


}