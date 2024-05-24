package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Modulo2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.modulo2)

        val btnNivel3 = findViewById<Button>(R.id.btnLevel3)
        val btnNivel4 = findViewById<Button>(R.id.btnLevel4)
        //botón de nivel 2 bloqueado
        btnNivel3.isEnabled = false
        btnNivel4.isEnabled = false

        btnNivel3.setOnClickListener {
            val intent = Intent(this, Nivel3Activity::class.java)
            startActivity(intent)
        }

        btnNivel3.setOnClickListener {
            val intent = Intent(this, Nivel4Activity::class.java)
            startActivity(intent)
        }
    }
}