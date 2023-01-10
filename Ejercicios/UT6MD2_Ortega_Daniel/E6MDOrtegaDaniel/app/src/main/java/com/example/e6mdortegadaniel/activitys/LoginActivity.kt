package com.example.e6mdortegadaniel.activitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.e6mdortegadaniel.control.Control
import com.example.e6mdortegadaniel.control.Usuario
import com.example.e6mdortegadaniel.control.Vehiculo
import com.example.e6mdortegadaniel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var control: Control
    private var usuario = mutableListOf(
        Usuario("1", "1", Usuario.RECEPCIONISTA),
        Usuario("2", "2", Usuario.MECANICO)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        control = Control(
            mutableListOf<Vehiculo>(
                Vehiculo("112233S", "Juan", "asdf@gmail.com", "1155357CDS", "Seat Panda"),
                Vehiculo("453423G", "Juanito", "gsdffgd@gmail.com", "5858974CDS", "BMW", "asdf", "25/5/2023", true
                )
            )
        )
        binding.whiteLayout.background.alpha = 200
        binding.buttonLogin.setOnClickListener() {
            val user = binding.userText.text.toString()
            val pass = binding.passText.text.toString()
            val pos = getUserEstado(user, pass)
            if (pos >= 0) {
                binding.passText.text?.clear()
                binding.userLayout.error = ""
                val myIntent = Intent(this, CochesActivity::class.java)
                myIntent.putExtra("tipo", usuario[pos].estado)
                myIntent.putExtra("controlUp", control)
                startForResult.launch(myIntent)
            } else {
                binding.passText.text?.clear()
                binding.userLayout.error = "Usuario o contraseña incorrectos"
            }
        }

    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            //Comprueba que si hay resultado, el intent tiene el codigo RESULT_OK
            //si salta un error no lo devuelve
            if (result.resultCode == Activity.RESULT_OK) {
                //"vehicula" es el nombre para recibir después el dato
                val intent: Control? = result.data?.getParcelableExtra("controlDown")
                if (intent != null) {
                    control = intent
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