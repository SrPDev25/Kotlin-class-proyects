package com.example.ortegadaniel

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class MyDBOpenHelper(context: Context, name:String,
                     factory:SQLiteDatabase.CursorFactory?,
                     version:Int):
    SQLiteOpenHelper(context,name,factory,version){
    companion object {
        const val DATABASE_NAME = "empresa"
        const val DATABASE_VERSION = 1

        const val TABLA_USUARIO = "usuarios"
        const val LOGIN = "login" //text pk
        const val PASS = "pass"//text

        const val TABLA_PRODUCTO = "productos"
        const val CODIGO_PRODUCTO = "cod_producto"//pk int autonum
        //const val CODIGO_CATEGORIA ="cod_categoria"//INT
        const val DENOMINACION_PRODUCTO="denominacion"//TEXT
        const val PRECIO="precio"//int
        const val EXCLUSIVO="exclusividad"//text (si, no)
        const val IMAGEN="URL"


        const val TABLA_CATEGORIA = "CATEGORIAS"
        const val COD_CATEGORIA = "cod_categoria"//pk int autonum
        const val DENOMINACION_CATEGORIA = "denominacion"//text
        //const val IMAGEN="url_Imagen"//text

    }
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val crearTablaUsuarios = "CREATE  TABLE $TABLA_USUARIO " +
                    "($LOGIN TEXT PRIMARY KEY," +
                    " $PASS TEXT)"
            val crearTablaCategoria = "CREATE  TABLe $TABLA_CATEGORIA  " +
                    "($COD_CATEGORIA Integer PRIMARY KEY AUTOINCREMENT," +
                    "$DENOMINACION_CATEGORIA text," +
                    "${IMAGEN} TEXT)"
            val crearTablaProducto = "CREATE  TABLE $TABLA_PRODUCTO  " +
                    "($CODIGO_PRODUCTO Integer PRIMARY KEY," +
                    "$COD_CATEGORIA INTEGER," +
                    "$DENOMINACION_PRODUCTO TEXT," +
                    "$PRECIO INTEGER," +
                    "$EXCLUSIVO TEXT," +
                    "$IMAGEN text)"

            db!!.execSQL(crearTablaUsuarios)
            db.execSQL(crearTablaCategoria)
            db.execSQL(crearTablaProducto)

        }catch(e:SQLiteException){}
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}