package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.util.ArrayMap
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import java.util.ArrayList
import kotlin.math.absoluteValue


class Nivel1Activity : AppCompatActivity() {
    private var index:Int=0
    private var index2:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nivel1)
        var question :Question = Question("module5")

        val textStatement  = findViewById<TextView>(R.id.textStatement)
        val btn = findViewById<Button>(R.id.button19)

        val preguntas: ArrayList<ArrayList<String>> = ArrayList()
        val enunciado: ArrayList<String> = ArrayList()
        for ( i in 1..5) {
            val questions = question.createQuestion(i)
            val statements = question.createStatements(i)
            statements.forEach { statement ->
                enunciado.add(statement.value as String)
            }
            questions.forEach { question ->
                preguntas.add(question.value as ArrayList<String>)
            }
        }
        textStatement.text = preguntas[index][0]
        btn.setOnClickListener {
            index2++;
            index++;
            if(index<preguntas.size) {
                textStatement.text = preguntas[index][0]

            } else if (index>=preguntas.size) {
                val intent = Intent(this, Module1Activity::class.java)
                startActivity(intent)
            }


        }


    }
}