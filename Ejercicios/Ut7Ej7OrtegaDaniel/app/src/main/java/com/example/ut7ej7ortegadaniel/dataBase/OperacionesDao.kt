package com.mjpg.basedatos.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.ut7ej7ortegadaniel.control.Profesor
import com.mjpg.basedatos.bd.MyDBOpenHelper



//Tiene los metodos para manejarla
class OperacionesDao(contexto: Context) {

    //Clase SQLite
    private val mBD: SQLiteDatabase


    init {
        val estructura = MyDBOpenHelper(
            contexto,//Interface with all the activity information, you can call him as you call the activity or class
            MyDBOpenHelper.DATABASE_NAME,//The dataBase
            null,//No idea
            MyDBOpenHelper.DATABASE_VERSION//The version
        )
        mBD = estructura.writableDatabase
    }



    fun getUsuario(login: String, contrasena: String): Profesor? {
        var usuarioEncontrado: Profesor? = null
        val cursor: Cursor = mBD.rawQuery(
            "SELECT * FROM ${MyDBOpenHelper.TABLA_PROFESORES} " +
                    "WHERE ${MyDBOpenHelper.COL_LOGIN} = '$login' " +
                    "AND ${MyDBOpenHelper.COL_CONTRA} = '$contrasena'",
            null
        )

        if (!cursor.isClosed)
            cursor.close()

        return usuarioEncontrado
    }





}
