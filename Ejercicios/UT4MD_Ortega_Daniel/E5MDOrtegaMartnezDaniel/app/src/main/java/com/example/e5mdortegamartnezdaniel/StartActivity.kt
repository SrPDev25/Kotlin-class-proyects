package com.example.e5mdortegamartnezdaniel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.e5mdortegamartnezdaniel.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity(),Events {
    lateinit var binding: ActivityStartBinding
    lateinit var linearLayout: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgLogo.setOnClickListener(){
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
        //Crea el metodo de long click y no hace nada
        //devuelve true para indicar que existe el método y que lo tenga en cuenta
        binding.imgLogo.setOnLongClickListener { true }
        //Cargar imagen
        Glide.with(this)
            .load("https://digitalhospital.com.sg/wp-content/uploads/2020/05/cropped-Digital-Hospital-Logo-FavIcon-2.png")
            .into(binding.imgLogo)
        binding.whiteLayout.background.alpha=200

    }

    override fun shortKeyStroke() {

    }

    override fun longKeyStroke(): Boolean {
        return false
    }

}
