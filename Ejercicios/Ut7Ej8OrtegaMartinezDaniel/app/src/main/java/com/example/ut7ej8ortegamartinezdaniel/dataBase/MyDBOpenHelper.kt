package com.example.ut7ej7ortegadaniel.dataBase

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

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "organizacion.db"

        const val TABLA_EVENTOS = "eventos"
        const val COL_ID_EVENTO = "id_evento"//Integer PRIMARY KEY AUTOINCREMENT
        const val COL_FECHA_EVENTO = "fecha"//TEXTO
        const val COL_HORA = "hora"//TEXTO
        const val COL_TITULO = "titulo"//TEXTO
        const val COL_DESCRIPCION = "descripcion"//TEXTO

        const val TABLA_EVENTOS_USUARIO = "eventos_usuario"
        const val COL_ID_RELACION="id_evento_usuario"
        //const val COL_ID_USUARIO = "id_usuario"//int NN PK
        //const val COL_ID_EVENTO = "id_evento"


        const val TABLA_USUARIO = "usuarios"
        const val COL_ID_USUARIO = "id_usuario"//int NN PK
        const val COL_LOGIN = "login" //TEXT
        const val COL_CONTRA = "contraseña" //TEXT
        const val COL_PERFIL = "perfil"//TEXT

    }

    /**
     * Funcion de inicio de base de datos
     */
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            //Crea la tabla con una String de MySQL
            val crearTablaUsuarios =
                "CREATE TABLE $TABLA_USUARIO (" +
                        "$COL_ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$COL_CONTRA TEXT," +
                        "$COL_LOGIN TEXT," +
                        "$COL_PERFIL TEXT)"
            val crearTablaEventos =
                "create TABLe $TABLA_EVENTOS(" +
                        "$COL_ID_EVENTO INTEGER primary key AUTOINCREMENT, " +
                        "$COL_FECHA_EVENTO TEXT," +
                        "$COL_HORA TEXT, " +
                        "$COL_TITULO TEXT," +
                        "$COL_DESCRIPCION TEXT)"
            val crearTablaRelacion =
                "create table $TABLA_EVENTOS_USUARIO(" +
                        "$COL_ID_RELACION INTEGER primary key AUTOINCREMENT," +
                        "$COL_ID_EVENTO INTEGER," +
                        "$COL_ID_USUARIO INTEGER)"

            //Si no es null, ejecuta la linea SQL
            db!!.execSQL(crearTablaEventos)
            db.execSQL(crearTablaUsuarios)
            db.execSQL(crearTablaRelacion)


        } catch (_: SQLiteException) {
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
