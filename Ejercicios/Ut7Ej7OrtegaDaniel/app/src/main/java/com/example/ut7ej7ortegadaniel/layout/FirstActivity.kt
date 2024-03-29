package com.example.ut7ej7ortegadaniel.layout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ut7ej7ortegadaniel.databinding.ActivityFirstBinding
import com.example.ut7ej7ortegadaniel.tool.MyXMLReader

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.whiteLayout.background.alpha=200
        val xmlReader = MyXMLReader()
        val context = applicationContext.assets.open("centro.xml")
        val centro=xmlReader.parse(context)

        binding.centroTxt.text=centro?.denominacion
        binding.direccionTxt.text=centro?.direccion
        binding.telefonoTxt.text=centro?.telefono

        val hilo=Thread{
            Thread.sleep(3*1000)
            val myIntent= Intent(this, LoginActivity::class.java)
            startActivity(myIntent)
        }
        hilo.start()
    }
}