package com.mjpg.tiendas2fragmentosvistamodelo.modelo

data class Tienda(var id: Long=0 ,
                  var name:String,
                  var phone:String,
                  var webSite:String="",
                  var photoUrl:String,
                  var esFavorito:Int=0
                  ){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tienda

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}