package com.mjpg.tiendas.interfaces

import com.mjpg.tiendas.modelo.Tienda

interface EventosListener {
    fun editar(id:Long)
    fun onFavorito(tienda: Tienda)
    fun borrarTienda(id:Long)

}
