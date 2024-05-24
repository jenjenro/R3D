package com.test.r3d

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.app.Dialog
import android.util.ArrayMap
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.window.Dialog
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
    private var index2:Int= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nivel1)



        val textStatement  = findViewById<TextView>(R.id.textStatement)
        val textStatementQuestion1 = findViewById<TextView>(R.id.textStatementQuestion1)
        val btn = findViewById<Button>(R.id.button19)

        val preguntas: ArrayList<ArrayList<String>> = ArrayList()
        val enunciado: ArrayList<String> = ArrayList()
        for ( i in 1..5) {
            val questions = createQuestion(i)
            val statements = createStatements(i)
            statements.forEach { statement ->
                enunciado.add(statement.value as String)
            }
            questions.forEach { question ->
                preguntas.add(question.value as ArrayList<String>)
            }
        }
        textStatement.text = enunciado[index2]
        textStatementQuestion1.text = preguntas[index][index]
        btn.setOnClickListener {
            if(index<preguntas.size) {
                textStatementQuestion1.text = preguntas[index][0]

            } else if (index>preguntas.size) {
                val intent = Intent(this, Module1Activity::class.java)
                startActivity(intent)
            }
            if(index2<enunciado.size) {
                textStatement.text = enunciado[index2]

            }
            if (index2>=enunciado.size) {
                val intent = Intent(this, Module1Activity::class.java)
                startActivity(intent)
            }
            index2++;
            index++;

        }



    }


    }

    fun getQuestionsFlow(numberQuestion: String): Flow<Map<String, Any?>> = flow {
        val db = Firebase.firestore
        val snapshots = db.collection("module1").document("question${numberQuestion}").get().await()
        emit(snapshots.data!!)
    }

    fun createQuestion(numQuestion: Int): ArrayMap<String, Any?> {
        val questions = ArrayMap<String, Any?>()
        runBlocking {
            getQuestionsFlow(numQuestion.toString()).collect{ question ->
                val numRandom = (1..question.size.minus(1)).random()
                question.filter { it.key.contains("variable${numRandom}")}.forEach{
                    questions[it.key] = it.value
                }
            }
        }
        return questions
    }
    fun createStatements(numQuestion: Int): ArrayMap<String, Any?> {
        var statements = ArrayMap<String, Any?>()
        runBlocking {
            getQuestionsFlow(numQuestion.toString()).collect { question ->
                question.filter { it.key == "pregunta" }.forEach {
                    statements[it.key] = it.value
                }
            }
        }
        return statements
    }
