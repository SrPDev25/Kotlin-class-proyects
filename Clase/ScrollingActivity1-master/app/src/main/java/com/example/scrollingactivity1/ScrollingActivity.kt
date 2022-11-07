package com.example.scrollingactivity1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.URLUtil
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.scrollingactivity1.databinding.ActivityScrollingBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.snackbar.Snackbar


class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding:ActivityScrollingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // INFLAMOS EL ARCHIVO XML (LE DA FORMA)
            //Solo se pude inflar un layout
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
        cargarImagen()
        //https://rlv.zcache.es/poster_selfie_de_mono-r97fed6033c784b2e8b5d1a004ee90393_wvg_8byvr_307.jpg
        binding.content.urlText.setOnFocusChangeListener { _, foco ->
            //foco comprueba si esta fucuseado o no

                val url = binding.content.urlText.text.toString()
                var error:String?=null
            if (!foco) {
                if (url.isBlank()) {
                    binding.content.urlText.error= R.string.vacio.toString()

                }else{
                    if(URLUtil.isValidUrl(url)){//Comprueba si la imagen es válida, si no lo es saldrá un error
                        cargarImagen(url)
                        error=null
                    }else{
                        error=getString(R.string.direction)//Introduce el nombre del error en la variable error
                    }

                }
                //En el error del contenido introduce el nombre del error, quitando el nulo y activando el error
                binding.content.txtContenido.error=error
            }

        }

    }
    //https://rlv.zcache.es/poster_selfie_de_mono-r97fed6033c784b2e8b5d1a004ee90393_wvg_8byvr_307.jpg
    private fun cargarImagen(url:String="https://www.nationalgeographic.com.es/medio/2018/02/27/monos__1280x720.jpg"){
         Glide.with(this)
             .load(url)//aqui se introduce la url de la imagen
             .centerCrop()//Ajusta al tamaño la imagen, creo
             .diskCacheStrategy(DiskCacheStrategy.DATA)//guarda en caché la imagen
             .into(binding.content.imgMono)//Indicamos que imageView tendra la imagen
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