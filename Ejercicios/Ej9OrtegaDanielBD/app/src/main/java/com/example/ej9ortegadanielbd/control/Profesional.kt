package com.example.ej9ortegadanielbd.control

data class Profesional (
    var numColegiado:Int,
    var nombre:String,
    var tipoProfesional:Int
        ){
    override fun toString(): String {
        return nombre
    }
}