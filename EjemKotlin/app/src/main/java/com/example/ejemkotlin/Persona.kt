package com.example.ejemkotlin

abstract class Persona(nombre:String) {
    var nombre:String=""
    set(value){
        field=if(value.isEmpty()) "Sin nombre" else value
    }
    get(){
        return "El nombre es ${field.uppercase()}"
    }
    var edad=0
    get () =if(field==0) 999 else field
    init{
        this.nombre=nombre
    }


    constructor(nombre: String,edad:Int):this(nombre){

    }
}