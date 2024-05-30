package com.test.r3d

import android.util.ArrayMap
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class Question (val module: String) {
    fun getQuestionsFlow(numberQuestion: String): Flow<Map<String, Any?>> = flow {
        val db = Firebase.firestore
        val snapshots = db.collection("${module}").document("question${numberQuestion}").get().await()
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
    fun corectQuestion(){

    }
    fun incorrectQuestion(){

    }
}