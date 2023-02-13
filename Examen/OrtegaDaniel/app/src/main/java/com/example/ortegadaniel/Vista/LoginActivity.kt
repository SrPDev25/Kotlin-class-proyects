package com.example.ortegadaniel.Vista

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ortegadaniel.OperacionesDao
import com.example.ortegadaniel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var bd:OperacionesDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bd=OperacionesDao(this)
        binding.buttonLogin.setOnClickListener(){
            val login=binding.userText.text.toString()
            val pass=binding.passText.text.toString()
            if (bd.isUsuarioValido(login,pass)){
                binding.passText.text?.clear()
                binding.userLayout.error=""
                var intent= Intent(this,MainActivity::class.java)
                    .putExtra("tipoLogin",1)
                startActivity(intent)
            }else{
                binding.passText.text?.clear()
                binding.userLayout.error="Usuario o contraseña incorrecta"
            }
        }
        binding.buttonInvitado.setOnClickListener(){
            var intent= Intent(this,MainActivity::class.java)
                .putExtra("tipoLogin",0)
            startActivity(intent)
        }

    }
}