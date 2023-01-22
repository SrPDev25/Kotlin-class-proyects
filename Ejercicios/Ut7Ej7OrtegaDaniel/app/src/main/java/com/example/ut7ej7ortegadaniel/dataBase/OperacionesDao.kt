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

    fun addObservacion(fecha:String,
                       hora:String,
                       codigoAlu:Int,
                        codigoProf: Int){
        var value=ContentValues()
        value.put(MyDBOpenHelper.COL_OBSERVACIONES,getProfesorName(codigoProf))
        var profesor=getProfesorName(codigoProf)
        var query="update ${MyDBOpenHelper.TABLA_FALTAS}" +
                " set  ${MyDBOpenHelper.COL_OBSERVACIONES} = '$profesor'" +
                " where ${MyDBOpenHelper.COL_FECHA}= '$fecha' " +
                "and ${MyDBOpenHelper.COL_HORA}= $hora " +
                "and ${MyDBOpenHelper.COL_CODIGO_ALU}= $codigoAlu"


        mBD.execSQL(query)
    }

    fun switchJustificada(codFalta:Int){
        var query="select ${MyDBOpenHelper.COL_JUSTIFICADA} " +
                "from ${MyDBOpenHelper.TABLA_FALTAS} " +
                "where ${MyDBOpenHelper.COL_CODIGO_FALTAS}= $codFalta "
        var cursor:Cursor=mBD.rawQuery(query,null)
        cursor.moveToFirst()
        var isJustificada=cursor.getString(0)
        if (isJustificada=="0"){
            query="update ${MyDBOpenHelper.TABLA_FALTAS} " +
                    "set ${MyDBOpenHelper.COL_JUSTIFICADA} = 1 " +
                    "where ${MyDBOpenHelper.COL_CODIGO_FALTAS} = $codFalta"
        }else{
            query="update ${MyDBOpenHelper.TABLA_FALTAS} " +
                    "set ${MyDBOpenHelper.COL_JUSTIFICADA} = 0 " +
                    "where ${MyDBOpenHelper.COL_CODIGO_FALTAS} = $codFalta"
        }

        mBD.execSQL(query)

    }

    fun getProfesorName(codigoProf: Int):String{
        var nombre=""
        var cursor:Cursor=mBD.rawQuery("select ${MyDBOpenHelper.COL_NOMBRE_PROFESOR} " +
                "from ${MyDBOpenHelper.TABLA_PROFESORES} " +
                "where ${MyDBOpenHelper.COL_CODIGO_PROF}='$codigoProf'",
            null)
        if (cursor.moveToFirst())
            nombre=cursor.getString(0)

        return nombre
    }
    fun addFalta(codigoAlu:Int
                 ,codigoProf:Int
                 ,fecha:String,
                 hora:String,
        ):Long{
        var value= ContentValues()
        value.put(MyDBOpenHelper.COL_CODIGO_PROF, codigoProf)
        value.put(MyDBOpenHelper.COL_CODIGO_ALU, codigoAlu)
        value.put(MyDBOpenHelper.COL_FECHA, fecha)
        value.put(MyDBOpenHelper.COL_HORA, hora)
        return mBD.insert(MyDBOpenHelper.TABLA_FALTAS, null, value)


    }

    fun isFaltaExistente(codigoAlu: Int, fecha: String, hora: String):Boolean{

        val cursor:Cursor=mBD.rawQuery(
            "Select * from ${MyDBOpenHelper.TABLA_FALTAS} " +
                    "where ${MyDBOpenHelper.COL_CODIGO_ALU} = '$codigoAlu'" +
                    "and ${MyDBOpenHelper.COL_FECHA} = '$fecha' " +
                    "and ${MyDBOpenHelper.COL_HORA}= '$hora'"
            ,null)

        return cursor.moveToFirst()
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
