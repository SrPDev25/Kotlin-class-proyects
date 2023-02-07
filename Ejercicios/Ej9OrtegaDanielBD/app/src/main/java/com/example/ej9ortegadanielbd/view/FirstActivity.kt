package com.example.ej9ortegadanielbd.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    private lateinit var bd:OperacionesDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bd= OperacionesDao(this)

        if(bd.tablasVacias()){
            bd.insertarDatos()
        }

        Thread{
            Thread.sleep(3*1000)
            val myIntent= Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }.start()

    }
}