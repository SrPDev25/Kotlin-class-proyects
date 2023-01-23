package com.example.ut7ej7ortegadaniel.control

data class Falta(
    var observaciones:String,
    var codigo:Int,
    var codigoAlumno: Int=-1,
    var codigoProfesor: Int=-1,
    var fecha: String="",
    var hora: String="",
    var justificada: Int=-1,//No existe booleano en SQLlite,


) {
}