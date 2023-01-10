package com.example.eneortegadaniel.control

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Control(
    var establecimientos: MutableList<Establecimiento>,
    var puntos: MutableList<Punto>
) : Parcelable {

    fun isPuntoExist(codigo:String):Boolean{
        var exist=true
        if (puntos.indexOf(Punto(codigo,0))==-1)
            exist =false
        return exist
    }

    fun getEstablecimiento(codigo: Int):String {
        var result=""
        var pos=establecimientos.indexOf(Establecimiento(codigo))
        if (pos!=-1)
            result=establecimientos.get(pos).denominacion

        return result
    }

}