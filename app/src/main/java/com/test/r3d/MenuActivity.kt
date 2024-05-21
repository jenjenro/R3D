package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        val btnActividad1 = findViewById<Button>(R.id.btnModulo1)
        val btnActividad2 = findViewById<Button>(R.id.btnModulo2)
        val btnActividad3 =findViewById<Button>(R.id.btnModulo3)

        btnActividad1.setOnClickListener {
            val intent = Intent(this, Modulo1::class.java)
            startActivity(intent)
        }

        btnActividad2.setOnClickListener {
            val intent = Intent(this, Modulo2::class.java)
            startActivity(intent)
        }

        btnActividad3.setOnClickListener {
            val intent = Intent(this, Modulo3::class.java)
            startActivity(intent)
        }
    }
}