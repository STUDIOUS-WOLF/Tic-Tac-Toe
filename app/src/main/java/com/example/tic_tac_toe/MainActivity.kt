package com.example.tic_tac_toe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.tic_tac_toe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    enum class Turn {
        Cross,Zero
    }
    private var boardList= mutableListOf<Button>()
    private var firstTurn=Turn.Cross
    private var currentTurn=Turn.Zero
    private var zeroScore=0
    private var crossScore =0
    private lateinit var  binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()

    }


    private fun initBoard() {
        boardList.add(binding.button1)
        boardList.add(binding.button2)
        boardList.add(binding.button3)
        boardList.add(binding.button4)
        boardList.add(binding.button5)
        boardList.add(binding.button6)
        boardList.add(binding.button7)
        boardList.add(binding.button8)
        boardList.add(binding.button9)
    }


    fun boardTapped(view: View){
        if(view !is Button)
            return
        addToBoard(view)
        if(victory(zero)){
            zeroScore++
            result("zero wins")
        }
       else if(victory(cross)) {
            crossScore++
            result("cross wins")
        }
        else if(boardIsFull())
            result("Draw")
    }


    private fun victory(checkFor: String): Boolean {
        // for horizontal
        if(binding.button1.text==checkFor && binding.button2.text==checkFor && binding.button3.text==checkFor )
            return true
        if(binding.button4.text==checkFor && binding.button5.text==checkFor && binding.button6.text==checkFor )
            return true
        if(binding.button7.text==checkFor && binding.button8.text==checkFor && binding.button9.text==checkFor )
            return true
        // for vertical
        if(binding.button1.text==checkFor && binding.button4.text==checkFor && binding.button7.text==checkFor )
            return true
        if(binding.button2.text==checkFor && binding.button5.text==checkFor && binding.button8.text==checkFor )
            return true
        if(binding.button3.text==checkFor && binding.button6.text==checkFor && binding.button9.text==checkFor )
            return true
        // for diagonals
        if(binding.button1.text==checkFor && binding.button5.text==checkFor && binding.button9.text==checkFor )
            return true
        if(binding.button3.text==checkFor && binding.button5.text==checkFor && binding.button7.text==checkFor )
            return true

        return false
    }

    private fun result(title: String) {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(title)
            .setPositiveButton("Reset") { _,_ -> resetBoard() }
            .show()
    }


    private fun boardIsFull(): Boolean {
        for(button in boardList) {
            if (button.text == "") {
                return false
            }
        }
            return true
    }

    private fun resetBoard() {
        for(button in boardList){
            button.text=""
        }
        if(firstTurn==Turn.Cross)
            firstTurn=Turn.Zero
        else
            firstTurn=Turn.Cross
        currentTurn=firstTurn

        scoreBoard(crossScore, zeroScore)
    }

    private fun scoreBoard(crossScore: Int, zeroScore: Int) {
        val player1Score= "Player 1 - $zeroScore"
        val player2Score= "Player 2 - $crossScore"
        binding.player1.text= player1Score
        binding.player2.text= player2Score
    }

    private fun addToBoard(button:Button){
        setTurnLabel()
        if(button.text != "")
            return
        if(currentTurn==Turn.Zero){
            button.text=zero
            currentTurn=Turn.Cross
        }
        else if(currentTurn==Turn.Cross){
            button.text=cross
            currentTurn=Turn.Zero
        }
    }


    private fun setTurnLabel(){
            if(currentTurn==Turn.Cross){
                binding.player1.setBackgroundColor(Color.CYAN)
                binding.player2.setBackgroundColor(Color.WHITE)
            }
            if(currentTurn==Turn.Zero){
                binding.player2.setBackgroundColor(Color.CYAN)
                binding.player1.setBackgroundColor(Color.WHITE)
            }
    }
    companion object{
        const val zero="0"
        const val cross="X"
    }
}