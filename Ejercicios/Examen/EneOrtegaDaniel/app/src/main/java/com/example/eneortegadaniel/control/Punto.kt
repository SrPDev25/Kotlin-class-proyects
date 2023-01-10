package com.example.eneortegadaniel.control

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Punto(
    var codigo:String,
    var tipo:Int,
    var denominacion:String="",
    var direccion:String="",
    var provincia: String="",
    var cargador:String="standar",
    var indicaciones:String="predeterminado",
    var precio:String="5"
) :Parcelable{

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Punto

        if (codigo != other.codigo) return false

        return true
    }

    override fun hashCode(): Int {
        return codigo.hashCode()
    }
}