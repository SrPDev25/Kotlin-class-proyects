package com.example.eneortegadaniel.control

//No hace falta parcelar
data class Usuario(
    var usuario: String,
    var pass: String,
) {



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (usuario != other.usuario) return false
        if (pass != other.pass) return false

        return true
    }

    override fun hashCode(): Int {
        var result = usuario.hashCode()
        result = 31 * result + pass.hashCode()
        return result
    }
}