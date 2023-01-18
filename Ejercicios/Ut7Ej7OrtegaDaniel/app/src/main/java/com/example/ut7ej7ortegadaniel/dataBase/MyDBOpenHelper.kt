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
        val DATABASE_NAME = "centro.db"

        val TABLA_PROFESORES = "usuarios"
        val COL_CODIGO_PROF = "codigo_profesor"//int NN PK
        val COL_LOGIN = "login"//txt NN
        val COL_CONTRA = "contra"//txt NN
        val COL_NOMBRE_PROFESOR = "nombre_apellido"//txt NN

        val TABLA_ALUMNOS = "alumnos"
        val COL_CODIGO_ALU = "codigo_alumno"//int NN PK
        val COL_NOMBRE_ALUMNO = "nombre_apellido" //txt NN

        val TABLA_PROFESOR_ALUMNO = "profesor_alumno"
        val COL_CODIGO_RELACION = "cod_relacion" //int NN PK
        //CODIGO ALUMNO//txt NN
        //CODIGO PROFESOR//txt NN

        val TABLA_FALTAS = "faltas"
        val COL_CODIGO_FALTAS = "login"
        //CODIGO ALUMNO//TXT NN
        //CODIGO PROFESOR/TXT NN
        val COL_FECHA = "contra"//TXT NN
        val COL_HORA = "contra"//TXT NN
        val COL_JUSTIFICADA = "contra"//BOOL PREDEF:FALSE
        val COL_OBSERVACIONES = "contra"//TXT

    }

    /**
     * Funcion de inicio de base de datos
     */
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            //Crea la tabla con una String de MySQL
            val crearTablaUsuarios =
                "CREATE TABLE $TABLA_ALUMNOS (" +
                        "$COL_CODIGO_ALU TEXT PRIMARY KEY , " +
                        "$COL_NOMBRE_ALUMNO TEXT)"
            //Si no es null, ejecuta la linea SQL
            db!!.execSQL(crearTablaUsuarios)


        } catch (e: SQLiteException) {
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


}
