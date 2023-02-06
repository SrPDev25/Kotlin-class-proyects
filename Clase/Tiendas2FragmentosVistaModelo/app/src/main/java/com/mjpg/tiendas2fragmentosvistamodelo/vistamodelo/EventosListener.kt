package com.mjpg.tiendas2fragmentosvistamodelo.vistamodelo

import com.mjpg.tiendas2fragmentosvistamodelo.modelo.Tienda

interface EventosListener {
    fun editar(id:Long)
    fun onFavorito(tienda: Tienda)
    fun borrarTienda(id:Long)

}
