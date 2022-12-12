package com.mjpg.solicitudpermisos

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.mjpg.solicitudpermisos.databinding.ActivityMainBinding
import android.Manifest
import androidx.core.app.ActivityCompat


import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog.Builder

//Libro página 123
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val PERMISOLLAMADA = 234


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnWebpage.setOnClickListener {
            webButtonAction()
        }



        binding.btnCallphone.setOnClickListener {
            llamadaTelefono1ButtonAction()
        }



        binding.btnCallphone2.setOnClickListener {
            llamadaTelefono2ButtonAction()
        }
    }

    private fun llamadaTelefono2ButtonAction() {
        when {
            ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED -> {
                val intent = Intent(
                    Intent.ACTION_CALL,
                    Uri.parse("tel:965555555")
                )
                startActivity(intent)
            }

            shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE) -> {
                Snackbar.make(
                    binding.rootlayout, "POR CONTRATO Este permiso es peligroso" +
                            "y es para poder llamar por teléfono",
                    Snackbar.LENGTH_LONG
                ).show()

                val builder = Builder(this@MainActivity)
                builder.setTitle("Permiso para llamar")
                builder.setMessage("Puede resultar interesante indicar porqué.")

                builder.setPositiveButton(android.R.string.ok) { _, _ ->
                    Snackbar.make(
                        binding.rootlayout,
                        "Se acepta y se vuelve a pedir permiso",
                        Snackbar.LENGTH_LONG
                    ).show()


                    requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)

                }
                builder.setNeutralButton(android.R.string.cancel, null)
                builder.show()
            }
            else -> {

                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        }
    }

    private fun llamadaTelefono1ButtonAction() {

        //Comprueba si no está concedido el permiso de llamada
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            Snackbar.make(
                binding.rootlayout,
                "No está concedido el permiso para llamar",
                Snackbar.LENGTH_LONG
            ).show()

            //Comprueba si se ha pedido ya el permiso de llamada ??En este activity??
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.CALL_PHONE
                )
            ) {
                //Si se cumple le avisa el motivo por el cual es el permiso
                Snackbar.make(
                    binding.rootlayout, "Este permiso es peligroso" +
                            "y es para poder llamar por teléfono...............",
                    Snackbar.LENGTH_LONG
                ).show()
                val builder = Builder(this)
                builder.setTitle("Permiso para llamar")
                builder.setMessage("Puede resultar interesante indicar porqué.")


                builder.setPositiveButton(android.R.string.ok) { _, _ ->
                    Snackbar.make(
                        binding.rootlayout,
                        "Se acepta y se vuelve a pedir permiso",
                        Snackbar.LENGTH_LONG
                    ).show()
                    //Solicitar el permiso
                    ActivityCompat.requestPermissions(
                                    //En este arrayOf se pueden pedir varios permisos
                        this, arrayOf(Manifest.permission.CALL_PHONE),//es aconsejable pedir de 1 en 1
                        PERMISOLLAMADA
                    )
                }

                builder.setNeutralButton(android.R.string.cancel, null)
                builder.show()
            } else {


                Snackbar.make(
                    binding.rootlayout, "No se da una explicación.",
                    Snackbar.LENGTH_LONG
                ).show()
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CALL_PHONE),
                    PERMISOLLAMADA
                )
            }
        } else {
            Snackbar.make(
                binding.rootlayout, "El permiso ya está concedido.",
                Snackbar.LENGTH_LONG
            ).show()

            val intent = Intent(
                Intent.ACTION_CALL,
                Uri.parse("tel:965555555")
            )
            startActivity(intent)
        }
    }

    private fun webButtonAction() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://developer.android.com/")
        )
        //Comprueba si existe un explorador por defecto
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Snackbar.make(
                binding.rootlayout,
                " No se encuentra un navegador",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }


    /**Metodo que se llama al hacerse el ActivityCompat.requestPermissions
     * @PERMISOLLAMADA no tiene por que ser 234, si no que al usarse en el ActivityCompat...
     *      Usa ese código para indicar que ha sido solicitado su permiso
     *      Por eso se sugiere perdir permisos de 1 en 1, para separar los códigos de comprobación
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISOLLAMADA -> {
                Log.d("DEBUG", "${grantResults[0]} ${permissions[0]}")
                if ((grantResults.isNotEmpty() && grantResults[0]
                            == PackageManager.PERMISSION_GRANTED)
                ) {
                    Snackbar.make(
                        binding.rootlayout, " Permiso concedido",
                        Snackbar.LENGTH_LONG
                    ).show()

                } else {
                    Snackbar.make(
                        binding.rootlayout, " Permiso rechazado",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                return
            }
            else -> {
                Snackbar.make(
                    binding.rootlayout, "Se pasa de los permisos.",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Snackbar.make(
                    binding.rootlayout, " Permiso concedido",
                    Snackbar.LENGTH_LONG
                ).show()
                val intent = Intent(
                    //Intent.ACTION_DIAL //Para solo introducir el número de telefono
                    Intent.ACTION_CALL,//Llamada directa
                    Uri.parse("tel:965555555")
                )
                startActivity(intent)

            } else {
                Snackbar.make(
                    binding.rootlayout, " Permiso  no concedido",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

}