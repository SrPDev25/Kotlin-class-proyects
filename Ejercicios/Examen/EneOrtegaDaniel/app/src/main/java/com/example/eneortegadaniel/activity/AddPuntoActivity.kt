package com.example.eneortegadaniel.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eneortegadaniel.control.Control
import com.example.eneortegadaniel.control.Punto
import com.example.eneortegadaniel.databinding.ActivityAddPuntoBinding

class AddPuntoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPuntoBinding
    private lateinit var control: Control

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPuntoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        control= intent.getParcelableExtra<Control>("controlAdd")!!

        binding.buttonAdd.setOnClickListener() {
            if (comprobacion()) {
                control.puntos.add(
                    Punto(
                        binding.codigoText.text.toString(),
                        binding.tipoText.text.toString().toInt(),
                        binding.denominacionText.text.toString(),
                        binding.direccionText.text.toString(),
                        binding.provinciaText.text.toString(),
                        binding.cargadorSoportadoText.text.toString(),
                        binding.indicacionesText.text.toString(),
                        binding.precioText.text.toString()
                    )
                )
                var myIntent = Intent()
                myIntent.putExtra("control", control)
                setResult(Activity.RESULT_OK, myIntent)
                finish()
            }

        }
        binding.cancelButton.setOnClickListener() {
            clean()
        }
        binding.volverButton.setOnClickListener() {
            finish()
        }
    }

    private fun comprobacion(): Boolean {
        var correct = true

        if (binding.codigoText.text.toString().isBlank()) {
            binding.codigoLayout.error = "Campo obligatorio"
            correct = false
        } else if (control.isPuntoExist(binding.codigoText.text.toString())) {
            binding.codigoLayout.error = "Codigo ya existente"
            correct = false
        } else
            binding.codigoLayout.error = ""

        if (binding.tipoText.text.toString().isBlank()) {
            binding.tipoLayout.error = "Campo obligatorio"
            correct = false
        } else if (control.getEstablecimiento(binding.tipoText.text.toString().toInt()).isBlank()) {
            binding.tipoLayout.error = "Codigo de tipo no existe"
            correct = false
        } else
            binding.tipoLayout.error = ""

        if (binding.denominacionText.text.toString().isBlank()) {
            correct = false
            binding.denominacionLayout.error = "Campo obligatorio"
        } else
            binding.denominacionLayout.error = ""

        if (binding.direccionText.text.toString().isBlank()) {
            correct = false
            binding.direccionLayout.error = "Campo obligatorio"
        } else
            binding.direccionLayout.error = ""

        if (binding.provinciaText.text.toString().isBlank()) {
            correct = false
            binding.provinciaLayout.error = "Campo obligatorio"
        } else
            binding.provinciaLayout.error = ""

        if (binding.cargadorSoportadoText.text.toString().isBlank()) {
            correct = false
            binding.cargadorSoportadoLayout.error = "Campo obligatorio"
        } else
            binding.cargadorSoportadoLayout.error = ""

        if (binding.indicacionesText.text.toString().isBlank()) {
            correct = false
            binding.indicacionesLayout.error = "Campo obligatorio"
        } else
            binding.indicacionesLayout.error = ""

        if (binding.precioText.text.toString().isBlank()) {
            correct = false
            binding.precioLayout.error = "Campo obligatorio"
        } else
            binding.precioLayout.error = ""


        return correct
    }

    private fun clean() {
        binding.codigoText.text?.clear()
        binding.codigoLayout.error=""
        binding.denominacionText.text?.clear()
        binding.denominacionLayout.error=""
        binding.cargadorSoportadoText.text?.clear()
        binding.cargadorSoportadoLayout.error=""
        binding.tipoText.text?.clear()
        binding.tipoLayout.error=""
        binding.direccionText.text?.clear()
        binding.direccionLayout.error=""
        binding.provinciaText.text?.clear()
        binding.provinciaLayout.error=""
        binding.precioText.text?.clear()
        binding.precioLayout.error=""
        binding.indicacionesText.text?.clear()
        binding.indicacionesLayout.error=""
    }
}