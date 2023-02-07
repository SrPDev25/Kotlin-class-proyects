package com.example.ej9ortegadanielbd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ej9ortegadanielbd.control.Usuario
import com.example.ej9ortegadanielbd.databinding.ItemCardBinding.bind
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao


class ListenerAdapterCards(private val usuarios: List<Usuario>,  private val cont: Context, private val listener: Events) :
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
            .inflate((R.layout.item_card), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            var usuario = usuarios.get(position)
            var bd= OperacionesDao(cont)
            binding.idCard.text = usuario.numAfiliado.toString()
            binding.nombre.text = usuario.nombre
            //Inserta un set listener a cada uno de los "holders"
            setListener(usuario.numAfiliado)
        }
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }


}