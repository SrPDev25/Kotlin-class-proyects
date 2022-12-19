package com.example.e6mdortegadaniel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e6mdortegadaniel.control.Usuario
import com.example.e6mdortegadaniel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var usuario = mutableListOf(
        Usuario("usuario1", "password1", Usuario.RECEPCIONISTA),
        Usuario("usuario2", "password2", Usuario.MECANICO)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.whiteLayout.background.alpha = 200
        binding.buttonLogin.setOnClickListener() {
            val user = binding.userText.text.toString()
            val pass = binding.passText.text.toString()
            val estado = getUserEstado(user, pass)
            if (estado >= 0) {
                binding.passText.text?.clear()
                binding.userLayout.error = ""
                val myIntent= Intent(this,CochesActivity::class.java)
                startActivity(myIntent)

            } else {
                binding.passText.text?.clear()
                binding.userLayout.error = "Usuario o contraseña incorrectos"
            }
        }

    }

    /**
     * Devuelve el estado del usuario,
     * si es -1 es que no existe
     */
    fun getUserEstado(user: String, pass: String): Int {
        val pos = usuario.indexOf(Usuario(user, pass))
        if (pos != -1)
            return usuario.get(pos).estado
        else
            return pos
    }


}