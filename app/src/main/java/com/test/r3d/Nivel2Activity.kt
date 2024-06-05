package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class Nivel2Activity : AppCompatActivity() {
    private var correct: Int = 0
    private var index: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.nivel2)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

            }
        })

        val question = Question("module2")
        val statement: ArrayList<String> = ArrayList()
        val preguntas: ArrayList<ArrayList<String>> = ArrayList()
        val btnOpcion1 = findViewById<Button>(R.id.btnOp1lv2)
        val btnOpcion2 = findViewById<Button>(R.id.btnOp2lv2)
        val btnOpcion3 = findViewById<Button>(R.id.btnOp3lv2)

        for (i in 1..5) {
            val questions = question.createQuestion(i)

            questions.forEach { question ->
                preguntas.add(question.value as ArrayList<String>)
            }
            val statements = question.createStatements(i)
            statements.forEach { i ->
                statement.add(i.value as String)
            }
        }

        correctQuestion(preguntas, index, statement)

        btnOpcion1.setOnClickListener {
            if (btnOpcion1.text == preguntas[index][1]) {
                correct++
                index++
                correctQuestion(preguntas, index, statement)
            }
        }
        btnOpcion2.setOnClickListener {
            if (btnOpcion2.text == preguntas[index][1]) {
                correct++
                index++
                correctQuestion(preguntas, index, statement)
            }
        }
        btnOpcion3.setOnClickListener {
            if (btnOpcion3.text == preguntas[index][1]) {
                correct++
                index++
                correctQuestion(preguntas, index, statement)
            }
        }
    }

    private fun correctQuestion(preguntas: ArrayList<ArrayList<String>> = ArrayList(), index: Int, statements: ArrayList<String> = ArrayList()) {
        val numRandoms: ArrayList<Int> = ArrayList()
        val textStatement = findViewById<TextView>(R.id.textStatementlvl2)
        val textStatement1 = findViewById<TextView>(R.id.textStatementlvl2_1)
        val btnOpcion1 = findViewById<Button>(R.id.btnOp1lv2)
        val btnOpcion2 = findViewById<Button>(R.id.btnOp2lv2)
        val btnOpcion3 = findViewById<Button>(R.id.btnOp3lv2)

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
            if (index == 0 || index == 1) {
                var operador = ""
                if (index == 0){
                    operador = "+"
                } else if (index == 1){
                    operador = "-"
                }
                val operacion = preguntas[index][0].split(" ")
                textStatement1.text = " ${operacion[0]}\n${operador}${operacion[1]}\n"
                textStatement.text = statements[index]
                btnOpcion1.text = preguntas[index][numRandoms[0]]
                btnOpcion2.text = preguntas[index][numRandoms[1]]
                btnOpcion3.text = preguntas[index][numRandoms[2]]

            } else {
                textStatement1.text = preguntas[index][0]
                textStatement.text = statements[index]
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