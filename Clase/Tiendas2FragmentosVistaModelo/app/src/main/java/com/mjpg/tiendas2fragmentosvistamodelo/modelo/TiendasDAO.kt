package com.mjpg.tiendas2fragmentosvistamodelo.modelo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class TiendasDAO(contexto: Context) {
    private val mBD: SQLiteDatabase

    companion object {
        const val DATABASE_NAME = "tiendasbd"
        const val DATABASE_VERSION = 1
        const val TABLA_TIENDA = "tiendas"
    }

    init {
        val estructura = TiendaBD(
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
         return mBD.insert(TABLA_TIENDA, null, values)
    }

    fun getAllTiendas(): MutableList<Tienda> {
        val lista: MutableList<Tienda> = ArrayList()
        val cursor: Cursor = mBD.query(
            TABLA_TIENDA,
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
        val sql="select * from $TABLA_TIENDA"
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
        val sql="select * from $TABLA_TIENDA where id=$id"
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
       mBD.update(TABLA_TIENDA, values, "id=?", args)

    }

    fun deleteTienda(id: Long) {
        val args = arrayOf(id.toString())
        mBD.delete(TABLA_TIENDA, "id=?", args)

    }
    fun cerrar(){
        mBD.close()
    }
}