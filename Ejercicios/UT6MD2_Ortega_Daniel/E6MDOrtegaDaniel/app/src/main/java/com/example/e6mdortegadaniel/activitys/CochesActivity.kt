package com.example.e6mdortegadaniel.activitys

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e6mdortegadaniel.Events
import com.example.e6mdortegadaniel.ListenerAdapter

import com.example.e6mdortegadaniel.control.Control
import com.example.e6mdortegadaniel.control.Usuario
import com.example.e6mdortegadaniel.control.Vehiculo
import com.example.e6mdortegadaniel.databinding.ActivityCochesBinding

class CochesActivity : AppCompatActivity(), Events {
    private lateinit var binding: ActivityCochesBinding
    private lateinit var linearLayout: LinearLayoutManager
    private var control= Control()
    private var estado:Int=-2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCochesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        chargeRecycler()
        estado=intent.getIntExtra("tipo",-2)

        if (estado==Usuario.MECANICO)
            binding.buttonAdd.isVisible=false

        binding.buttonAdd.setOnClickListener(){
            val myIntent = Intent(this, AddCocheActivity::class.java)
            startForResult.launch(myIntent)
        }

    }

    override fun longClick(pos: Int): Boolean {

        if (!control.vehiculos[pos].estado && estado== Usuario.MECANICO){
            control.vehiculos[pos].estado=true
            chargeRecycler()
            enviarEmail(pos)
        }
        return true
    }

    override fun shortClick(pos: Int) {
        if (control.vehiculos[pos].estado && estado== Usuario.RECEPCIONISTA){
            control.vehiculos.remove(control.vehiculos[pos])
            chargeRecycler()
        }
    }

    /** Carga el recyclerView para actualizar los nuevos cambios
     */
    private fun chargeRecycler() {
        binding.recyclerview.adapter = ListenerAdapter(control.vehiculos, this)
        linearLayout = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayout
        binding.recyclerview.setHasFixedSize(true)
    }



    /**
     * En via un correo rellenando el mail, el asunto y el contenido
     * Psdt: No funciona con Gmail de mi movil
     * todo pelearse con el gmail
     */
    fun enviarEmail(pos:Int) {
        val vehiculo=control.vehiculos.get(pos)
        val address= arrayOf( vehiculo.email)
        val mensaje= "Hola, ${vehiculo.nombre}\n\n" +
                "Le mando este mensaje del taller de coches para informarle que su vehiculo" +
                " ${vehiculo.modelo} con matricula ${vehiculo.matricula} ha sido reparado y está " +
                "listo para ser recogido." +
                "\nUn saludo, Taller San Vicente"
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // Llama a la aplicación de correo
        intent.putExtra(Intent.EXTRA_EMAIL,address)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Reparación")
        intent.putExtra(Intent.EXTRA_TEXT,mensaje)

        //Llama al intent indicado
        startActivity(intent)
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result->
        //Comprueba que si hay resultado, el intent tiene el codigo RESULT_OK
        //si salta un error no lo devuelve
        if (result.resultCode == Activity.RESULT_OK) {
            //"vehicula" es el nombre para recibir después el dato
            val intent: Vehiculo? = result.data?.getParcelableExtra("vehiculo")
            if (intent!=null){
                control.vehiculos.add(intent)
                chargeRecycler()
            }
        }
    }
}


