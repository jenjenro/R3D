package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable


class Nivel1Activity : AppCompatActivity() {
    private var correct: Int = 0
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nivel1)
        var question = Question("module5")
        val preguntas: ArrayList<ArrayList<String>> = ArrayList()
        val btnOpcion1 = findViewById<Button>(R.id.buttonOption1)
        val btnOpcion2 = findViewById<Button>(R.id.buttonOption2)
        val btnOpcion3 = findViewById<Button>(R.id.buttonOption3)

        val btnD = findViewById<Button>(R.id.buttonOption1)

        dialog()

        btnD.setOnClickListener{
            val dialogBinding = layoutInflater.inflate(R.layout.dialog_ok,null)

            val myDialog = android.app.Dialog(this)
            myDialog.setContentView(dialogBinding)

            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()
            }





        for (i in 1..5) {
            val questions = question.createQuestion(i)

            questions.forEach { question ->
                preguntas.add(question.value as ArrayList<String>)
            }
        }

        correctQuestion(preguntas, index)

        btnOpcion1.setOnClickListener {
            if (btnOpcion1.text == preguntas[index][1]) {
                correct++
                index++
                correctQuestion(preguntas, index)
            }
        }
        btnOpcion2.setOnClickListener {
            if (btnOpcion2.text == preguntas[index][1]) {
                correct++
                index++
                correctQuestion(preguntas, index)
            }
        }
        btnOpcion3.setOnClickListener {
            if (btnOpcion3.text == preguntas[index][1]) {
                correct++
                index++
                correctQuestion(preguntas, index)
            }
        }
    }

    private fun dialog(){
        val dialogBinding = layoutInflater.inflate(R.layout.dialog_ok,null)

        val myDialog = android.app.Dialog(this)
        myDialog.setContentView(dialogBinding)

        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }

    fun correctQuestion(preguntas: ArrayList<ArrayList<String>> = ArrayList(), index: Int) {
        val numRandoms: ArrayList<Int> = ArrayList()
        val numRandoms2: ArrayList<Int> = ArrayList()
        val textStatement = findViewById<TextView>(R.id.textStatement)
        val btnOpcion1 = findViewById<Button>(R.id.buttonOption1)
        val btnOpcion2 = findViewById<Button>(R.id.buttonOption2)
        val btnOpcion3 = findViewById<Button>(R.id.buttonOption3)

        if (index < preguntas.size) {
            println(preguntas[index].size.minus(1))
            if (preguntas[index].size.minus(1) == 2) {
                do {
                    val randomNumber = (1..preguntas[index].size.minus(1)).random()
                    if (randomNumber !in numRandoms) {
                        numRandoms.add(randomNumber)
                    }
                } while (numRandoms.size !== 2)
            } else{
                do {
                    val randomNumber = (1..preguntas[index].size.minus(1)).random()
                    if (randomNumber !in numRandoms) {
                        numRandoms.add(randomNumber)
                    }
                } while (numRandoms.size !== 3)
            }

            println(numRandoms)
            if (1 !in numRandoms) {
                val randomNumber = (1..3).random()
                numRandoms[randomNumber] = 1
            }
            println(numRandoms)

            if (index == 1) {
                btnOpcion3.visibility = Button.INVISIBLE
                textStatement.text = preguntas[index][0]
                btnOpcion1.text = preguntas[index][numRandoms[0]]
                btnOpcion2.text = preguntas[index][numRandoms[1]]
            } else {
                btnOpcion3.visibility = Button.VISIBLE
                textStatement.text = preguntas[index][0]
                btnOpcion1.text = preguntas[index][numRandoms[0]]
                btnOpcion2.text = preguntas[index][numRandoms[1]]
                btnOpcion3.text = preguntas[index][numRandoms[2]]

            }

        } else if (index >= preguntas.size) {
            val intent = Intent(this, Module1Activity::class.java)
            startActivity(intent)
        }
    }
}