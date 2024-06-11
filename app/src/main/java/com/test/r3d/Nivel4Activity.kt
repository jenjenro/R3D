package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Nivel4Activity : AppCompatActivity() {

    private var correct: Int = 0
    private var index: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nivel4)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

            }
        })

        val question = Question("module4")
        val preguntas: ArrayList<ArrayList<String>> = ArrayList()
        val btnOpcion1 = findViewById<Button>(R.id.btnOp1lv4)
        val btnOpcion2 = findViewById<Button>(R.id.btnOp2lv4)
        val btnOpcion3 = findViewById<Button>(R.id.btnOp3lv4)

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

    private fun correctQuestion(preguntas: ArrayList<ArrayList<String>> = ArrayList(), index: Int) {
        val numRandoms: ArrayList<Int> = ArrayList()
        val textStatement = findViewById<TextView>(R.id.textStatementlvl4)
        val btnOpcion1 = findViewById<Button>(R.id.btnOp1lv4)
        val btnOpcion2 = findViewById<Button>(R.id.btnOp2lv4)
        val btnOpcion3 = findViewById<Button>(R.id.btnOp3lv4)

        if (index < preguntas.size) {
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

            if (1 !in numRandoms) {
                val randomNumber = (0..2).random()
                numRandoms[randomNumber] = 1
            }

            textStatement.text = preguntas[index][0]
            btnOpcion1.text = preguntas[index][numRandoms[0]]
            btnOpcion2.text = preguntas[index][numRandoms[1]]
            btnOpcion3.text = preguntas[index][numRandoms[2]]

        } else if (index >= preguntas.size) {
            val intent = Intent(this, Module2Activity::class.java)
            startActivity(intent)
        }
    }
}