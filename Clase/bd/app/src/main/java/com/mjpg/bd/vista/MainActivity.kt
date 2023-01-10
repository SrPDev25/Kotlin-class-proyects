package com.mjpg.bd.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import com.google.android.material.snackbar.Snackbar
import com.mjpg.basedatos.dao.OperacionesDao
import com.mjpg.bd.databinding.ActivityMainBinding
import com.mjpg.bd.modelo.Usuario
import com.mjpg.bd.vista.Consulta


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bd: OperacionesDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bd = OperacionesDao(this)
        // RELLENAR CON DATOS
        val tablaUsuariosVacia = bd.tablaVaciaUsuarios()
        if (tablaUsuariosVacia) {
            insertarDatosUsuarios()
        }

        binding.btIniciar.setOnClickListener {
            comprobar()

        }
    }


    private fun comprobar() {
        val loginIntroducido = binding.usuario.text.toString()
        val contrasenaIntroducida = binding.contra.text.toString()
        if (isEmpty(loginIntroducido) || isEmpty(contrasenaIntroducida)) {
            Snackbar.make(
                binding.root, "Los campos no pueden estar vacíos",
                Snackbar.LENGTH_LONG
            ).show()
        } else {
            val usuarioEncontrado = bd.getUsuario(loginIntroducido, contrasenaIntroducida)
            if (usuarioEncontrado == null) {
                Snackbar.make(
                    binding.root, "Usuario no encontrado",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(this, Consulta::class.java)
                startActivity(intent)
            }
        }
    }

    private fun insertarDatosUsuarios() {
        val usuario1 =
            Usuario(
                login = "mj",
                contra = "1234"
            )
        val usuario2 =
            Usuario(
                login = "pepe",
                contra = "001"
            )
        Thread {
            bd.addUsuario(usuario1)
            bd.addUsuario(usuario2)
        }.start()
    }



}