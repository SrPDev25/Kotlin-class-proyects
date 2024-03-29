package com.example.ej9ortegadanielbd.vistaModelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VistaModelo(id: Long) : ViewModel() {
    var _identificador = MutableLiveData(id)//vista modelo de lectura y escritura
    //LiveData es un contenedor de observers
    var _nombre=MutableLiveData("")
    val identificador: LiveData<Long>
        //vista modelo de lectura
        get() = _identificador
    val nombre:LiveData<String>
        get()=_nombre
    //Inicializa el LiveData
    init {
        _identificador = MutableLiveData(0)
    }

    //Cambia el modificador
    fun setUsuario(id: Long) {
        _identificador.value = id
    }

    fun setNombre(nombre:String){
        this._nombre.value=nombre
    }



}

