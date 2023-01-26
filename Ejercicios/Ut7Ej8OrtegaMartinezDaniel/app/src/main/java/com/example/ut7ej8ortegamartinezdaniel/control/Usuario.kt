package com.example.ut7ej8ortegamartinezdaniel.control

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    val login:String,
    val contra:String,
    val perfil:String,
    val id:Int=-1) : Parcelable