package com.mjpg.basedatos.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.mjpg.basedatos.bd.MyDBOpenHelper


import com.mjpg.bd.modelo.Usuario

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

    fun addUsuario(usuario: Usuario) {
        val values = ContentValues()

        values.put(MyDBOpenHelper.COL_LOGIN, usuario.login)
        values.put(MyDBOpenHelper.COL_CONTRA, usuario.contra)
        mBD.insert(MyDBOpenHelper.TABLA_USUARIOS, null, values)
    }

    fun getUsuario(login: String, contrasena: String): Usuario? {
        var usuarioEncontrado: Usuario? = null
        val cursor: Cursor = mBD.rawQuery(
            "SELECT * FROM ${MyDBOpenHelper.TABLA_USUARIOS} " +
                    "WHERE ${MyDBOpenHelper.COL_LOGIN} = '$login' " +
                    "AND ${MyDBOpenHelper.COL_CONTRA} = '$contrasena'",
            null
        )
        if (cursor.moveToFirst()) {
            usuarioEncontrado = Usuario(
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_LOGIN)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_CONTRA))
            )

        }
        if (!cursor.isClosed)
            cursor.close()

        return usuarioEncontrado
    }

    fun tablaVaciaUsuarios(): Boolean {
        val vacia: Boolean
        val cursor: Cursor = mBD.query(
            MyDBOpenHelper.TABLA_USUARIOS, null, null,
            null, null, null, null
        )
        vacia = !cursor.moveToFirst()
        cursor.close()
        return vacia
    }






}
