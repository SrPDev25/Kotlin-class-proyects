package com.example.ej9ortegadanielbd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ej9ortegadanielbd.Events
import com.example.ej9ortegadanielbd.ListenerAdapterCards
import com.example.ej9ortegadanielbd.ListenerAdapterCitas
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.FragmentCitaBinding

class CitasFragment : Fragment(),Events {

    private lateinit var binding: FragmentCitaBinding
    private var mActivity: MainActivity? = null
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var bd: OperacionesDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCitaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity=activity as? MainActivity
        bd=OperacionesDao(mActivity!!.applicationContext)

        linearLayout= LinearLayoutManager(mActivity!!.applicationContext)


    }

    override fun shortClick(codigo: Int) {
        //TODO("Not yet implemented")
    }

    override fun longClick(codigo: Int): Boolean {
        //TODO("Not yet implemented")
        return false
    }
}