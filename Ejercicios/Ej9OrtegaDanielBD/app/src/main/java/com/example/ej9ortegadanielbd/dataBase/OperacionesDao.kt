package com.example.ej9ortegadanielbd.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.ej9ortegadanielbd.control.Cita
import com.example.ej9ortegadanielbd.control.Profesional
import com.example.ej9ortegadanielbd.control.TipoProfesional
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

    fun tablasVacias(): Boolean {
        val cursor: Cursor = mBD.rawQuery(
            "Select * from" +
                    " ${MyDBOpenHelper.TABLA_TIPO_PROFESIONAL} ", null
        )
        val isVacia = !cursor.moveToFirst()
        if (!cursor.isClosed)
            cursor.close()
        return isVacia
    }


    fun addUsuario(nombre: String) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.NOMBRE_USUARIO, nombre)
        mBD.insert(MyDBOpenHelper.TABLA_USUARIO, null, values)
    }

    fun addProfesional(tipoProfesional: Int, nombre: String) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.COD_TIPO_PROFESIONAL, tipoProfesional)
        values.put(MyDBOpenHelper.NOMBRE_PROFESIONAL, nombre)
        mBD.insert(MyDBOpenHelper.TABLA_PROFESIONAL, null, values)
    }

    fun addRelacion(numAfiliado: Int, numColegiado: Int) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.NUM_AFILIADO, numAfiliado)
        values.put(MyDBOpenHelper.NUM_COLEGIADO, numColegiado)
        mBD.insert(MyDBOpenHelper.TABLA_RALACION_PROF_USU, null, values)
    }

    fun addTipoProfesional(nombre: String) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.DESCRIPCION_TIPO, nombre)
        mBD.insert(MyDBOpenHelper.TABLA_TIPO_PROFESIONAL, null, values)
    }

    fun addCita(fecha: String, hora: String, tipoProfesional: Int, numAfiliado: Int) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.FECHA, fecha)
        values.put(MyDBOpenHelper.HORA, hora)
        values.put(MyDBOpenHelper.NUM_AFILIADO, numAfiliado)
        values.put(MyDBOpenHelper.COD_TIPO_PROFESIONAL, tipoProfesional)

        mBD.insert(MyDBOpenHelper.TABLA_CITAS, null, values)
    }

    fun getUsuarios(): MutableList<Usuario> {
        var list = mutableListOf<Usuario>()
        var cursor: Cursor = mBD.rawQuery(
            "select * from ${MyDBOpenHelper.TABLA_USUARIO}",
            null
        )
        while (cursor.moveToNext())
            list.add(
                Usuario(
                    cursor.getInt(0),
                    cursor.getString(1)
                )
            )

        return list
    }

    /**
     * Devulve todas las citas de un usuario
     */
    fun getCitas(numAfiliado: Int): MutableList<Cita> {
        var citas = mutableListOf<Cita>()
        //El select completo donde se obtienen las citas del usuario y los datos que vamos a añadir en el recycler para identificarlas
        val cursor: Cursor = mBD.rawQuery(
            "select c.id, c.fecha,c.hora,c.cod_tipo, p.nombre, tp.descripcion " +
                    "from citas c, profesional_usuario pu, tipo_profesional tp, profesionales p " +
                    "where c.num_afiliacion=pu.num_afiliacion and pu.num_colegiado=p.num_colegiado " +
                    "and c.cod_tipo=p.cod_tipo and tp.cod_tipo=c.cod_tipo " +
                    "and c.num_afiliacion=$numAfiliado", null
        )
        while (cursor.moveToNext()) {
            citas.add(
                Cita(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    numAfiliado,
                    cursor.getString(4),
                    cursor.getString(
                        (5),
                    )
                )
            )
        }
        return citas
    }

    /**
     * Devulve todas las citas de un usuario
     */
    fun getTipoProfesional(): MutableList<TipoProfesional> {
        var tipoProfesionals = mutableListOf<TipoProfesional>()
        //El select completo donde se obtienen las citas del usuario y los datos que vamos a añadir en el recycler para identificarlas
        val cursor: Cursor = mBD.rawQuery(
            "select ${MyDBOpenHelper.COD_TIPO_PROFESIONAL}, ${MyDBOpenHelper.DESCRIPCION_TIPO} " +
                    "from ${MyDBOpenHelper.TABLA_TIPO_PROFESIONAL}", null
        )
        while (cursor.moveToNext()) {
            tipoProfesionals.add(
                TipoProfesional(
                    cursor.getInt(0),
                    cursor.getString(1)
                )
            )

        }
        return tipoProfesionals
    }

    fun getProfesionales(tipoProfesional: Int): MutableList<Profesional> {
        val listaProfesional= mutableListOf<Profesional>()
        val cursor:Cursor=mBD.rawQuery("select * from ${MyDBOpenHelper.TABLA_PROFESIONAL} " +
                "where ${MyDBOpenHelper.COD_TIPO_PROFESIONAL}=$tipoProfesional",null)
        while (cursor.moveToNext()){
            listaProfesional.add(Profesional(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2)
            ))
        }

        return listaProfesional
    }

    fun isProfesionalOcupado(fecha: String,hora: String,numAfiliado: Int,tipoProfesional: Int):Boolean{
        val cursor:Cursor=mBD.rawQuery(
                "select c.* from citas c join profesional_usuario r on r.num_afiliacion=c.num_afiliacion " +
                "where r.num_colegiado=(select r.num_colegiado  from profesional_usuario r " +
                        "join citas c on c.num_afiliacion=r.num_afiliacion join profesionales p on " +
                        "r.num_colegiado=p.num_colegiado where c.num_afiliacion=$numAfiliado and p.cod_tipo=$tipoProfesional group by r.id limit 1)"+
                " and fecha=\"$fecha\" and hora=\"$hora\"",null)


        return cursor.moveToFirst()
    }
//select r.num_colegiado  from profesional_usuario r join citas c on c.num_afiliacion=r.num_afiliacion join profesionales p on r.num_colegiado=p.num_colegiado where c.num_afiliacion=1 and p.cod_tipo=1 group by r.id
//select c.* from citas c join profesional_usuario r on r.num_afiliacion=c.num_afiliacion where r.num_colegiado=1 and fecha="23/11/2023" and hora="15:30"


    fun insertarDatos() {
        addUsuario("Nieves")
        addUsuario("Carlos I de España V de Alemania")
        addTipoProfesional("Enfermeria")
        addTipoProfesional("Matrona")
        addTipoProfesional("Medicina")
        addTipoProfesional("Trabajador social")
        addTipoProfesional("Extracciones")

        addProfesional(1, "Juanjo")
        addProfesional(2, "Juan")
        addProfesional(3, "Juan Antonio")
        addProfesional(4, "Alvar")
        addProfesional(5, "Paco")
        addProfesional(2, "Andres")
        addProfesional(1, "Marina")
        addProfesional(3, "María")
        addProfesional(5, "Alberto")
        addProfesional(1, "Ejemplo")

        addRelacion(2, 1)
        addRelacion(2, 6)
        addRelacion(2, 3)
        addRelacion(2, 4)
        addRelacion(2, 5)
        addRelacion(1, 2)
        addRelacion(1, 1)
        addRelacion(1, 3)
        addRelacion(1, 4)
        addRelacion(1, 5)

        addCita("15/03/2023", "13:30", 2, 1)
        addCita("23/05/2023", "15:30", 5, 1)
        addCita("23/11/2023", "15:30", 3, 2)
        addCita("11/11/2023", "11:10", 3, 2)

    }


}
