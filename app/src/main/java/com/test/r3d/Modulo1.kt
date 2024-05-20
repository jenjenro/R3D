package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Modulo1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.modulo1)


        val btnNivel1 = findViewById<Button>(R.id.btnNivel1)
        val btnNivel2 = findViewById<Button>(R.id.btnNivel2)

        btnNivel1.setOnClickListener {
            val intent = Intent(this, Nivel1Activity::class.java)
            startActivity(intent)
        }


    }
}