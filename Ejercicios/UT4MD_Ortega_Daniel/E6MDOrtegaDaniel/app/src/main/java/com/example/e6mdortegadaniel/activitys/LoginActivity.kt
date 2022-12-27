package com.example.e6mdortegadaniel.activitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.e6mdortegadaniel.control.Usuario
import com.example.e6mdortegadaniel.control.Vehiculo
import com.example.e6mdortegadaniel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var usuario = mutableListOf(
        Usuario("1", "1", Usuario.RECEPCIONISTA),
        Usuario("2", "2", Usuario.MECANICO)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.whiteLayout.background.alpha = 200
        binding.buttonLogin.setOnClickListener() {
            val user = binding.userText.text.toString()
            val pass = binding.passText.text.toString()
            val pos = getUserEstado(user, pass)
            if (pos >= 0) {
                binding.passText.text?.clear()
                binding.userLayout.error = ""
                val myIntent= Intent(this, CochesActivity::class.java)
                myIntent.putExtra("tipo",usuario[pos].estado)
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