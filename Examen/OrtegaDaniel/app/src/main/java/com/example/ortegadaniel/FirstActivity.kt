package com.example.ortegadaniel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ortegadaniel.databinding.ActivityFirstBinding
import java.util.zip.Inflater

class FirstActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarEmpresa()
        var bd=OperacionesDao(this)
        if (bd.tablasVacias())
            bd.insertarDatos()


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