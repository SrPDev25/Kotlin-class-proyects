package com.example.ut7ej7ortegadaniel.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.ut7ej8ortegamartinezdaniel.control.Evento
import com.example.ut7ej8ortegamartinezdaniel.control.Usuario
import java.util.*


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
        if (tablasVacias())
            insertarDatos()
    }


    fun verificar(login: String, pass: String): Usuario? {
        var usuario: Usuario? = null
        val cursor: Cursor = mBD.rawQuery(
            "Select * from ${MyDBOpenHelper.TABLA_USUARIO}" +
                    " WHERE ${MyDBOpenHelper.COL_LOGIN}='$login'" +
                    "and ${MyDBOpenHelper.COL_CONTRA}='$pass'",
            null
        )
        //como el .Next de java
        if (cursor.moveToFirst()) usuario = Usuario(
            cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_LOGIN)),
            cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_CONTRA)),
            cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_PERFIL)),
            cursor.getInt(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_ID_USUARIO))
        )
        if (!cursor.isClosed)
            cursor.close()//Cerrar base de datos?

        return usuario
    }




    fun getEventos(): MutableList<Evento> {
        val eventos = mutableListOf<Evento>()
        var evento:Evento?=null
        val cursor: Cursor = mBD.rawQuery(
            "select * from ${MyDBOpenHelper.TABLA_EVENTOS} ",
            null
        )
        while (cursor.moveToNext()) {
            evento=Evento(cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(0)
            )
            if (!isPasado(evento.fecha,evento.hora))//Se purgan eventos pasados
                eventos.add(evento)
        }

        if (!cursor.isClosed)
            cursor.close()
        return eventos
    }

    /**
     * Comprueba si la fecha ya ocurrio transformando la fecha pasada en Date
     */
    fun isPasado(fecha:String, hora:String):Boolean{
        var isPasado=false
        val peaces=fecha.split("/")
        var date= Date(peaces[2].toInt(),peaces[1].toInt(),peaces[0].toInt())
        return date.time<System.currentTimeMillis()//Se transforma la fecha en milisegundos deslde el año 0
    }

    fun getEventosOrdenados():MutableList<Evento>{
        var eventos=getEventos()
        var eventosOrdenados= mutableListOf<Evento>()
        var currentMaxFecha=0L
        var n=0L
        var pos=0
        var posToDelete=0
        var date: Date? =null
        var fecha:List<String>?=null


        while(eventos.size!=0){
            currentMaxFecha=0
            pos=0
            while (pos < eventos.size){
                fecha=eventos.get(pos).fecha.split("/")
                n=Date(fecha[2].toInt(),fecha[1].toInt(),fecha[0].toInt()).time
                if (n>currentMaxFecha){
                    currentMaxFecha=n
                    posToDelete=pos
                }
                pos++
            }
            eventosOrdenados.add(eventos.get(posToDelete))
            eventos.removeAt(posToDelete)
        }

        return eventosOrdenados
    }

    /**
     * Añade un nuevo evento en la base de datos
     */
    fun addEvento(fecha:String, hora:String,titulo:String,descripcion:String){
        val values = ContentValues()
        values.put(MyDBOpenHelper.COL_FECHA_EVENTO, fecha)
        values.put(MyDBOpenHelper.COL_HORA, hora)
        values.put(MyDBOpenHelper.COL_TITULO, titulo)
        values.put(MyDBOpenHelper.COL_DESCRIPCION, descripcion)
        mBD.insert(MyDBOpenHelper.TABLA_EVENTOS, null, values)
    }

    /**
     * añade un nuevo usuario a la base de datos
     */
    fun addUsuario(login:String, contra:String, perfil:String){
        val values = ContentValues()
        values.put(MyDBOpenHelper.COL_LOGIN, login)
        values.put(MyDBOpenHelper.COL_CONTRA, contra)
        values.put(MyDBOpenHelper.COL_PERFIL, perfil)
        mBD.insert(MyDBOpenHelper.TABLA_USUARIO, null, values)
    }

    /**
     * Añade un nuevo evento-usuario a la base de datos
     */
    fun addEventosUsuario(idUsuario:Int, idEvento:Int){
        val values = ContentValues()
        values.put(MyDBOpenHelper.COL_ID_USUARIO, idUsuario)
        values.put(MyDBOpenHelper.COL_ID_EVENTO, idEvento)
        mBD.insert(MyDBOpenHelper.TABLA_USUARIO, null, values)
    }

    fun tablasVacias():Boolean{
        
    }



}
