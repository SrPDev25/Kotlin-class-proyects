package com.example.e5mdortegamartnezdaniel.vista

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.e5mdortegamartnezdaniel.control.Control
import com.example.e5mdortegamartnezdaniel.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var control: Control
    var problemExist=arrayOf(true,true,true,true,true)//Está pendiente que todos los textos están correctos
    companion object{
        const val NO_ERROR=-1
        const val ERROR_NO_NUMBERS=-3
        const val ERROR_LETTER=-2
        const val ERROR_USER_EXIST=0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        control = intent.getParcelableExtra("baseDatos")!!
        Glide.with(this)
            .load("https://digitalhospital.com.sg/wp-content/uploads/2020/05/cropped-Digital-Hospital-Logo-FavIcon-2.png")
            .into(binding.imgLogo)

        binding.imgLogo.setOnClickListener { finish() }
        binding.especialidadButton.setOnClickListener() {information()}
        binding.registrarButton.setOnClickListener(){ejecutarRegistro()}
        binding.cancelarButton.setOnClickListener(){clean()}
        binding.exitButton.setOnClickListener(){System.exit(-1)}


        //-------Watchers
        binding.dniText.addTextChangedListener(watcherDNI)
        binding.titulacionText.addTextChangedListener(watcherTitulacion)
        binding.nombreText.addTextChangedListener(watcherNombre)
        binding.apellidosText.addTextChangedListener(watcherApellido)
    }

    /**
     * Ejecuta las diferentes comprobaciónes necesarias para comprobar que el formaulario es válido
     */
    private fun ejecutarRegistro() {
        isEspecialidadEscogida()
        var problem = false
        var count = 0
        //Comprueba que ninguno de los 5 textos tengan problemas
        while (!problem && count < 5) {
            if (problemExist[count])
                problem = true
            count++
        }
        //Si no hay problemas se ejecuta el formulario
        if (!problem) {
            control.addUsuario(binding.dniText.text.toString())
            clean()
        }
        //TODO podrias poner un toas en el else
    }

    /**
     * Nueva forma que sustituye al startForResult
     */
    private fun MainActivity.information() {
        //Crea el intent que pasa la base de datos (donde está el conjunto de especialidades)
        val myIntent = Intent(this, InformationActivity::class.java)
            .putExtra("dataBase", control)//Problema en el Parcelable
        //Ejecuta un registerForActivity result, con el nuevo método .launch
        startForResult.launch(myIntent)
    }

    /**
     * ???
     * Valor de tipo ActivityResultContracts, clase que controla los los permisos y los datos
     * que se reciben de un activity o aplicación llamada
     */
    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result->
        //Comprueba que si hay resultado, el intent tiene el codigo RESULT_OK
        //si salta un error no lo devuelve
        if (result.resultCode == Activity.RESULT_OK) {
            //Se saca el int con un codigo por defecto, por si es nulo
            val intent = result.data?.getIntExtra("code",-1)
            binding.especialidadText.text=intent.toString()
        }
    }

    /**
     * Calcula si el DNI es válido
     */
    fun calcularDNI(dni:String):Int{
        var isFine = NO_ERROR
        val letters= arrayOf('T','R','W','A','G','Y','F','O','D','X','B','N','J','Z','S','Q','V','H','L','C','K')
        var count=0
        //Comprueba que los 8 primeros caracteres no sean letras
        while(count<8&&isFine==NO_ERROR) {
            if (dni[count].isLetter()) {
                isFine = ERROR_NO_NUMBERS
            }
            count++
        }
        //Comprueba que la letra es la que devería ser
        if (isFine!=ERROR_NO_NUMBERS){
            val dniLetter= dni[8].uppercaseChar()
            var dniNumber=""
            for (i in 0..7)
                dniNumber+=dni[i]
            val number:Int =(dniNumber.toInt()%23)-1
            if (letters[number] != dniLetter.toChar())
                isFine= ERROR_LETTER
        }
        //Comprueba si el usuario ya existe
        if (isFine== NO_ERROR){
            isFine=control.checkUsuario(dni)
        }
        return isFine
    }

    fun isEspecialidadEscogida(){
        val text =binding.especialidadText.text.toString()
        problemExist[4]=  text.length==0
    }

    fun clean(){
        binding.dniText.text?.clear()
        binding.nombreText.text?.clear()
        binding.apellidosText.text?.clear()
        binding.titulacionText.text?.clear()
        binding.especialidadText.text=""
    }
    //---------------------------------------------No tiene por que servir
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    //------------------------------------Watchers

    val watcherDNI: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            val text=binding.dniText.text.toString()

            if (text.length==9) {
                val error=calcularDNI(text)
                if (error== ERROR_NO_NUMBERS) {
                    binding.DNILayout.error = "No se cumple el formato 11111111A"
                    problemExist[0]=true
                }
                else if(error== ERROR_LETTER) {
                    binding.DNILayout.error = "DNI incorrecto"
                    problemExist[0]=true
                }
                else if(error== ERROR_USER_EXIST) {
                    binding.DNILayout.error = "Usuario ya registrado"
                    problemExist[0]=true
                }
                else if(error==NO_ERROR) {
                    binding.DNILayout.error = ""
                    problemExist[0]=false
                }

            }else{
                problemExist[0]=true
            }

        }

    }

    val watcherNombre: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            val textNombre=binding.nombreText.text.toString()

            if (textNombre.length==0){
                binding.nombreLayout.error="Campo obligatorio"
                problemExist[1]=true
            }else{
                binding.nombreLayout.error=""
                problemExist[1]=false
            }

        }

    }

    val watcherApellido: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            val text=binding.apellidosText.text.toString()

            if (text.length==0){
                binding.apellidosLayout.error="Campo obligatorio"
                problemExist[2]=true
            }else{
                binding.apellidosLayout.error=""
                problemExist[2]=false
            }

        }

    }

    val watcherTitulacion: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            val text=binding.titulacionText.text.toString()
            val anno=Calendar.YEAR
            var annoTitulacion=0
            if (text.length!=0)
                annoTitulacion=text.toInt()

            if (annoTitulacion<1900&&annoTitulacion>anno) {
                binding.titulacionLayout.error = "Año no váliodo"
                problemExist[3]=true
            }else{
                binding.titulacionLayout.error = ""
                problemExist[3]=false
            }


        }

    }


}