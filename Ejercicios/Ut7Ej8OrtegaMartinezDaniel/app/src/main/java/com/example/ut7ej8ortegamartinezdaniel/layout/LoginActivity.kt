package com.example.ut7ej8ortegamartinezdaniel.layout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ut7ej7ortegadaniel.dataBase.OperacionesDao
import com.example.ut7ej8ortegamartinezdaniel.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.whiteLayout.background.alpha=200
        val bd=OperacionesDao(this)
        binding.buttonLogin.setOnClickListener(){

            val usuario=bd.verificar(binding.userText.text.toString(),binding.passText.text.toString())
            if (usuario!=null){

                if (usuario.perfil=="A"){
                    val myIntent=Intent(this,MainActivity::class.java)
                        .putExtra("usuario",usuario.id)
                    startActivity(myIntent)
                }else if(usuario.perfil=="U"){
                    val myIntent=Intent(this,EventosActivity::class.java)
                        .putExtra("usuario",usuario.id)
                    startActivity(myIntent)
                }else{
                    Snackbar.make(binding.root
                        ,"Error en la base de datos"
                        ,Snackbar.LENGTH_LONG)
                        .show()
                }
                binding.userLayout.error=""
                binding.passText.text?.clear()
            }else{
                binding.userLayout.error="Usuario o contraseña incorrectos"
            }


        }
    }
}