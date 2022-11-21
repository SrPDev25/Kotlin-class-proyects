package com.example.e1mdortegadaniel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.e1mdortegadaniel.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySecondBinding
    private var count: Int =3
    private var users= arrayListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {

        //Inicio del layout
        super.onCreate(savedInstanceState)
        binding=ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this)
            .load("https://librosking.com/wp-content/uploads/2022/04/Logo-Libros-king-2022.1.png")
            .into(binding.imgLogo)
        binding.whiteLayout.background.alpha=200
        //Variables
        users.add(User("usuario1","password1"))
        users.add(User("usuario2","password2"))

        binding.loginLayout.buttonLogin.setOnClickListener {
            val user:String=binding.loginLayout.user.text.toString()
            val pass:String=binding.loginLayout.pass.text.toString()
            if (isUser(user,pass)){
                Toast.makeText(this,"Login correcto, bienvenido",
                    Toast.LENGTH_LONG)
                    .show()//muestra la tostada
            }else{
                count--
                if (count!=0) {
                    Toast.makeText(
                        this, "Usuario o contraseña incorrectos, te quedan " + this.count+" intentos",
                        Toast.LENGTH_LONG
                    )
                        .show()//muestra la tostada
                }else{
                    Toast.makeText(
                        this, "Superaste los intentos",
                        Toast.LENGTH_LONG
                    )
                        .show()//muestra la tostada
                    finishAffinity()
                    finishAndRemoveTask()
                }
            }
            print("hola")

        }




    }

    /**
     * Comprueba si existe el usuario en el arrayList
     */
    private fun isUser(user:String, pass:String):Boolean{
        var fine =false
        if (users.indexOf(User(user,pass))!=-1){
            fine=true
        }
        return fine
    }
}