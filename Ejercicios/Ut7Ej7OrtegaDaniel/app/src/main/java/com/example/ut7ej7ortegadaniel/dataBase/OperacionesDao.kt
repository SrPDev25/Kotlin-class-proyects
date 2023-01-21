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

    fun verificar(login:String, pass:String): Profesor? {
        var profesor:Profesor?=null
        val cursor:Cursor=mBD.rawQuery(
            "Select * from ${MyDBOpenHelper.TABLA_PROFESORES}" +
                    " WHERE ${MyDBOpenHelper.COL_LOGIN}='$login'" +
                    "and ${MyDBOpenHelper.COL_CONTRA}='$pass'",
                    null
        )
        if(cursor.moveToFirst()){//como el .Next de java
            profesor=Profesor(
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_LOGIN)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_CONTRA))
            )
        }
        if(!cursor.isClosed)
            cursor.close()//Cerrar base de datos?

        return profesor
    }

    fun addProfesor(profesor: Profesor) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.COL_LOGIN, profesor.login)
        values.put(MyDBOpenHelper.COL_CONTRA, profesor.pass)
        values.put(MyDBOpenHelper.COL_NOMBRE_PROFESOR, profesor.nombre)
        mBD.insert(MyDBOpenHelper.TABLA_PROFESORES, null, values)
    }

    //Evita que el programa vaya al pete
    //Comprueba si las tablas tienen datos
    fun tablasVacias(): Boolean {
        var vacia:Boolean
        var cursor: Cursor = mBD.query(
            MyDBOpenHelper.TABLA_PROFESORES, null, null,
            null, null, null, null
        )
        vacia = !cursor.moveToFirst()
        if (vacia)
            return vacia
        cursor= mBD.query(
        MyDBOpenHelper.TABLA_PROFESORES, null, null,
        null, null, null, null
        )
        vacia = !cursor.moveToFirst()
        if (vacia)
            return vacia
        cursor= mBD.query(
        MyDBOpenHelper.TABLA_PROFESORES, null, null,
        null, null, null, null
        )
        vacia = !cursor.moveToFirst()
        if (vacia)
            return vacia
        cursor= mBD.query(
        MyDBOpenHelper.TABLA_PROFESORES, null, null,
        null, null, null, null
        )
        if (vacia)
            return vacia
        cursor.close()
        return vacia
    }


}
