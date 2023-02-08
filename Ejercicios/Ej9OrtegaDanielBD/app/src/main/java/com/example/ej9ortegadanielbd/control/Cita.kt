package com.example.ej9ortegadanielbd.control

data class Cita (
    var id:Int,
    var fecha:String,
    var hora:String,
    var codigoProfesional: Int,
    var numAfiliado:Int,
    //Valores para mostrar en tarjeta
    var nombreProfesional:String="",
    var nombreTipoProfesional: String=""
        )