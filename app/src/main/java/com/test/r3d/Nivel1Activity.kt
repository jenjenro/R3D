package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import androidx.lifecycle.Observer


class Nivel1Activity : AppCompatActivity() {
    private var correct: Int = 0
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.nivel1)

        val bundle = intent?.extras
        var user = bundle?.getSerializable("user") as User

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
            }
        })
        GlobalVariable.updateVariable("")

        val question = Question("module1")
        val statement: ArrayList<String> = ArrayList()
        val preguntas: ArrayList<ArrayList<String>> = ArrayList()
        val btnOp1lv1Blue = findViewById<Button>(R.id.btnOp1lv1Blue)
        val btnOp2lv1Red = findViewById<Button>(R.id.btnOp2lv1Red)
        val btnOp3lv1Yellow = findViewById<Button>(R.id.btnOp3lv1Yellow)

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

        GlobalVariable.myVariable.observe(this, Observer { value ->
            if (value == ""){

            } else{
                if (btnOp1lv1Blue.text == preguntas[index][1] && value.trim() == "Azul") {
                    Log.i("Nivel1Activity", value)
                    correct++
                    index++
                    dialogOk(preguntas, index, statement, user)
                } else if (btnOp2lv1Red.text == preguntas[index][1] && value.trim() == "Rojo") {
                    Log.i("Nivel1Activity", value)
                    correct++
                    index++
                    dialogOk(preguntas, index, statement, user)
                } else if (btnOp3lv1Yellow.text == preguntas[index][1] && value.trim() == "Amarillo") {
                    Log.i("Nivel1Activity", value)
                    correct++
                    index++
                    dialogOk(preguntas, index, statement, user)
                } else {
                    index++
                    dialogUps(preguntas, index, statement, user)
                }
            }
        })

        btnOp1lv1Blue.setOnClickListener {
            if (btnOp1lv1Blue.text == preguntas[index][1]) {
                correct++
                index++
                dialogOk(preguntas, index, statement, user)
            } else{
                index++
                dialogUps(preguntas, index, statement, user)

            }
        }

        btnOp2lv1Red.setOnClickListener {
            if (btnOp2lv1Red.text == preguntas[index][1]) {
                correct++
                index++
                dialogOk(preguntas, index, statement, user)
            } else{
                index++
                dialogUps(preguntas, index, statement, user)

            }
        }
        btnOp3lv1Yellow.setOnClickListener {
            if (btnOp3lv1Yellow.text == preguntas[index][1]) {
                correct++
                index++
                dialogOk(preguntas, index, statement, user)
            } else{
                index++
                dialogUps(preguntas, index, statement, user)

            }
        }

    }

    private fun dialogOk(preguntas: ArrayList<ArrayList<String>> = ArrayList(), index: Int, statements: ArrayList<String> = ArrayList(), user: User) {

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

    private fun dialogUps(preguntas: ArrayList<ArrayList<String>> = ArrayList(), index: Int, statements: ArrayList<String> = ArrayList(), user: User) {

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

    fun correctQuestion(preguntas: ArrayList<ArrayList<String>> = ArrayList(), index: Int, statements: ArrayList<String> = ArrayList(), user: User) {
        val numRandoms: ArrayList<Int> = ArrayList()
        val textStatement = findViewById<TextView>(R.id.textStatement)
        val textStatement1 = findViewById<TextView>(R.id.textStatement1)
        val btnOpcion1 = findViewById<Button>(R.id.btnOp1lv1Blue)
        val btnOpcion2 = findViewById<Button>(R.id.btnOp2lv1Red)
        val btnOpcion3 = findViewById<Button>(R.id.btnOp3lv1Yellow)

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

            user.puntajelvl1 = correct
            val intent = Intent(this, Module1Activity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }
}