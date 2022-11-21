package com.example.e1mdortegadaniel

class User (user:String,pass:String) {
    private var user:String=""
    private var pass:String=""
    init {
        this.user=user
        this.pass=pass
    }


    //El equals para el indexOf
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (user != other.user) return false
        if (pass != other.pass) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user.hashCode()
        result = 31 * result + pass.hashCode()
        return result
    }


}

