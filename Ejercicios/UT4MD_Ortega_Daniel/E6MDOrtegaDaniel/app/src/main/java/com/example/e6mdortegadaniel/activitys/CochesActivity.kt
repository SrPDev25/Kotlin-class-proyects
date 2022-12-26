package com.example.e6mdortegadaniel.activitys

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e6mdortegadaniel.Events
import com.example.e6mdortegadaniel.ListenerAdapter

import com.example.e6mdortegadaniel.control.Control
import com.example.e6mdortegadaniel.control.Vehiculo
import com.example.e6mdortegadaniel.databinding.ActivityCochesBinding

class CochesActivity : AppCompatActivity(), Events {
    private lateinit var binding: ActivityCochesBinding
    private lateinit var linearLayout: LinearLayoutManager
    private var control= Control()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCochesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        chargeRecycler()

        binding.buttonAdd.setOnClickListener(){
            val myIntent = Intent(this, AddCocheActivity::class.java)
                .putExtra("dataBase", control)
            startForResult.launch(myIntent)
        }

    }

    override fun longClick(pos: Int): Boolean {

        if (!control.vehiculos.get(pos).estado){
            control.vehiculos.get(pos).estado=true
            chargeRecycler()

        }
        return true
    }

    private fun chargeRecycler() {
        binding.recyclerview.adapter = ListenerAdapter(control.vehiculos, this)
        linearLayout = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayout
        binding.recyclerview.setHasFixedSize(true)
    }

    override fun shortClick(pos: Int) {

    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result->
        //Comprueba que si hay resultado, el intent tiene el codigo RESULT_OK
        //si salta un error no lo devuelve
        if (result.resultCode == Activity.RESULT_OK) {
            //"vehicula" es el nombre para recibir después el dato

            val intent: Parcelable? = result.data?.getParcelableExtra("vehiculo")
        }
    }
}