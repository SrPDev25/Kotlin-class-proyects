package com.example.ortegadaniel.Vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ortegadaniel.MyXMLReader
import com.example.ortegadaniel.OperacionesDao
import com.example.ortegadaniel.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarEmpresa()
        var bd = OperacionesDao(this)
        if (bd.tablasVacias())
            bd.insertarDatos()
        Thread {
            Thread.sleep(3 * 1000)
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }.start()

    }

    private fun cargarEmpresa() {
        Glide.with(this)
            .load("https://1000marcas.net/wp-content/uploads/2021/05/Hard-Rock-Cafe-Logo-500x313.png")
            .into(binding.logo)
        val xmlReader = MyXMLReader()
        val context = applicationContext.assets.open("empresa.xml")
        val empresa = xmlReader.parse(context)
        binding.cifTxt.text = empresa?.cif
        binding.denominacionTxt.text = empresa?.denominacion
    }
}