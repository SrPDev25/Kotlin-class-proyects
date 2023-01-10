package com.example.eneortegadaniel.control

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Establecimiento(
    var codigo:Int,
    var denominacion:String=""
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Establecimiento

        if (codigo != other.codigo) return false

        return true
    }

    override fun hashCode(): Int {
        return codigo
    }
}