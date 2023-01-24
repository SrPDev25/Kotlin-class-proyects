package com.example.ut7ej7ortegadaniel.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ut7ej7ortegadaniel.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.example.ut7ej7ortegadaniel.dataBase.OperacionesDao


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db= OperacionesDao(this)



        binding.whiteLayout.background.alpha = 200
        binding.buttonLogin.setOnClickListener() {
            val user = binding.userText.text.toString()
            val pass = binding.passText.text.toString()
            val profesor=db.verificar(user, pass)
            if(profesor!=null){
                val myIntent= Intent(this,MainActivity::class.java)
                    .putExtra("Profesor",profesor.codigo)
                startActivity(myIntent)
            }else{
                Snackbar.make(
                    binding.root,
                    "Usuario no encontrado",
                    Snackbar.LENGTH_LONG
                ).show()
            }

        }

    }

}