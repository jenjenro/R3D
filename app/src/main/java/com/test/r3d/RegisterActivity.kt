package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        val btnPrueba = findViewById<Button>(R.id.btnActividad1)
        btnPrueba.setOnClickListener {
            ingresarDatos()
        }
    }

    fun
            ingresarDatos(){
        val db = Firebase.firestore
        val name: EditText? = findViewById<EditText>(R.id.inputName)
        val pass: EditText? = findViewById<EditText>(R.id.inputPass)
        val age : EditText? = findViewById<EditText>(R.id.inputAge)

        if (name?.text.toString().isEmpty() || pass?.text.toString().isEmpty() || age?.text.toString().isEmpty()){
            println("Faltan datos")
        }
        else {
            val user = hashMapOf(
                "name" to name?.text.toString(),
                "age" to age?.text.toString(),
                "password" to pass?.text.toString()
            )
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    println("DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    println("Error adding document $e")
                }
            nextScreen()
        }
    }
    fun nextScreen(){
        val intent = Intent(this, ConexionActivity::class.java)
        startActivity(intent)
    }
}