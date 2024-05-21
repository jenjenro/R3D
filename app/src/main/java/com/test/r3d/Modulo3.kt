package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Modulo3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.modulo3)

        val btnNivel5 = findViewById<Button>(R.id.btnNivel5)
        val btnNivel6 = findViewById<Button>(R.id.btnNivel6)
        //botón de nivel 2 bloqueado
        btnNivel5.isEnabled = false
        btnNivel6.isEnabled = false

        btnNivel5.setOnClickListener {
            val intent = Intent(this, Nivel5Activity::class.java)
            startActivity(intent)
        }

        btnNivel6.setOnClickListener {
            val intent = Intent(this, Nivel6Activity::class.java)
            startActivity(intent)
        }

    }
}