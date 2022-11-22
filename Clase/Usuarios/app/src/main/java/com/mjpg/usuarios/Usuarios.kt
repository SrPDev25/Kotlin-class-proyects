package com.mjpg.usuarios

data class Usuarios(val id:Long, var nombre:String,var apellidos:String, var ruta:String){
    fun dameNombreCompleto()="$nombre  $apellidos"
    
}
