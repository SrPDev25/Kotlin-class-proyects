package com.mjpg.tiendas2fragmentosvistamodelo.vistas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mjpg.tiendas2fragmentosvistamodelo.vistamodelo.AdaptadorTienda
import com.mjpg.tiendas2fragmentosvistamodelo.vistamodelo.EventosListener
import com.mjpg.tiendas2fragmentosvistamodelo.modelo.Tienda
import com.mjpg.tiendas2fragmentosvistamodelo.modelo.TiendasDAO
import com.mjpg.tiendas2fragmentosvistamodelo.databinding.ConsultaBinding
import com.mjpg.tiendas2fragmentosvistamodelo.vistamodelo.VistaModelo
import com.mjpg.tiendas2fragmentosvistamodelo.vistamodelo.VistaModeloFactory


class Consulta : Fragment(), EventosListener {
    private lateinit var adaptador: AdaptadorTienda
    private lateinit var gridLayout: GridLayoutManager
    private lateinit var db: TiendasDAO
    private lateinit var mBinding: ConsultaBinding
    private var mActivity: MainActivity? = null
    private lateinit var tiendas: MutableList<Tienda>
    private lateinit var modelo: VistaModelo

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
        configurarRecycler()
        val observador2 = Observer<Long> {
            if (it == -2L) {
                configurarRecycler()
            }
        }
        modelo.identificador.observe(this.viewLifecycleOwner, observador2)
    }


    private fun setUpActionBar() {

        mActivity = activity as? MainActivity
        val viewModelFactory = VistaModeloFactory(0)

        modelo =
            ViewModelProvider(this.requireActivity(), viewModelFactory).get(VistaModelo::class.java)
        mBinding.fab.setOnClickListener {
            modelo.setIdentificador(-1L)
            mActivity?.editar()
        }
    }

    private fun configurarRecycler() {
        adaptador = AdaptadorTienda(mutableListOf(), this)
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

    private fun consultaCorrutinas() {
        tiendas = db.getAllTiendas()
        adaptador.setTiendas(tiendas)
    }


    /**
     * Eventos listener
     */
    override fun editar(id: Long) {
        modelo.setIdentificador(id)
        mActivity!!.editar()
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

        db.deleteTienda(id)

        adaptador.borrar(id)

    }


}