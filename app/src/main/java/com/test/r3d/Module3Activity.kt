package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Module3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modulo3)

        val btnNivel5 = findViewById<Button>(R.id.btnLevel5)
        val btnNivel6 = findViewById<Button>(R.id.btnLevel6)

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