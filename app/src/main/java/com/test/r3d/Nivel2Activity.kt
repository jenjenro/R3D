package com.test.r3d

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData

class Nivel2Activity : AppCompatActivity() {
    private var correct: Int = 0
    private var index: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.nivel2)

        val bundle = intent?.extras
        var user = bundle?.getSerializable("user") as User

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

            }
        })

        GlobalVariable.updateVariable("")

        val question = Question("module2")
        val statement: ArrayList<String> = ArrayList()
        val preguntas: ArrayList<ArrayList<String>> = ArrayList()
        val btnOp1lv2Blue = findViewById<Button>(R.id.btnOp1lv2Blue)
        val btnOp2lv2Red = findViewById<Button>(R.id.btnOp2lv2Red)
        val btnOp3lv2Yellow = findViewById<Button>(R.id.btnOp3lv2Yellow)
        val buttonlvl2_2 = findViewById<Button>(R.id.buttonlvl2_2)


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

        correctQuestion(preguntas, index, statement, user)

        GlobalVariable.myVariable.observe(this) { value ->
            if (value == ""){

            } else{
                if (btnOp1lv2Blue.text == preguntas[index][1] && value.trim() == "Azul") {
                    Log.i("Nivel1Activity", value)
                    correct++
                    index++
                    dialogOk(preguntas, index, statement, user)
                } else if (btnOp2lv2Red.text == preguntas[index][1] && value.trim() == "Rojo") {
                    Log.i("Nivel1Activity", value)
                    correct++
                    index++
                    dialogOk(preguntas, index, statement, user)
                } else if (btnOp3lv2Yellow.text == preguntas[index][1] && value.trim() == "Amarillo") {
                    Log.i("Nivel1Activity", value)
                    correct++
                    index++
                    dialogOk(preguntas, index, statement, user)
                } else {
                    Log.i("Nivel1Activity", value + "Error")
                    index++
                    dialogUps(preguntas, index, statement, user)
                }
            }
        }


        btnOp1lv2Blue.setOnClickListener {
            if (btnOp1lv2Blue.text == preguntas[index][1]) {
                correct++
                index++
                dialogOk(preguntas, index, statement, user)
            } else{
                index++
                dialogUps(preguntas, index, statement, user)

            }
        }
        btnOp2lv2Red.setOnClickListener {
            if (btnOp2lv2Red.text == preguntas[index][1]) {
                correct++
                index++
                dialogOk(preguntas, index, statement, user)
            } else{
                index++
                dialogUps(preguntas, index, statement, user)

            }
        }
        btnOp3lv2Yellow.setOnClickListener {
            if (btnOp3lv2Yellow.text == preguntas[index][1]) {
                correct++
                index++
                dialogOk(preguntas, index, statement, user)
            } else{
                index++
                dialogUps(preguntas, index, statement, user)

            }
        }


    }

    private fun dialogOk(preguntas: java.util.ArrayList<java.util.ArrayList<String>> = java.util.ArrayList(), index: Int, statements: java.util.ArrayList<String> = java.util.ArrayList(), user: User) {

        val dialogBinding = layoutInflater.inflate(R.layout.dialog_ok ,null)

        val myDialog = android.app.Dialog(this)
        myDialog.setContentView(dialogBinding)
        val btnOk = dialogBinding.findViewById<Button>(R.id.alertDialog)
        btnOk.setOnClickListener {
            correctQuestion(preguntas, index, statements, user)
            myDialog.dismiss()
        }
        myDialog.setCancelable(false)
        myDialog.setCanceledOnTouchOutside(false)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }

    private fun dialogUps(preguntas: java.util.ArrayList<java.util.ArrayList<String>> = java.util.ArrayList(), index: Int, statements: java.util.ArrayList<String> = java.util.ArrayList(), user: User) {

        val dialogBinding = layoutInflater.inflate(R.layout.dialog_ups ,null)

        val myDialog = android.app.Dialog(this)
        myDialog.setContentView(dialogBinding)
        val btnOk = dialogBinding.findViewById<Button>(R.id.alertDialog1)
        btnOk.setOnClickListener {
            correctQuestion(preguntas, index, statements, user)
            myDialog.dismiss()
        }
        myDialog.setCancelable(false)
        myDialog.setCanceledOnTouchOutside(false)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }

    private fun correctQuestion(preguntas: ArrayList<ArrayList<String>> = ArrayList(), index: Int, statements: ArrayList<String> = ArrayList(), user: User) {
        val numRandoms: ArrayList<Int> = ArrayList()
        val textStatement = findViewById<TextView>(R.id.textStatementlvl2)
        val textStatement1 = findViewById<TextView>(R.id.textStatementlvl2_1)
        val btnOpcion1 = findViewById<Button>(R.id.btnOp1lv2Blue)
        val btnOpcion2 = findViewById<Button>(R.id.btnOp2lv2Red)
        val btnOpcion3 = findViewById<Button>(R.id.btnOp3lv2Yellow)

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

            user.puntajelvl2 = correct
            val intent = Intent(this, Module1Activity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }
}