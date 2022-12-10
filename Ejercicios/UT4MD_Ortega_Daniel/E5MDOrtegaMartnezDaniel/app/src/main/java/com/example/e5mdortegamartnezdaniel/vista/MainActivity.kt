package com.example.e5mdortegamartnezdaniel.vista

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.e5mdortegamartnezdaniel.control.Control
import com.example.e5mdortegamartnezdaniel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var control: Control
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        control = intent.getParcelableExtra("baseDatos")!!
        Glide.with(this)
            .load("https://digitalhospital.com.sg/wp-content/uploads/2020/05/cropped-Digital-Hospital-Logo-FavIcon-2.png")
            .into(binding.imgLogo)
        //TODO hacer comprobaciones de los text
        binding.imgLogo.setOnClickListener { finish() }
        binding.especialidadButton.setOnClickListener() {
            information()

        }

    }
    /**
     * Nueva forma que sustituye al startForResult
     */
    private fun MainActivity.information() {
        //Crea el intent que pasa la base de datos (donde está el conjunto de especialidades)
        val myIntent = Intent(this, InformationActivity::class.java)
            .putExtra("dataBase", control)
        //Ejecuta un registerForActivity result, con el nuevo método .launch
        startForResult.launch(myIntent)
    }

    /**
     * ???
     * Valor de tipo ActivityResultContracts, clase que controla los los permisos y los datos
     * que se reciben de un activity o aplicación llamada
     */
    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result->
        //Comprueba que si hay resultado, el intent tiene el codigo RESULT_OK
        //si salta un error no lo devuelve
        if (result.resultCode == Activity.RESULT_OK) {
            //Se saca el int con un codigo por defecto, por si es nulo
            val intent = result.data?.getIntExtra("code",-1)
            binding.especialidadText.text=intent.toString()
        }
    }





}