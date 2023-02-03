package com.example.ej9ortegadanielbd.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ej9ortegadanielbd.databinding.ActivityFirstBinding
import com.example.ut7ej7ortegadaniel.dataBase.OperacionesDao

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    private lateinit var bd:OperacionesDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bd=OperacionesDao(this)

        if(bd.tablasVacias()){
            bd.insertarDatos()
        }

        Thread{
            Thread.sleep(3*1000)
            val myIntent= Intent(this, CardsActivity::class.java)
            startActivity(intent)
        }.start()

    }
}