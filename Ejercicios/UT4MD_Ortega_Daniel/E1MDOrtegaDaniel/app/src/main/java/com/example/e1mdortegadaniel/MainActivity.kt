package com.example.e1mdortegadaniel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.e1mdortegadaniel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load("https://librosking.com/wp-content/uploads/2022/04/Logo-Libros-king-2022.1.png")
            .into(binding.imgLogo)
        binding.whiteLayout.background.alpha=200
        binding.main.buttonCredits.background.alpha=0



        //Inicia el second activity ejecutando también el SecondActivity.kt
        binding.main.buttonLogin.setOnClickListener {
            val myIntent = Intent(this, SecondActivity::class.java)
            //myIntent.putExtra()//Learning:    método con el que puedes enviarle datos en una variable tipo Bundle
            startActivity(myIntent)
        }

        binding.main.buttonCredits.setOnClickListener {
            val myIntent =Intent(this,CreditsActivity::class.java)
            startActivity(myIntent)
        }




    }
}


