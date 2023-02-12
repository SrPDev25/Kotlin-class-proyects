package com.example.ej9ortegadanielbd.control

data class Cita (
    var id:Int,
    var fecha:String,
    var hora:String,
    var codigoProfesional: Int=-1,
    var numAfiliado:Int=-1,
    //Valores para mostrar en tarjeta
    var nombreProfesional:String="",
    var nombreTipoProfesional: String=""
        )