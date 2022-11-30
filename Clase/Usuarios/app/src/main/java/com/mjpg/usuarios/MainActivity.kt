package com.mjpg.usuarios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mjpg.usuarios.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),Events {
    lateinit var binding: ActivityMainBinding
    lateinit var linearLayout: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Envía la lista de usuarios al adapter
        binding.recyclerview.adapter = UserAdapter(getUsuarios(),
                                            this)//Pasa todos el interfaz como la interface
        linearLayout = LinearLayoutManager(this)//this, se
        binding.recyclerview.layoutManager = linearLayout//
        binding.recyclerview.setHasFixedSize(true)//no se, supongo que es para prevenir bugs visuales
    }

    private fun getUsuarios(): MutableList<Usuario> {
        val usuarios = mutableListOf<Usuario>()
        usuarios.add(
            Usuario(
                1,
                "ElPepe",
                "Cogollos",
                "https://i.ytimg.com/vi/DwlzqcGhITA/maxresdefault.jpg"
            )
        )
        usuarios.add(
            Usuario(
                2,
                "Huecco",
                "Cogollos",
                "https://www.todomusica.org/imagenes/680x380/huecco.jpg"
            )
        )
        usuarios.add(
            Usuario(
                3,
                "Oreja",
                "Cogollos",
                "https://www.mejorinfluencer.com/wp-content/uploads/2021/01/Orslok-Wiki-Youtuber-Espa%C3%B1a.png"
            )
        )
        usuarios.add(
            Usuario(
                4,
                "Melo",
                "Cogollo",
                "https://images.complex.com/complex/images/c_fill,dpr_auto,f_auto,q_90,w_1400/fl_lossy,pg_1/apemfvfql7n9pfr3yzgd/melo-warm-up"
            )
        )

        return usuarios
    }

    override fun pulsirenCorta(usuario: Usuario) {
        Toast.makeText(this,
            "${usuario.id} ${usuario.dameNombreCompleto()}",
            Toast.LENGTH_LONG).show()
    }

    override fun pulsirenLarga(posicion: Int): Boolean {
        Toast.makeText(this,
            "$posicion",
            Toast.LENGTH_LONG).show()
        return false//Se debe indicar true para verificar que la pulsación ha sido larga y no llame al metodo de pulsación corta
    }


}