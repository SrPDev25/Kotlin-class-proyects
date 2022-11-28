package com.mjpg.usuarios

interface Events
{
    //Listener pa
    fun pulsirenCorta(usuario: Usuario)
    fun pulsirenLarga(posicion:Int):Boolean//Te pide que devuelvas un boolean por algun motivo



}