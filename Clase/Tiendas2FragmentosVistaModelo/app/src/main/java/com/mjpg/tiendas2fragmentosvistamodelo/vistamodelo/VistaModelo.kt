package com.mjpg.tiendas2fragmentosvistamodelo.vistamodelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VistaModelo(id: Long) : ViewModel() {
    var _identificador = MutableLiveData(id)//vista modelo de lectura y escritura
    val identificador: LiveData<Long>
        //vista modelo de lectura
        get() = _identificador

    init {
        _identificador = MutableLiveData(0)
    }


    fun setIdentificador(id: Long) {
        _identificador.value = id
    }

}

