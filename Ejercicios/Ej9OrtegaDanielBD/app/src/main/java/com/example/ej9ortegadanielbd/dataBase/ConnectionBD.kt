package com.mjpg.tiendas.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class ConnectionBD(context: Context, name:String,
                   factory:SQLiteDatabase.CursorFactory?,
                   version:Int):
    SQLiteOpenHelper(context,name,factory,version){
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val crearTablaTienda = "CREATE  TABLE ${OperacionesDAO.TABLA_USUARIO} " +
                    "(${OperacionesDAO.NUM_AFILIADO} PRIMARY KEY AUTOINCREMENT," +
                    " name TEXT," +
                    " phone TEXT, webSite TEXT," +
                    " photoUrl TEXT, " +
                    "esFavorito INTEGER)"

            db!!.execSQL(crearTablaTienda)
        }catch(e:SQLiteException){}
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}