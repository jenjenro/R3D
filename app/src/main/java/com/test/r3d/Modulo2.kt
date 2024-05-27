package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Modulo2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.modulo2)

        val btnLevel3 = findViewById<Button>(R.id.btnLevel3)
        val btnLevel4 = findViewById<Button>(R.id.btnLevel4)
        //botón de nivel 2 bloqueado
        btnLevel3.isEnabled = false
        btnLevel4.isEnabled = false

        btnLevel3.setOnClickListener {
            val intent = Intent(this, Nivel3Activity::class.java)
            startActivity(intent)
        }

        btnLevel3.setOnClickListener {
            val intent = Intent(this, Nivel4Activity::class.java)
            startActivity(intent)
        }
    }
}