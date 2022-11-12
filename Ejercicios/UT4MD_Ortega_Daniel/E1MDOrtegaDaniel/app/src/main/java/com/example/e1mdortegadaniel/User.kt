package com.example.e1mdortegadaniel

class User (user:String,pass:String) {
    private var user:String=""
    private var pass:String=""
    init {
        this.user=user
        this.pass=pass
    }

    //Metodo que comprueba si el usuario coincide con los datos en la base de datos
    fun isSame (user:String,pass:String): Boolean {
        return this.user==user&&this.pass==pass
    }

}

