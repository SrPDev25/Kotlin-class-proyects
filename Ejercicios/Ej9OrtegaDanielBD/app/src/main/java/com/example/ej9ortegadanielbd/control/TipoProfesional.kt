package com.example.ej9ortegadanielbd.control

data class TipoProfesional (
    var codigo:Int,
    var denominacion:String
        )
{
    override fun toString(): String {
        return denominacion
    }
}