package com.example.ut7ej8ortegamartinezdaniel.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ut7ej7ortegadaniel.dataBase.OperacionesDao
import com.example.ut7ej8ortegamartinezdaniel.R
import com.example.ut7ej8ortegamartinezdaniel.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bd=OperacionesDao(this)
        if (bd.tablasVacias()) {
            bd.insertarDatos()
            bd.insertUsuarios(applicationContext.assets.open("usuarios.xml"))
        }
        binding.whiteLayout.background.alpha=200
        Thread{
            Thread.sleep(3*1000)
            val myIntent=Intent(this,LoginActivity::class.java)
            startActivity(myIntent)
        }.start()

    }
}