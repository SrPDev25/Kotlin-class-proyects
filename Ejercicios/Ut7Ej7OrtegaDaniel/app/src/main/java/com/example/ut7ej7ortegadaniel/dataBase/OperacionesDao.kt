package com.example.ut7ej7ortegadaniel.dataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.ut7ej7ortegadaniel.control.Alumno
import com.example.ut7ej7ortegadaniel.control.Falta
import com.example.ut7ej7ortegadaniel.control.Profesor


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


    fun verificar(login: String, pass: String): Profesor? {
        var profesor: Profesor? = null
        val cursor: Cursor = mBD.rawQuery(
            "Select * from ${MyDBOpenHelper.TABLA_PROFESORES}" +
                    " WHERE ${MyDBOpenHelper.COL_LOGIN}='$login'" +
                    "and ${MyDBOpenHelper.COL_CONTRA}='$pass'",
            null
        )
        //como el .Next de java
        if (cursor.moveToFirst()) profesor = Profesor(
            cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_LOGIN)),
            cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_CONTRA)),
            cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_NOMBRE_PROFESOR)),
            cursor.getInt(cursor.getColumnIndexOrThrow(MyDBOpenHelper.COL_CODIGO_PROF))
        )
        if (!cursor.isClosed)
            cursor.close()//Cerrar base de datos?

        return profesor
    }


    @SuppressLint("Recycle")
    fun switchJustificada(codFalta: Int) {
        var query = "select ${MyDBOpenHelper.COL_JUSTIFICADA} " +
                "from ${MyDBOpenHelper.TABLA_FALTAS} " +
                "where ${MyDBOpenHelper.COL_CODIGO_FALTAS}= $codFalta "
        val cursor: Cursor = mBD.rawQuery(query, null)
        cursor.moveToFirst()
        val isJustificada = cursor.getString(0)
        query = if (isJustificada == "0") {
            "update ${MyDBOpenHelper.TABLA_FALTAS} " +
                    "set ${MyDBOpenHelper.COL_JUSTIFICADA} = 1 " +
                    "where ${MyDBOpenHelper.COL_CODIGO_FALTAS} = $codFalta"
        } else {
            "update ${MyDBOpenHelper.TABLA_FALTAS} " +
                    "set ${MyDBOpenHelper.COL_JUSTIFICADA} = 0 " +
                    "where ${MyDBOpenHelper.COL_CODIGO_FALTAS} = $codFalta"
        }

        mBD.execSQL(query)

    }

    private fun getProfesorName(codigoProf: Int): String {
        var nombre = ""
        val cursor: Cursor = mBD.rawQuery(
            "select ${MyDBOpenHelper.COL_NOMBRE_PROFESOR} " +
                    "from ${MyDBOpenHelper.TABLA_PROFESORES} " +
                    "where ${MyDBOpenHelper.COL_CODIGO_PROF}='$codigoProf'",
            null
        )
        if (cursor.moveToFirst())
            nombre = cursor.getString(0)

        if (!cursor.isClosed)
            cursor.close()
        return nombre
    }

    fun getAlumnos(codigoProf: Int): MutableList<Alumno> {
        val alumnos = mutableListOf<Alumno>()
        val cursor: Cursor = mBD.rawQuery(
            "select * " +
                    "from ${MyDBOpenHelper.TABLA_ALUMNOS} as a , ${MyDBOpenHelper.TABLA_PROFESOR_ALUMNO} as p " +
                    "where p.${MyDBOpenHelper.COL_CODIGO_ALU}= a.${MyDBOpenHelper.COL_CODIGO_ALU} " +
                    "and p.${MyDBOpenHelper.COL_CODIGO_PROF} = $codigoProf " +
                    "order by ${MyDBOpenHelper.COL_NOMBRE_ALUMNO} asc", null
        )
        while (cursor.moveToNext()) {
            alumnos.add(
                Alumno(
                    cursor.getString(1),
                    cursor.getInt(0)
                )
            )
        }

        if (!cursor.isClosed)
            cursor.close()
        return alumnos
    }

    /**
     * Obtengo el código del profesor que ha creado la falta
     */
    fun getCodProfesorFalta(hora: String, fecha: String, alumno: Int): Int {
        var codigo = -1
        val cursor: Cursor = mBD.rawQuery(
            "select ${MyDBOpenHelper.COL_CODIGO_PROF} " +
                    "from ${MyDBOpenHelper.TABLA_FALTAS} " +
                    "where ${MyDBOpenHelper.COL_HORA}= $hora " +
                    "and ${MyDBOpenHelper.COL_FECHA}='$fecha' " +
                    "and ${MyDBOpenHelper.COL_CODIGO_ALU} = $alumno", null
        )
        if (cursor.moveToFirst())
            codigo = cursor.getInt(0)
        if (!cursor.isClosed)
            cursor.close()
        return codigo

    }

    fun getFaltas(alumno: Int): MutableList<Falta> {
        val faltas = mutableListOf<Falta>()
        val cursor: Cursor = mBD.rawQuery(
            "select * " +
                    "from ${MyDBOpenHelper.TABLA_FALTAS} " +
                    "where ${MyDBOpenHelper.COL_CODIGO_ALU} = $alumno", null
        )
        while (cursor.moveToNext()) {
            faltas.add(
                Falta(
                    cursor.getString(6),
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)
                )
            )
        }
        return faltas
    }


    /**
     * Returna todas las faltas de un alumno
     */
    @SuppressLint("Recycle")
    fun isFaltaExistente(codigoAlu: Int, fecha: String, hora: String): Boolean {

        val cursor: Cursor = mBD.rawQuery(
            "Select * from ${MyDBOpenHelper.TABLA_FALTAS} " +
                    "where ${MyDBOpenHelper.COL_CODIGO_ALU} = '$codigoAlu'" +
                    "and ${MyDBOpenHelper.COL_FECHA} = '$fecha' " +
                    "and ${MyDBOpenHelper.COL_HORA}= '$hora'", null
        )

        return cursor.moveToFirst()
    }

    /**
     * Añade una falta
     */
    fun addFalta(
        codigoAlu: Int, codigoProf: Int, fecha: String,
        hora: String,
        justificada: Int
    ): Long {
        val value = ContentValues()
        value.put(MyDBOpenHelper.COL_CODIGO_PROF, codigoProf)
        value.put(MyDBOpenHelper.COL_CODIGO_ALU, codigoAlu)
        value.put(MyDBOpenHelper.COL_FECHA, fecha)
        value.put(MyDBOpenHelper.COL_HORA, hora)
        value.put(MyDBOpenHelper.COL_JUSTIFICADA, justificada)
        return mBD.insert(MyDBOpenHelper.TABLA_FALTAS, null, value)


    }

    /**
     *
     */
    fun addObservacion(
        fecha: String,
        hora: String,
        codigoAlu: Int,
        codigoProf: Int
    ) {

        val profesor = getProfesorName(codigoProf)
        val query = "update ${MyDBOpenHelper.TABLA_FALTAS}" +
                " set  ${MyDBOpenHelper.COL_OBSERVACIONES} = '$profesor'" +
                " where ${MyDBOpenHelper.COL_FECHA}= '$fecha' " +
                "and ${MyDBOpenHelper.COL_HORA}= $hora " +
                "and ${MyDBOpenHelper.COL_CODIGO_ALU}= $codigoAlu"


        mBD.execSQL(query)
    }

    private fun addProfesor(profesor: Profesor) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.COL_LOGIN, profesor.login)
        values.put(MyDBOpenHelper.COL_CONTRA, profesor.pass)
        values.put(MyDBOpenHelper.COL_NOMBRE_PROFESOR, profesor.nombre)
        mBD.insert(MyDBOpenHelper.TABLA_PROFESORES, null, values)
    }

    private fun addAlumno(alumno: Alumno) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.COL_NOMBRE_ALUMNO, alumno.nombre)
        mBD.insert(MyDBOpenHelper.TABLA_ALUMNOS, null, values)
    }

    private fun addRelacion(alumno: Int, profesor: Int) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.COL_CODIGO_PROF, profesor)
        values.put(MyDBOpenHelper.COL_CODIGO_ALU, alumno)
        mBD.insert(MyDBOpenHelper.TABLA_PROFESOR_ALUMNO, null, values)
    }


    //Evita que el programa vaya al pete
    //Comprueba si las tablas tienen datos
    private fun tablasVacias(): Boolean {
        val vacia: Boolean
        val cursor: Cursor = mBD.query(
            MyDBOpenHelper.TABLA_PROFESORES, null, null,
            null, null, null, null
        )
        vacia = !cursor.moveToFirst()
        cursor.close()
        return vacia
    }

    private fun insertarDatos() {
        addProfesor(
            Profesor(
                "1",
                "1",
                "Pepe"
            )
        )
        addProfesor(
            Profesor(
                "2",
                "2",
                "Juan"
            )
        )

        addAlumno(
            Alumno(
                "David"
            )
        )
        addAlumno(
            Alumno(
                "Antonio"
            )
        )
        addAlumno(
            Alumno(
                "Rosa"
            )
        )
        addAlumno(
            Alumno(
                "Maria Antonieta"
            )
        )
        addAlumno(
            Alumno(
                "Alumno"
            )
        )
        addAlumno(
            Alumno(
                "Si"
            )
        )
        addAlumno(
            Alumno(
                "Nombre"
            )
        )

        addRelacion(1, 2)
        addRelacion(1, 1)
        addRelacion(3, 2)
        addRelacion(2, 1)
        addRelacion(5, 2)
        addRelacion(6, 2)
        addRelacion(7, 1)
        addRelacion(5, 1)


        addFalta(1, 1, "23/01/2023", "6", 1)
        addFalta(1, 2, "22/01/2023", "1", 0)
        addFalta(1, 2, "25/01/2023", "3", 0)
        addFalta(2, 1, "26/01/2023", "6", 1)
        addFalta(3, 1, "23/01/2023", "6", 1)
        addFalta(3, 2, "23/01/2023", "1", 0)
        addFalta(3, 2, "21/01/2023", "3", 0)
        addFalta(4, 1, "23/01/2023", "6", 1)
        addFalta(4, 2, "27/01/2023", "1", 0)
        addFalta(4, 2, "23/01/2023", "3", 1)


    }


}
