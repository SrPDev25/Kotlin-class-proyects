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

        val TABLA_PROFESORES = "profesores"
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
        val COL_CODIGO_FALTAS = "codigo_falta"
        //CODIGO ALUMNO//TXT NN
        //CODIGO PROFESOR/TXT NN
        val COL_FECHA = "fecha"//TXT NN
        val COL_HORA = "hora"//TXT NN
        val COL_JUSTIFICADA = "justificada"//BOOL PREDEF:FALSE
        val COL_OBSERVACIONES = "observaciones"//TXT

    }

    /**
     * Funcion de inicio de base de datos
     */
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            //Crea la tabla con una String de MySQL
            val crearTablaAlumnos =
                "CREATE TABLE $TABLA_ALUMNOS (" +
                        "$COL_CODIGO_ALU INT PRIMARY KEY AUTOINCREMENT  , " +
                        "$COL_NOMBRE_ALUMNO TEXT)"
            val crearTablaProfesores =
                "CREATE TABLE $TABLA_PROFESORES (" +
                        "$COL_CODIGO_PROF INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$COL_LOGIN TEXT NOT NULL," +
                        "$COL_NOMBRE_PROFESOR TEXT," +
                        "$COL_CONTRA TEXT)"

            val query=crearTablaAlumnos+";"+crearTablaProfesores

            //Si no es null, ejecuta la linea SQL
            db!!.execSQL(query)



        } catch (e: SQLiteException) {
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


}
