package com.example.e4mdortegadaniel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.e4mdortegadaniel.databinding.ActivityMainBinding

class GameActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    var control =HangmanControl()
    var hangmanImages =ArrayList<String>()
    var countFails=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.hangmanImage.setBackgroundResource(R.drawable.hangman1)
        chargeLines()

        //binding.buttonB.visibility = View.INVISIBLE//Arapaho

        binding.buttonA.setOnClickListener {
            insertLetter('A')
            binding.buttonA.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonB.setOnClickListener {
            insertLetter('B')
            binding.buttonB.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonC.setOnClickListener {
            insertLetter('C')
            binding.buttonC.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonD.setOnClickListener {
            insertLetter('D')
            binding.buttonD.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonE.setOnClickListener {
            insertLetter('E')
            binding.buttonE.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonF.setOnClickListener {
            insertLetter('F')
            binding.buttonF.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonG.setOnClickListener {
            insertLetter('G')
            binding.buttonG.visibility= View.INVISIBLE
            isVictory()
        }

    }
    fun chargeLines(){
        binding.textToResolve.text=control.startGame()
    }

    fun insertLetter(letter:Char){
        if (control.checkWord(letter)==-1){
            countFails++
            changeHangman()
        }
        binding.textToResolve.text=control.resetLines()
    }

    fun changeHangman(){
        if (countFails==1){
            binding.hangmanImage.setBackgroundResource(R.drawable.hangman2)
        }else if(countFails==2){
            binding.hangmanImage.setBackgroundResource(R.drawable.hangman3)
        }else if(countFails==3){
            binding.hangmanImage.setBackgroundResource(R.drawable.hangman4)
        }else if(countFails==4){
            binding.hangmanImage.setBackgroundResource(R.drawable.hangman5)
        }else if(countFails==5){
            binding.hangmanImage.setBackgroundResource(R.drawable.hangman6)
        }else {
            binding.hangmanImage.setBackgroundResource(R.drawable.hangman7)
        }
    }

    /**
     * Envía al menú de victoria
     */
    fun isVictory(){
        if (control.isVictory()) {
            val myIntent = Intent(this, VictoryActivity::class.java)
            startActivity(myIntent)
        }
    }








}