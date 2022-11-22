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

        binding.buttonH.setOnClickListener {
            insertLetter('H')
            binding.buttonH.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonI.setOnClickListener {
            insertLetter('I')
            binding.buttonI.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonJ.setOnClickListener {
            insertLetter('J')
            binding.buttonJ.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonK.setOnClickListener {
            insertLetter('K')
            binding.buttonK.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonL.setOnClickListener {
            insertLetter('L')
            binding.buttonL.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonM.setOnClickListener {
            insertLetter('M')
            binding.buttonM.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonN.setOnClickListener {
            insertLetter('N')
            binding.buttonN.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonO.setOnClickListener {
            insertLetter('O')
            binding.buttonO.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonP.setOnClickListener {
            insertLetter('P')
            binding.buttonP.visibility= View.INVISIBLE
            isVictory()
        }
        binding.buttonQ.setOnClickListener {
            insertLetter('Q')
            binding.buttonQ.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonR.setOnClickListener {
            insertLetter('R')
            binding.buttonR.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonS.setOnClickListener {
            insertLetter('S')
            binding.buttonS.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonT.setOnClickListener {
            insertLetter('T')
            binding.buttonT.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonU.setOnClickListener {
            insertLetter('U')
            binding.buttonU.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonV.setOnClickListener {
            insertLetter('V')
            binding.buttonV.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonW.setOnClickListener {
            insertLetter('W')
            binding.buttonW.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonX.setOnClickListener {
            insertLetter('X')
            binding.buttonX.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonY.setOnClickListener {
            insertLetter('Y')
            binding.buttonY.visibility= View.INVISIBLE
            isVictory()
        }

        binding.buttonZ.setOnClickListener {
            insertLetter('Z')
            binding.buttonZ.visibility= View.INVISIBLE
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
            resetAll()
        }else if(countFails>=6){
            val myIntent = Intent(this, LostActivity::class.java)
            startActivity(myIntent)
            resetAll()
        }
    }

    /**
     * This function reset all
     */
    fun resetAll(){
        countFails=0
        binding.hangmanImage.setBackgroundResource(R.drawable.hangman1)
        chargeLines()
        binding.buttonA.visibility= View.INVISIBLE
        binding.buttonB.visibility= View.INVISIBLE
        binding.buttonC.visibility= View.INVISIBLE
        binding.buttonD.visibility= View.INVISIBLE
        binding.buttonE.visibility= View.INVISIBLE
        binding.buttonF.visibility= View.INVISIBLE
        binding.buttonG.visibility= View.INVISIBLE
        binding.buttonH.visibility= View.INVISIBLE
        binding.buttonI.visibility= View.INVISIBLE
        binding.buttonJ.visibility= View.INVISIBLE
        binding.buttonK.visibility= View.INVISIBLE
        binding.buttonL.visibility= View.INVISIBLE
        binding.buttonM.visibility= View.INVISIBLE
        binding.buttonN.visibility= View.INVISIBLE
        binding.buttonO.visibility= View.INVISIBLE
        binding.buttonP.visibility= View.INVISIBLE
        binding.buttonQ.visibility= View.INVISIBLE
        binding.buttonR.visibility= View.INVISIBLE
        binding.buttonS.visibility= View.INVISIBLE
        binding.buttonT.visibility= View.INVISIBLE
        binding.buttonU.visibility= View.INVISIBLE
        binding.buttonV.visibility= View.INVISIBLE
        binding.buttonW.visibility= View.INVISIBLE
        binding.buttonX.visibility= View.INVISIBLE
        binding.buttonY.visibility= View.INVISIBLE
        binding.buttonZ.visibility= View.INVISIBLE

    }







}