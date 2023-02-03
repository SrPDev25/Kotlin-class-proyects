package com.mjpg.tiendas.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class MyDBOpenHelper(context: Context, name:String,
                     factory:SQLiteDatabase.CursorFactory?,
                     version:Int):
    SQLiteOpenHelper(context,name,factory,version){
    companion object {
        const val DATABASE_NAME = "CSOrtegaDaniel"
        const val DATABASE_VERSION = 1

        const val TABLA_USUARIO = "usuarios"
        const val NOMBRE_USUARIO = "nombre" //text
        const val NUM_AFILIADO = "num_afiliacion"//PK Int Autonum

        const val TABLA_PROFESIONAL = "profesionales"
        const val NUM_COLEGIADO = "num_colegiado"//pk int autonum

        //const val COD_TIPO_PROFESIONAL="cod_tipo"//int
        const val NOMBRE_PROFESIONAL = "nombre"//text

        const val TABLA_TIPO_PROFESIONAL = "tipo_profesional"
        const val COD_TIPO_PROFESIONAL = "cod_tipo"//pk int autonum
        const val DESCRIPCION_TIPO = "descripcion"//text(20)

        const val TABLA_RALACION_PROF_USU = "profesional_usuario"
        const val COD_RELACION = "id"//int auto pk
        //const val NUM_COLEGIADO="num_colegiado"//int UNIQUE
        //const val NUM_AFILIADO="num_afiliacion"//int UNIQUE
        //La relacion entre dos codigos no se puede repetir

        const val TABLA_CITAS = "citas"
        const val COD_CITA = "id"//PK INT AUTONUM
        const val FECHA = "FECHA"//TExt (aaaa/mm/dd)
        const val HORA = "hora"
        // const val COD_TIPO_PROFESIONAL="cod_tipo"
        //const val NUM_AFILIADO="num_afiliacion"//varchar(20)
        //Un afiliado no puede tener 2 citas la misma hora y fecha
        //Un profesional no puede tener 2 citas, la misma hora y fecha
    }
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val crearTablaUsuarios = "CREATE  TABLE $TABLA_USUARIO " +
                    "($NUM_AFILIADO Integer PRIMARY KEY AUTOINCREMENT," +
                    " $NOMBRE_USUARIO TEXT(40))"
            val crearTablaProfesionales = "CREATE  TABLE $TABLA_PROFESIONAL " +
                    "($NUM_COLEGIADO Integer PRIMARY KEY AUTOINCREMENT," +
                    "$COD_TIPO_PROFESIONAL INTEGER," +
                    "$NOMBRE_PROFESIONAL TEXT)"
            val crearTablaRelacion = "CREATE  TABLE ${TABLA_RALACION_PROF_USU} " +
                    "($COD_RELACION Integer PRIMARY KEY AUTOINCREMENT," +
                    "$NUM_COLEGIADO INTEGER," +
                    "$NUM_AFILIADO INTEGER(20))"
            val crearTablaTipoProfesional = "CREATE  TABLE ${TABLA_TIPO_PROFESIONAL}" +
                    "(${COD_TIPO_PROFESIONAL} Integer PRIMARY KEY AUTOINCREMENT," +
                    "$DESCRIPCION_TIPO TEXT)"
            val crearTablaCitas = "CREATE  TABLE ${TABLA_CITAS}" +
                    "(${COD_CITA} Integer PRIMARY KEY AUTOINCREMENT," +
                    "$FECHA TEXT," +
                    "$HORA text," +
                    "$COD_TIPO_PROFESIONAL INTEGER," +
                    "$NUM_AFILIADO INTEGER)"
            db!!.execSQL(crearTablaUsuarios)
            db.execSQL(crearTablaProfesionales)
            db.execSQL(crearTablaRelacion)
            db.execSQL(crearTablaTipoProfesional)
            db.execSQL(crearTablaCitas)
        }catch(e:SQLiteException){}
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}