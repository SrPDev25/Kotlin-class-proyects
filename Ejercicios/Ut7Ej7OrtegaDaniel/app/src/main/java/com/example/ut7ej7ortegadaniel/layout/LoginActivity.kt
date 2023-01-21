package com.example.ut7ej7ortegadaniel.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ut7ej7ortegadaniel.control.Profesor
import com.example.ut7ej7ortegadaniel.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.mjpg.basedatos.dao.OperacionesDao


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var db=OperacionesDao(this)

        /*db.addProfesor(Profesor(
            "1",
            "1",
            "",
            "Pepe"
        ))*/
        binding.whiteLayout.background.alpha = 200
        binding.buttonLogin.setOnClickListener() {
            val user = binding.userText.text.toString()
            val pass = binding.passText.text.toString()
            var profesor=db.verificar(user, pass)
            if(profesor!=null){
                Snackbar.make(
                    binding.root,
                    "Verificado",
                    Snackbar.LENGTH_LONG
                ).show()
            }else{
                Snackbar.make(
                    binding.root,
                    "No verificado",
                    Snackbar.LENGTH_LONG
                ).show()
            }

        }

    }

}