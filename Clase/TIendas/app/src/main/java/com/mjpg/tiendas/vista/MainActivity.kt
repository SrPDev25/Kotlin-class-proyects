package com.mjpg.tiendas.vista


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mjpg.tiendas.*
import com.mjpg.tiendas.databinding.ActivityMainBinding
import com.mjpg.tiendas.interfaces.Comunicar

class MainActivity : AppCompatActivity(), Comunicar {

    private lateinit var binding: ActivityMainBinding

    private lateinit var fragmentEditar: EditStoreFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Como el programa reconoce que layout usar,
            si no es tablet ejecuta
            */
        if (binding.fragmentLista == null)
            launchFragment(savedInstanceState)
        else {
            // tablet
            fragmentEditar = EditStoreFragment()
            val args = Bundle() //Un hashMap utilizado para recoger valores <Parcelable>
            args.putLong(
                getString(R.string.arg_id),
                -1
            )//Son los argumentos .argments que se transmiten al fragmento, solo pueden ser datos primitivos
            fragmentEditar.arguments=args
            val fragmentManager =
                supportFragmentManager //Supongo que igual que el layoutManager, pero con fragments
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fragmentdetalle, fragmentEditar)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()


        }
    }

    /**
     * Metodo que ejecuta los
     */
    private fun launchFragment(savedInstanceState: Bundle?) {
        val fragment = Consulta()//El fragmento se puede tratar como una variable
        val fragmentManager = supportFragmentManager//Fragment manager, osea el gestor
        val fragmentTransaction =
            fragmentManager.beginTransaction()//Indicas que empiezas con tus propios fragmentos
        //Si la activity no fue creada anteriormente añade el fragmento consulta
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.frag_contenedor, fragment)
            //Si la activity ya fue creada anteriormente se remplaza el fragment que está en el activity por otro nuevo
            //De esta manera se actualiza la lista al volver atrás
        } else {
            fragmentTransaction.replace(R.id.frag_contenedor, fragment)
        }

        //Supuestamente es para evitar que al hacer varias acciones de errores y funcionen como una sola funcion
        //Aquí como solo se hace una add/replace no se si sirve, pero usala igualemente
        //MJ: Inhabilita el boton de ir hacia atras, PRESUNTAMENTE
        fragmentTransaction.addToBackStack(null)

        //Aplicar resultados
        fragmentTransaction.commit()
    }


    override fun editar(id: Long) {
        val fragmentManager = supportFragmentManager
        val args = Bundle()
        val id_key=getString(R.string.arg_id)//Coge
        args.putLong(id_key, id)
        val fragment = EditStoreFragment()
        fragment.arguments = args
        if (binding.fragmentdetalle == null) {
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.frag_contenedor, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        } else {
            fragmentEditar.cambio(id)
        }
    }

    override fun anadir() {
        val args = Bundle()
        args.putLong(getString(R.string.arg_id), -1)

        val fragmentManager = supportFragmentManager
        val fragment = EditStoreFragment()
        fragment.arguments = args
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (binding.fragmentdetalle == null) {
            fragmentTransaction.replace(R.id.frag_contenedor, fragment)
        } else {
            fragmentTransaction.replace(R.id.fragmentdetalle, fragment)
        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}