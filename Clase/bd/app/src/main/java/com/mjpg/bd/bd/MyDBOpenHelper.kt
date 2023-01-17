package com.mjpg.basedatos.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

//Crea y es la base de datos
class MyDBOpenHelper(
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    /**
     * Constantes estaticas
     */
        companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "enero.db"

        val TABLA_USUARIOS = "usuarios"
        val COL_LOGIN = "login"
        val COL_CONTRA = "contra"

    }

    /**
     * Funcion de inicio de base de datos
     */
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            //Crea la tabla con una String de MySQL
            val crearTablaUsuarios =
                "CREATE TABLE $TABLA_USUARIOS (" +
                        "$COL_LOGIN TEXT PRIMARY KEY , " +
                        "$COL_CONTRA TEXT)"
            //Si no es null, ejecuta la linea SQL
            db!!.execSQL(crearTablaUsuarios)


        } catch (e: SQLiteException) {
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


}
