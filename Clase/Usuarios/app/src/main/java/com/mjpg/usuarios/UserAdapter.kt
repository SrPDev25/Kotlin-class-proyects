package com.mjpg.usuarios

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mjpg.usuarios.databinding.ItemUsersAltBinding

//Herencia de RecyclerView
//Creo que es un adaptador
class UserAdapter(private val usuarios: List<Usuario>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context: Context//????????

    /**
     * Asocia la vista con el adaptador (RecyclerView????)
     * Se vincula la vista con el adaptador ItemUsers inflando la vista a la que
     * llamemos en los siguientes métodos
     *
     * ????inner
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUsersAltBinding.bind(view)
    }

    /**
     * ????????????
     * Infla el layout item_user_alt.xml para poder llamar al holder y tener conexión
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context)//????????
            .inflate((R.layout.item_users_alt), parent, false)
        return ViewHolder(view)
    }

    /**
     *
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TTodo lo que se haga dentro de las llaves hace que this==holder
        with(holder) {
            binding.tvName.text = usuarios.get(position).dameNombreCompleto()
            binding.tvOrder.text = usuarios.get(position).id.toString()
            Glide.with(context)
                .load("")//TODO usuarios.ruta
                .centerCrop()//Centra la imagen
                .circleCrop()//Decoración circular
                .into(binding.imgPhoto)
        }
    }

    /**
     * Devuelve la cantidad de usuarios del arrayList
     */
    override fun getItemCount()=usuarios.size


    inner class viewHolder(view:View): RecyclerView
}