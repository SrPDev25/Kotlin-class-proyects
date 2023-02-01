package com.mjpg.tiendas.bd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.mjpg.tiendas.modelo.Tienda

class OperacionesDAO(contexto: Context) {
    private val mBD: SQLiteDatabase

    companion object {
        const val DATABASE_NAME = "CSOrtegaDaniel"
        const val DATABASE_VERSION = 1

        const val TABLA_USUARIO = "usuarios"
        const val NOMBRE_USUARIO="nombre" //text
        const val NUM_AFILIADO="num_afiliacion"//PK Int Autonum

        const val TABLA_PROFESIONAL="profesionales"
        const val NUM_COLEGIADO="num_colegiado"//pk int autonum
        //const val COD_TIPO_PROFESIONAL="cod_tipo"//int
        const val NOMBRE_PROFESIONAL="nombre"//text

        const val TABLA_TIPO_PROFESIONAL="tipo_profesional"
        const val COD_TIPO_PROFESIONAL="cod_tipo"//pk int autonum
        const val DESCRIPCION_TIPO="descripcion"//text(20)

        const val TABLA_RALACION_PROF_USU="profesional_usuario"
        const val COD_RELACION="id"//int auto pk
        //const val NUM_COLEGIADO="num_colegiado"//int UNIQUE
        //const val NUM_AFILIADO="num_afiliacion"//int UNIQUE
        //La relacion entre dos codigos no se puede repetir

        const val TABLA_CITAS="citas"
        const val COD_CITA="id"//PK INT AUTONUM
        const val FECHA="FECHA"//TExt (aaaa/mm/dd)
        const val HORA="hora"
        // const val COD_TIPO_PROFESIONAL="cod_tipo"
        //const val NUM_AFILIADO="num_afiliacion"//varchar(20)
        //Un afiliado no puede tener 2 citas la misma hora y fecha
        //Un profesional no puede tener 2 citas, la misma hora y fecha

    }

    init {
        val estructura = ConnectionBD(
            contexto,
            DATABASE_NAME, null, DATABASE_VERSION
        )
        mBD = estructura.writableDatabase
    }

    fun addTienda(tienda: Tienda):Long {
        val values = ContentValues()
        values.put("name", tienda.name)
        values.put("phone", tienda.phone)
        values.put("webSite", tienda.webSite)
        values.put("photoUrl", tienda.photoUrl)
        values.put("esFavorito", tienda.esFavorito)
         return mBD.insert(TABLA_USUARIO, null, values)
    }

    fun getAllTiendas(): MutableList<Tienda> {
        val lista: MutableList<Tienda> = ArrayList()
        val cursor: Cursor = mBD.query(
            TABLA_USUARIO,
            null, null,
            null, null,
            null, null
        )
        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    Tienda(
                        cursor.getLong(0),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                        cursor.getString(cursor.getColumnIndexOrThrow("webSite")),
                        cursor.getString(cursor.getColumnIndexOrThrow("photoUrl")),
                        cursor.getInt(
                            cursor.getColumnIndexOrThrow("esFavorito")
                        )
                    )
                )
            } while (cursor.moveToNext())

        }
        if (!cursor.isClosed)
            cursor.close()
        return lista
    }
    fun primerElemento(): Tienda?{
        var elemento: Tienda?=null
        val sql="select * from $TABLA_USUARIO"
        val cursor:Cursor=mBD.rawQuery(sql,null)
        if(cursor.moveToFirst())
            elemento= Tienda(
            cursor.getLong(0),
            cursor.getString(cursor.getColumnIndexOrThrow("name")),
            cursor.getString(cursor.getColumnIndexOrThrow("phone")),
            cursor.getString(cursor.getColumnIndexOrThrow("webSite")),
            cursor.getString(cursor.getColumnIndexOrThrow("photoUrl")),
            cursor.getInt(
                cursor.getColumnIndexOrThrow("esFavorito")
            ))
        cursor.close()
        return elemento
    }
    fun getStoreById(id:Long): Tienda? {
        var elemento: Tienda?=null
        val sql="select * from $TABLA_USUARIO where id=$id"
        val cursor: Cursor = mBD.rawQuery(sql,null)

        if (cursor.moveToFirst()) {
                 elemento= Tienda(
                        cursor.getLong(0),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                        cursor.getString(cursor.getColumnIndexOrThrow("webSite")),
                        cursor.getString(cursor.getColumnIndexOrThrow("photoUrl")),
                        cursor.getInt(
                            cursor.getColumnIndexOrThrow("esFavorito")
                        )
                    )


        }
        if (!cursor.isClosed)
            cursor.close()
        return elemento
    }

    fun updateTienda(tienda: Tienda) {

        val values = ContentValues()
        values.put("name", tienda.name)
        values.put("phone", tienda.phone)
        values.put("webSite", tienda.webSite)
        values.put("photoUrl", tienda.photoUrl)
        values.put("esFavorito", tienda.esFavorito)
        val args = arrayOf(tienda.id.toString())
       mBD.update(TABLA_USUARIO, values, "id=?", args)

    }

    fun deleteTienda(id: Long) {
        val args = arrayOf(id.toString())
        mBD.delete(TABLA_USUARIO, "id=?", args)

    }
    fun cerrar(){
        mBD.close()
    }
}