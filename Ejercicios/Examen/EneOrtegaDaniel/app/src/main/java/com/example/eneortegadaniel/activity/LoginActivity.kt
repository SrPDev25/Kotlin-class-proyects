package com.example.eneortegadaniel.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.eneortegadaniel.control.Control
import com.example.eneortegadaniel.control.Establecimiento
import com.example.eneortegadaniel.control.Punto
import com.example.eneortegadaniel.control.Usuario
import com.example.eneortegadaniel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var control: Control
    private var count=0
    private var usuario = mutableListOf(
        Usuario("1", "1"),
        Usuario("2", "2")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        control = Control(
            mutableListOf<Establecimiento>(
                Establecimiento(1, "Supermercado"),
                Establecimiento(2, "Gasolinera"),
                Establecimiento(3, "Parking")
            ),
            mutableListOf<Punto>(
                Punto("C1",1,"Hipercor","Avenida Madrid 5","Burgos"),
                Punto("D1",2,"Respol","C/ Madrid 7","Burgos"),
                Punto("J1",3,"Cargador Mañana","C/ Mañana 15","Madrid")
            )
        )
        binding.whiteLayout.background.alpha = 200
        binding.buttonLogin.setOnClickListener() {
            val user = binding.userText.text.toString()
            val pass = binding.passText.text.toString()
            val check = getUserEstado(user, pass)
            if (check) {
                count=0
                binding.passText.text?.clear()
                binding.userLayout.error = ""
                val myIntent = Intent(this, PuntosActivity::class.java)
                myIntent.putExtra("controlUp", control)
                startForResult.launch(myIntent)
            } else {
                binding.passText.text?.clear()
                binding.userLayout.error = "Usuario o contraseña incorrectos"
                count++
                if (count==3)
                    finish()
            }
        }

    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent: Control? = result.data?.getParcelableExtra("controlDown")
                if (intent != null) {
                    control = intent
                }
            }
        }


    fun getUserEstado(user: String, pass: String): Boolean {
        val pos = usuario.indexOf(Usuario(user, pass))
        if (pos != -1)
            return true
        else
            return false
    }


}