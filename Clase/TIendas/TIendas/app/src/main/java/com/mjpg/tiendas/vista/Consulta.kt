package com.mjpg.tiendas.vista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.mjpg.tiendas.*
import com.mjpg.tiendas.adaptador.AdaptadorTienda
import com.mjpg.tiendas.bd.TiendasDAO
import com.mjpg.tiendas.databinding.ConsultaBinding
import com.mjpg.tiendas.interfaces.EventosListener
import com.mjpg.tiendas.modelo.Tienda
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Consulta: Fragment(), EventosListener {
    private lateinit var adaptador: AdaptadorTienda
    private lateinit var gridLayout: GridLayoutManager
    private lateinit var db: TiendasDAO
    private lateinit var mBinding:ConsultaBinding
    private var mActivity: MainActivity? = null
    private lateinit var tiendas :MutableList<Tienda>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = ConsultaBinding.inflate(inflater, container, false)
        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActionBar()
        db = TiendasDAO(mActivity!!.applicationContext)
    }
    override fun onResume() {
        super.onResume()
        configurarRecycler()
    }
    private fun setUpActionBar() {
        // Vamos a conseguir la actividad en la cual este alojada este fragment
        mActivity = activity as? MainActivity
        //mostrar  la flecha de retroceso en la parte arriba
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // configurar el titulo
        mActivity?.supportActionBar?.title = getString(R.string.consulta)
        // Aqui indicamos que tenga acceso al menú de la barra de opciones
        // con setHasOptionsMenu a true toma el control del menú
        setHasOptionsMenu(true)
        mBinding.fab.setOnClickListener {
            mActivity?.anadir()
        }
    }
 private fun configurarRecycler() {
        adaptador = AdaptadorTienda(mutableListOf(),this )
        gridLayout = GridLayoutManager(mActivity!!.applicationContext, 2)
        getAllTiendas()
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = gridLayout
            adapter = adaptador
        }
    }
    private fun getAllTiendas() {

        consultaCorrutinas()
    }

   private  fun consultaCorrutinas()
    {

       GlobalScope.launch (Dispatchers.IO){

             tiendas= db.getAllTiendas()
         launch(Dispatchers.Main){
               adaptador.setTiendas(tiendas)
           }

       }
   }

    /**
     * Eventos listener
     */
    override fun editar(id: Long) {
        mActivity?.editar(id)
    }
    override fun onFavorito(tienda: Tienda) {
// todo falta meterlo en un hilo
        if (tienda.esFavorito == 0)
            tienda.esFavorito = 1
        else
            tienda.esFavorito = 0
        db.updateTienda(tienda)
        adaptador.update(tienda)
    }
    override fun borrarTienda(id: Long) {
// todo falta meterlo en un hilo y preguntar si realmente lo quiere borrar
              db.deleteTienda(id)
              adaptador.borrar(id) }

    override fun onDestroyView() {
        super.onDestroyView()
        db.cerrar()
    }
}
