package com.example.ut7ej7ortegadaniel.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.ut7ej8ortegamartinezdaniel.control.Evento
import com.example.ut7ej8ortegamartinezdaniel.control.MyXMLReader
import com.example.ut7ej8ortegamartinezdaniel.control.Usuario
import java.io.InputStream
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
        val hourPeaces=hora.split(":")
        //Error en la clase date, los años van a aprtir del 1900
        var date= Date(peaces[2].toInt()-1900,peaces[1].toInt(),peaces[0].toInt(),hourPeaces[0].toInt(),hourPeaces[1].toInt())
        val resultado=date.time<System.currentTimeMillis()//Se transforma la fecha en milisegundos deslde el año 0
        return resultado
    }

    fun getEventosOrdenados():MutableList<Evento>{
        var eventos=getEventos()
        var eventosOrdenados= mutableListOf<Evento>()
        var currentMaxFecha=0L
        var n=0L
        var pos=0
        var posToDelete=0
        var date: Date? =null
        var fecha:List<String>
        var hora:List<String>

        while(eventos.size!=0){
            currentMaxFecha=Long.MAX_VALUE
            pos=0
            while (pos < eventos.size){
                fecha=eventos.get(pos).fecha.split("/")
                hora=eventos.get(pos).hora.split(":")
                //Transforma la fecha y hora en milisegundos
                n=Date(fecha[2].toInt(),fecha[1].toInt(),fecha[0].toInt(),
                    hora[0].toInt(),hora[1].toInt()).time
                //Se van guardando la fecha más nueva
                if (n<currentMaxFecha){
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
        mBD.insert(MyDBOpenHelper.TABLA_EVENTOS_USUARIO, null, values)
    }

    fun changeEvento(date:Date,idEvento: Int){
        val fecha=date.day+(date.month+1)+date.year
        val hora="${date.hours} : ${date.minutes}"
        val values=ContentValues()
        values.put(MyDBOpenHelper.COL_FECHA_EVENTO,fecha)
        values.put(MyDBOpenHelper.COL_HORA,hora)
        mBD.update(MyDBOpenHelper.TABLA_EVENTOS,values,"${MyDBOpenHelper.COL_ID_EVENTO}=$idEvento",null)
    }

    fun isEventoUsuarioExist(idUsuario: Int, idEvento: Int):Boolean{
        var cursor:Cursor=mBD.rawQuery("select * from ${MyDBOpenHelper.TABLA_EVENTOS_USUARIO}" +
                " where ${MyDBOpenHelper.COL_ID_USUARIO}= $idUsuario" +
                " and ${MyDBOpenHelper.COL_ID_EVENTO}= $idEvento" ,null)
        return cursor.moveToFirst()
    }

    fun removeEventosUsuario(idUsuario: Int, idEvento: Int){
        mBD.delete(MyDBOpenHelper.TABLA_EVENTOS_USUARIO,"${MyDBOpenHelper.COL_ID_USUARIO}= $idUsuario and ${MyDBOpenHelper.COL_ID_EVENTO}= $idEvento",null )
        //                " and ${MyDBOpenHelper.COL_ID_USUARIO}= $idEvento")
        //"delete from ${MyDBOpenHelper.TABLA_EVENTOS_USUARIO}" +
        //                " where ${MyDBOpenHelper.COL_ID_USUARIO}= $idUsuario" +
        //                " and ${MyDBOpenHelper.COL_ID_USUARIO}= $idEvento",null
    }


    fun tablasVacias():Boolean{
        val cursor:Cursor=mBD.rawQuery("Select * from" +
                " ${MyDBOpenHelper.TABLA_EVENTOS} ",null)
        val isVacia=!cursor.moveToFirst()
        if (!cursor.isClosed)
            cursor.close()
        return isVacia
    }



    fun insertUsuarios(xml:InputStream){
        var xmlReader=MyXMLReader()
        var usuarios=xmlReader.parse(xml)
        //var usuarios=xmlReader.parse(assets.)
        for(i in usuarios){
            addUsuario(i.login,i.contra,i.perfil)
        }
    }

    fun insertarDatos(){
        addEvento("15/2/2023","15:30","Ejemplo1","Evento default")
        addEvento("24/2/2022","15:30","Ejemplo2","Evento default")
        addEvento("15/4/2023","15:30","Ejemplo3","Evento default")
        addEvento("15/7/2023","15:30","Ejemplo4","Evento default")
        addEvento("15/12/2023","15:30","Ejemplo5","Evento default")

        addEventosUsuario(1,1)
        addEventosUsuario(1,3)
    }



}
