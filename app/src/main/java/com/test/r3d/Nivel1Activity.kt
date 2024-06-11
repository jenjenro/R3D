package com.test.r3d

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
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

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

            }
        })

        val question = Question("module1")
        val statement: ArrayList<String> = ArrayList()
        val preguntas: ArrayList<ArrayList<String>> = ArrayList()
        val btnOpcion1 = findViewById<Button>(R.id.btnOp1lv1)
        val btnOpcion2 = findViewById<Button>(R.id.btnOp2lv1)
        val btnOpcion3 = findViewById<Button>(R.id.btnOp3lv1)

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
                dialog(preguntas, index, statement, true)
            } else{

            }
        }
        btnOpcion2.setOnClickListener {
            if (btnOpcion2.text == preguntas[index][1]) {
                correct++
                index++
                dialog(preguntas, index, statement, true)
            }
        }
        btnOpcion3.setOnClickListener {
            if (btnOpcion3.text == preguntas[index][1]) {
                correct++
                index++
                dialog(preguntas, index, statement, true)
            }
        }
    }

    private fun dialog(preguntas: ArrayList<ArrayList<String>> = ArrayList(), index: Int, statements: ArrayList<String> = ArrayList(), option : Boolean) {
        var opt : Int = 0
        if (option) {
            opt = R.layout.dialog_ok
        }
        val dialogBinding = layoutInflater.inflate(opt ,null)

        val myDialog = android.app.Dialog(this)
        myDialog.setContentView(dialogBinding)
        val btnOk = dialogBinding.findViewById<Button>(R.id.alertDialog)
        btnOk.setOnClickListener {
            correctQuestion(preguntas, index, statements)
            myDialog.dismiss()
        }
        myDialog.setCancelable(false)
        myDialog.setCanceledOnTouchOutside(false)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }

    fun correctQuestion(preguntas: ArrayList<ArrayList<String>> = ArrayList(), index: Int, statements: ArrayList<String> = ArrayList()) {
        val numRandoms: ArrayList<Int> = ArrayList()
        val textStatement = findViewById<TextView>(R.id.textStatement)
        val textStatement1 = findViewById<TextView>(R.id.textStatement1)
        val btnOpcion1 = findViewById<Button>(R.id.btnOp1lv1)
        val btnOpcion2 = findViewById<Button>(R.id.btnOp2lv1)
        val btnOpcion3 = findViewById<Button>(R.id.btnOp3lv1)

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

            if (1 !in numRandoms) {
                val randomNumber = (0..2).random()
                numRandoms[randomNumber] = 1
            }

            if (index == 2) {
                btnOpcion3.visibility = Button.INVISIBLE
                textStatement1.text = preguntas[index][0]
                textStatement.text = statements[index]
                btnOpcion1.text = preguntas[index][numRandoms[0]]
                btnOpcion2.text = preguntas[index][numRandoms[1]]
            } else {
                btnOpcion3.visibility = Button.VISIBLE
                textStatement.text = statements[index]
                textStatement1.text = preguntas[index][0]
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