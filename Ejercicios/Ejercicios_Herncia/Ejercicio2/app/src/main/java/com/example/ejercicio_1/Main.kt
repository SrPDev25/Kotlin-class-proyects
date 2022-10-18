package com.example.ejercicio_1

fun main(){
    var vehiculos= arrayListOf<Vehiculo>(
        Vehiculo("","Rosa","Motomami"),
        Vehiculo("","Negro","Nissan"),
        Vehiculo("Nisu","","Nisu"),
    )
    //Ejercicio 1
    /*vehiculos.add(Vehiculo())
    vehiculos.get(0).marca="Nissan"
    vehiculos.add(Vehiculo())
    vehiculos.get(1).marca="Motomami"
    vehiculos.add(Vehiculo())
    vehiculos.get(2).marca="Nisu"*/
    for (x in vehiculos){
        mostrarVehiculo(x)
    }
}

fun mostrarVehiculo(x:Vehiculo):Unit{
    println("-------------------")
    println("marca: ${x.marca}")
    println("color: ${x.color}")
    println("modelo: ${x.modelo}")
}