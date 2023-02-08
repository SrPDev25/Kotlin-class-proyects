package com.mjpg.tiendas2fragmentosvistamodelo.vistas

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.mjpg.tiendas2fragmentosvistamodelo.R
import com.mjpg.tiendas2fragmentosvistamodelo.modelo.Tienda
import com.mjpg.tiendas2fragmentosvistamodelo.modelo.TiendasDAO
import com.mjpg.tiendas2fragmentosvistamodelo.databinding.FragmentEditStoreBinding

import com.mjpg.tiendas2fragmentosvistamodelo.vistamodelo.VistaModelo
import com.mjpg.tiendas2fragmentosvistamodelo.vistamodelo.VistaModeloFactory


class EditStoreFragment : Fragment() {
    private lateinit var mBinding: FragmentEditStoreBinding
    private var mActivity: MainActivity? = null
    private var misEditMode: Boolean = false
    private var mTienda: Tienda? = null
    private lateinit var db: TiendasDAO


    private lateinit var modelo: VistaModelo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentEditStoreBinding.inflate(inflater, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as? MainActivity
        val viewModelFactory = VistaModeloFactory(0)

        modelo =
            ViewModelProvider(this.requireActivity(), viewModelFactory).get(VistaModelo::class.java)
        db = TiendasDAO(mActivity!!.applicationContext)
        setUpActionBar()

        val id = modelo.identificador.value
        casos(id!!)

        val observador = Observer<Long> {
            casos(it)
        }
        modelo.identificador.observe(this.viewLifecycleOwner, observador)
        setupTextFields()
    }


    private fun casos(id: Long) {
        when (id) {
            0L -> {
                mTienda = db.primerElemento()

                if (mTienda != null) {
                    setUiStore(mTienda!!)
                    misEditMode = true
                }


            }
            -1L -> {

                misEditMode = false
                mTienda = Tienda(name = "", phone = "", photoUrl = "")
                setUiStore(mTienda!!)
            }
            else -> {

                getStore(id)


                misEditMode = true

            }
        }

    }

    private fun setUpActionBar() {
        setHasOptionsMenu(true)
    }

    private fun setupTextFields() {
        with(mBinding) {
            etName.addTextChangedListener {
                validateFields(tilName)
            }
            etPhone.addTextChangedListener { validateFields(tilPhone) }
            etPhotoUrl.addTextChangedListener {
                validateFields(tilPhotoUrl)
                loadImage(it.toString().trim())
            }
        }
    }

    private fun loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .error(R.drawable.ic_error)
            .into(mBinding.imgPhoto)
    }

    private fun getStore(id: Long) {

        mTienda = db.getStoreById(id)

        if (mTienda != null) {
            setUiStore(mTienda!!)
        }


    }

    private fun setUiStore(storeEntity: Tienda) {
        with(mBinding) {
            etName.setText(storeEntity.name)
            etPhone.text = storeEntity.phone.editable()
            etWebSite.text = storeEntity.webSite.editable()
            etPhotoUrl.setText(storeEntity.photoUrl)
            loadImage(storeEntity.photoUrl)
        }
    }

    private fun String.editable(): Editable = Editable.Factory.getInstance().newEditable(this)
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mActivity?.onBackPressed()
                true
            }
            R.id.action_save -> {

                if (mTienda != null && validateFields(
                        mBinding.tilPhotoUrl,
                        mBinding.tilPhone,
                        mBinding.tilName
                    )
                ) {
                    with(mTienda!!) {
                        name = mBinding.etName.text.toString().trim()
                        phone = mBinding.etPhone.text.toString().trim()
                        webSite = mBinding.etWebSite.text.toString().trim()
                        photoUrl = mBinding.etPhotoUrl.text.toString().trim()
                    }

                    if (misEditMode) db.updateTienda(mTienda!!)
                    else
                        mTienda!!.id = db.addTienda(mTienda!!)



                    modelo.setIdentificador(-2L)
                    hideKeyboard()
                    //mActivity?.onBackPressed()
                }

                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun validateFields(vararg textFields: TextInputLayout): Boolean {
        var isValid = true
        for (textField in textFields) {
            if (textField.editText?.text.toString().isEmpty()) {
                textField.error = getString(R.string.helper_requiered)

                isValid = false
            } else {
                textField.error = null
            }
        }
        if (!isValid)
            Snackbar.make(
                mBinding.root, R.string.edit_store_message_valid,
                Snackbar.LENGTH_LONG
            ).show()
        return isValid
    }

    private fun hideKeyboard() {
        if (view != null) {
            val imm =
                mActivity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    override fun onDestroyOptionsMenu() {
        hideKeyboard()
        super.onDestroyOptionsMenu()
    }

    override fun onDestroy() {
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mActivity?.supportActionBar?.title = getString(R.string.consulta)
        setHasOptionsMenu(false)
        db?.cerrar()
        super.onDestroy()
    }
}



