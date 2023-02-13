package com.example.ej9ortegadanielbd.vistaModelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VistaModelo(id: Long) : ViewModel() {
    var _identificador = MutableLiveData(id)//vista modelo de lectura y escritura
    //LiveData es un contenedor de observers
    val identificador: LiveData<Long>
        //vista modelo de lectura
        get() = _identificador

    var _categoria = MutableLiveData(id)//vista modelo de lectura y escritura
    //LiveData es un contenedor de observers
    val categoria: LiveData<Long>
        //vista modelo de lectura
        get() = _categoria
    //Inicializa el LiveData
    init {
        _identificador = MutableLiveData(0)
        _categoria=MutableLiveData(-1)
    }

    //Cambia el modificador
    fun setUsuario(id: Long) {
        _identificador.value = id
    }

    fun setCategoria(cod:Long){
        _categoria.value=cod
    }



}

