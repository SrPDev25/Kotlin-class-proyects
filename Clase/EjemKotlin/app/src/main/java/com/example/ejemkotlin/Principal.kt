package com.example.ejemkotlin

fun main(){
    println("Arriba España")
    mostrarProducto("Boligrafo")
    mostrarProducto("Lapiz",vigencia=15, promo=69)
    //TODO cosas
}

fun suma (a:Int, b:Int):Int{
    return a+b
}
fun resta (a:Int, b:Int)= a-b

fun mostrarProducto(nombre:String, vigencia:Int=10, promo:Int=222):Unit{
    println("---------------------")
    println("nombre $nombre")
    println("vigencia $vigencia")
    println("promo $promo")
}