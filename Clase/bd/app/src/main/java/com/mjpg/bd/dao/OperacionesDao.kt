package com.mjpg.basedatos.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.mjpg.basedatos.bd.MyDBOpenHelper


import com.mjpg.bd.modelo.Usuario


class OperacionesDao(contexto: Context) {

    private val mBD: SQLiteDatabase


    init {
        val estructura = MyDBOpenHelper(
            contexto,
            MyDBOpenHelper.DATABASE_NAME,
            null,
            MyDBOpenHelper.DATABASE_VERSION
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
