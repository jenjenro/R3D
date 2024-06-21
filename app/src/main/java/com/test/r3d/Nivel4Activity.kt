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

class Nivel4Activity : AppCompatActivity() {

    private var correct: Int = 0
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nivel4)

        val bundle = intent?.extras
        var user = bundle?.getSerializable("user") as User

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

            }
        })

        GlobalVariable.updateVariable("")

        val question = Question("module4")
        val preguntas: ArrayList<ArrayList<String>> = ArrayList()
        val btnOp1lv4Blue = findViewById<Button>(R.id.btnOp1lv4Blue)
        val btnOp2lv4Red = findViewById<Button>(R.id.btnOp2lv4Red)
        val btnOp3lv4Yellow = findViewById<Button>(R.id.btnOp3lv4Yellow)

        for (i in 1..5) {
            val questions = question.createQuestion(i)

            questions.forEach { question ->
                preguntas.add(question.value as ArrayList<String>)
            }
        }

        correctQuestion(preguntas, index, user)

        GlobalVariable.myVariable.observe(this) { value ->
            if (value == ""){

            } else{
                if (btnOp1lv4Blue.text == preguntas[index][1] && value.trim() == "Azul") {
                    Log.i("Nivel1Activity", value)
                    correct++
                    index++
                    dialogOk(preguntas, index, user)
                } else if (btnOp2lv4Red.text == preguntas[index][1] && value.trim() == "Rojo") {
                    Log.i("Nivel1Activity", value)
                    correct++
                    index++
                    dialogOk(preguntas, index, user)
                } else if (btnOp3lv4Yellow.text == preguntas[index][1] && value.trim() == "Amarillo") {
                    Log.i("Nivel1Activity", value)
                    correct++
                    index++
                    dialogOk(preguntas, index, user)
                } else {
                    Log.i("Nivel1Activity", value + "Error")
                    index++
                    dialogUps(preguntas, index, user)
                }
            }
        }

        btnOp1lv4Blue.setOnClickListener {
            if (btnOp1lv4Blue.text == preguntas[index][1]) {
                correct++
                index++
                dialogOk(preguntas, index, user)
            } else{
                index++
                dialogUps(preguntas, index, user)

            }
        }
        btnOp2lv4Red.setOnClickListener {
            if (btnOp2lv4Red.text == preguntas[index][1]) {
                correct++
                index++
                dialogOk(preguntas, index, user)
            } else{
                index++
                dialogUps(preguntas, index, user)

            }
        }
        btnOp3lv4Yellow.setOnClickListener {
            if (btnOp3lv4Yellow.text == preguntas[index][1]) {
                correct++
                index++
                dialogOk(preguntas, index, user)
            } else{
                index++
                dialogUps(preguntas, index, user)

            }
        }
    }

    private fun dialogOk(preguntas: java.util.ArrayList<java.util.ArrayList<String>> = java.util.ArrayList(), index: Int, user: User) {

        val dialogBinding = layoutInflater.inflate(R.layout.dialog_ok ,null)

        val myDialog = android.app.Dialog(this)
        myDialog.setContentView(dialogBinding)
        val btnOk = dialogBinding.findViewById<Button>(R.id.alertDialog)
        btnOk.setOnClickListener {
            correctQuestion(preguntas, index, user)
            myDialog.dismiss()
        }
        myDialog.setCancelable(false)
        myDialog.setCanceledOnTouchOutside(false)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }

    private fun dialogUps(preguntas: java.util.ArrayList<java.util.ArrayList<String>> = java.util.ArrayList(), index: Int, user: User) {

        val dialogBinding = layoutInflater.inflate(R.layout.dialog_ups ,null)

        val myDialog = android.app.Dialog(this)
        myDialog.setContentView(dialogBinding)
        val btnOk = dialogBinding.findViewById<Button>(R.id.alertDialog1)
        btnOk.setOnClickListener {
            correctQuestion(preguntas, index, user)
            myDialog.dismiss()
        }
        myDialog.setCancelable(false)
        myDialog.setCanceledOnTouchOutside(false)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }

    private fun correctQuestion(preguntas: ArrayList<ArrayList<String>> = ArrayList(), index: Int, user: User) {
        val numRandoms: ArrayList<Int> = ArrayList()
        val textStatement = findViewById<TextView>(R.id.textStatementlvl4)
        val btnOpcion1 = findViewById<Button>(R.id.btnOp1lv4Blue)
        val btnOpcion2 = findViewById<Button>(R.id.btnOp2lv4Red)
        val btnOpcion3 = findViewById<Button>(R.id.btnOp3lv4Yellow)

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
            user.puntajelvl4 = correct
            val intent = Intent(this, Module2Activity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }
}