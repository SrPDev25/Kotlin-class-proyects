package com.mjpg.basedatos.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.ut7ej7ortegadaniel.control.Alumno
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
        if(tablasVacias())
            insertarDatos()
    }


    fun verificar(login:String, pass:String): Profesor? {
        var profesor:Profesor?=null
        val cursor:Cursor=mBD.rawQuery(
            "Select * from ${MyDBOpenHelper.TABLA_PROFESORES}" +
                    " WHERE ${MyDBOpenHelper.COL_LOGIN}='$login'" +
                    "and ${MyDBOpenHelper.COL_CONTRA}='$pass'",
                    null
        )
        //como el .Next de java
        if(cursor.moveToFirst()) profesor=Profesor(
            cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_LOGIN)),
            cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_CONTRA)),
            cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_NOMBRE_PROFESOR)),
            cursor.getInt(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_CODIGO_PROF)))
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
            nombre=cursor.getString(3)

        return nombre
    }

    fun getAlumnos(codigoProf: Int):MutableList<Alumno>{
        var alumnos= mutableListOf<Alumno>()
        var cursor:Cursor=mBD.rawQuery("select * " +
                "from ${MyDBOpenHelper.TABLA_ALUMNOS} as a , ${MyDBOpenHelper.TABLA_PROFESOR_ALUMNO} as p " +
                "where p.${MyDBOpenHelper.COL_CODIGO_ALU}= a.${MyDBOpenHelper.COL_CODIGO_ALU} " +
                "and p.${MyDBOpenHelper.COL_CODIGO_PROF} = $codigoProf " +
                "order by ${MyDBOpenHelper.COL_NOMBRE_ALUMNO} asc"
            ,null)
        while (cursor.moveToNext()){
            alumnos.add(
                Alumno(
                    cursor.getString(1),
                    cursor.getInt(0)
            ))
        }

        return alumnos
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

    fun addAlumno(alumno: Alumno) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.COL_NOMBRE_ALUMNO, alumno.nombre)
        mBD.insert(MyDBOpenHelper.TABLA_ALUMNOS, null, values)
    }

    fun addRelacion(alumno:Int,profesor:Int){
        val values=ContentValues()
        values.put(MyDBOpenHelper.COL_CODIGO_PROF, profesor)
        values.put(MyDBOpenHelper.COL_CODIGO_ALU, alumno)
        mBD.insert(MyDBOpenHelper.TABLA_PROFESOR_ALUMNO,null ,values)
    }


    //Evita que el programa vaya al pete
    //Comprueba si las tablas tienen datos
    fun tablasVacias(): Boolean {
        var vacia:Boolean
        var cursor: Cursor = mBD.query(
            MyDBOpenHelper.TABLA_PROFESORES, null, null,
            null, null, null, null
        )
        vacia=!cursor.moveToFirst()
        cursor.close()
        return vacia
    }

    fun insertarDatos(){
        addProfesor(Profesor(
            "1",
            "1",
            "Pepe"
        ))
        addProfesor(Profesor(
            "2",
            "2",
            "Juan"
        ))

        addAlumno(Alumno(
            "David"
        ))
        addAlumno(Alumno(
            "Antonio"
        ))
        addAlumno(Alumno(
            "Rosa"
        ))
        addAlumno(Alumno(
            "Maria Antonieta"
        ))
        addAlumno(Alumno(
            "Alumno"
        ))
        addAlumno(Alumno(
            "Si"
        ))
        addAlumno(Alumno(
            "Nombre"
        ))

        addRelacion(1,2)
        addRelacion(1,1)
        addRelacion(3,2)
        addRelacion(2,1)
        addRelacion(5,2)
        addRelacion(6,2)
        addRelacion(7,1)
        addRelacion(5,1)


        addFalta(-1, 1,"1/1/1","6")

    }



}
