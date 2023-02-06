package com.example.ej9ortegadanielbd.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.ej9ortegadanielbd.control.Usuario
import com.mjpg.tiendas.bd.MyDBOpenHelper


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

    fun tablasVacias():Boolean{
        val cursor: Cursor =mBD.rawQuery("Select * from" +
                " ${MyDBOpenHelper.TABLA_TIPO_PROFESIONAL} ",null)
        val isVacia=!cursor.moveToFirst()
        if (!cursor.isClosed)
            cursor.close()
        return isVacia
    }

    //TODO al crear al usuario hay que crear todas sus relaciones
    fun addUsuario(nombre:String){
        val values=ContentValues()
        values.put(MyDBOpenHelper.NOMBRE_USUARIO,nombre)
        mBD.insert(MyDBOpenHelper.TABLA_USUARIO,null,values)
    }

    fun addProfesional(tipoProfesional:Int, nombre:String){
        val values=ContentValues()
        values.put(MyDBOpenHelper.COD_TIPO_PROFESIONAL,tipoProfesional)
        values.put(MyDBOpenHelper.NOMBRE_PROFESIONAL,nombre)
        mBD.insert(MyDBOpenHelper.TABLA_PROFESIONAL,null,values)
    }

    fun addRelacion(numAfiliado:Int, numColegiado:Int){
        val values=ContentValues()
        values.put(MyDBOpenHelper.NUM_AFILIADO,numAfiliado)
        values.put(MyDBOpenHelper.NUM_COLEGIADO,numColegiado)
        mBD.insert(MyDBOpenHelper.TABLA_RALACION_PROF_USU,null,values)
    }
    fun addTipoProfesional( nombre:String){
        val values=ContentValues()
        values.put(MyDBOpenHelper.DESCRIPCION_TIPO,nombre)
        mBD.insert(MyDBOpenHelper.TABLA_TIPO_PROFESIONAL,null,values)
    }

    fun addCita(fecha:String, hora:String, tipoProfesional:Int,numAfiliado:Int){
        val values=ContentValues()
        values.put(MyDBOpenHelper.FECHA,fecha)
        values.put(MyDBOpenHelper.HORA,hora)
        values.put(MyDBOpenHelper.NUM_AFILIADO,numAfiliado)
        values.put(MyDBOpenHelper.COD_TIPO_PROFESIONAL,tipoProfesional)

        mBD.insert(MyDBOpenHelper.TABLA_CITAS,null,values)
    }

    fun getUsuarios():MutableList<Usuario>{
        var list= mutableListOf<Usuario>()
        var cursor:Cursor=mBD.rawQuery("select * from ${MyDBOpenHelper.TABLA_USUARIO}",
        null)
        while(cursor.moveToNext())
            list.add(Usuario(
                cursor.getInt(0),
                cursor.getString(1)
            ))

        return list
    }


    fun insertarDatos(){
        addUsuario("ELPEPE")
        addUsuario("Carlos I de España V de Alemania")
        addTipoProfesional("Enfermeria")
        addTipoProfesional("Matrona")
        addTipoProfesional("Medicina")
        addTipoProfesional("Trabajador social")
        addTipoProfesional("Extracciones")
        addProfesional(2,"Juan")
        addProfesional(1,"Juanjo")
        addProfesional(3,"Juan Antonio")
        addProfesional(4,"Alvar")
        addProfesional(5,"Paco")
        addRelacion(2,1)
        addRelacion(2,5)
        addRelacion(1,2)
        addCita("2023/03/15","13:30",2,1)

    }




}
