package com.mjpg.usuarios

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mjpg.usuarios.databinding.ItemUsersAltBinding
import com.mjpg.usuarios.databinding.ItemUsersAltBinding.*

//Herencia de RecyclerView
//Creo que es un adaptador
class UserAdapter(private val usuarios: List<Usuario>,
                            private val listener:Events) ://Añade el interface como
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
                val binding = bind(view)
                fun setListener(usuario:Usuario, posicion:Int){//Así se podría añadir un nuevo método en el inner
                    binding.root.setOnClickListener{//crea un listener en el da igual donde clices en la pantalla por ser root
                        listener.pulsirenCorta(usuario)//llama al metodo pulsiren() que se encuentra en la interface
                    }
                    binding.root.setOnLongClickListener{
                        listener.pulsirenLarga(posicion)
                    }
                }

            }

            /**
             * ????????????
             * Infla el layout item_user_alt.xml para poder llamar al holder y tener conexión
             */
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                    context = parent.context//Como estamos en una view, coge al parent para algo TODO ????????
                    val view = LayoutInflater.from(context)//TODO ????????
                        .inflate((R.layout.item_users_alt), parent, false)
                    return ViewHolder(view)

            }

            /**
             *
             */
            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                //TTodo lo que se haga dentro de las llaves hace que this==holder
                with(holder) {
                    binding.tvName.text = usuarios.get(position).dameNombreCompleto()//En el valor nombre le indica su nombre
                    binding.tvOrder.text = usuarios.get(position).id.toString()//En el valor Order añade la id
                    val usuario=usuarios.get(position)
                    setListener(usuario,position)
                    Glide.with(context)
                        .load(usuarios.get(position).ruta)//En el
                        .centerCrop()//Centra la imagen
                        .circleCrop()//Decoración circular
                        .into(binding.imgPhoto)
                }
            }

            /**
             * Devuelve la cantidad de usuarios del arrayList
             */
            override fun getItemCount()=usuarios.size



        }