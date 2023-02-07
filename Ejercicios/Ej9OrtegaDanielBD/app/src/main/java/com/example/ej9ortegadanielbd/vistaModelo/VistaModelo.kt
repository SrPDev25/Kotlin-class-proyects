package com.example.ej9ortegadanielbd.vistaModelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VistaModelo(id:Long): ViewModel() {
    var _identificador = MutableLiveData(id)//vista modelo de lectura y escritura
    val identificador: LiveData<Long>
        get()=_identificador

}