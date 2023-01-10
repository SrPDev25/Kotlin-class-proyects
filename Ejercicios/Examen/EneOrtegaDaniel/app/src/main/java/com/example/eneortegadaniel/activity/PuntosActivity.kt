package com.example.eneortegadaniel.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e6mdortegadaniel.ListenerAdapter
import com.example.eneortegadaniel.control.Control
import com.example.eneortegadaniel.databinding.ActivityPuntosBinding

class PuntosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPuntosBinding
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var control: Control
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuntosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        control= intent.getParcelableExtra("controlUp")!!
        chargeRecycler()

        binding.buttonAdd.setOnClickListener(){
            val myIntent = Intent(this, AddPuntoActivity::class.java)
            myIntent.putExtra("controlAdd",control)
            startForResult.launch(myIntent)
        }

    }

    //Controla que hace cuando el usuario vuelve hacia atras
    override fun onBackPressed() {
        var myIntent=Intent()
            .putExtra("controlDown", control)
        setResult(Activity.RESULT_OK,myIntent)
        finish()
    }



    /** Carga el recyclerView para actualizar los nuevos cambios
     */
    private fun chargeRecycler() {
        binding.recyclerview.adapter = ListenerAdapter(control)
        linearLayout = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayout
        binding.recyclerview.setHasFixedSize(true)
    }




    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent: Control? = result.data?.getParcelableExtra("control")
            if (intent!=null){
                control= intent
                chargeRecycler()
            }
        }
    }
}


