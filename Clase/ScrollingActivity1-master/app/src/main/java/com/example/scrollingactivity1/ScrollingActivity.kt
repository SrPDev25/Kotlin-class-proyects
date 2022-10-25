package com.example.scrollingactivity1

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.scrollingactivity1.databinding.ActivityScrollingBinding
import com.google.android.material.bottomappbar.BottomAppBar


class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding:ActivityScrollingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // INFLAMOS EL ARCHIVO XML (LE DA FORMA)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        // SETTEAMOS EL CONTENT VIEW A ROOT
     setContentView(binding.root)
        //fab la acciones de los botones
        binding.fab.setOnClickListener {
            if (binding.barra.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER)
                binding.barra.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
        else
                binding.barra.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

        }
        binding.barra.setNavigationOnClickListener {//Escucha que clicken la barra(El emoticono de silla de ruedas)
            Snackbar.make(binding.root,getString(R.string.exito),
                Snackbar.LENGTH_LONG)//Una barra de mensaje que sale y depende de la vista de la aplicación
                .setAnchorView(binding.fab)
                .setAction("boton de acción"){//Añade un boton
                    Toast.makeText(this,"Mensaje tostada",
                        Toast.LENGTH_LONG)
                        .show()//muestra la tostada
                    //Toast no esta asociado a ninguna vista(No depende de estar ejecutada la vista de ese programa)
                }
                .show()//muestra el picoteo

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}