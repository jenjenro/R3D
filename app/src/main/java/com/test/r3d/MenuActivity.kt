package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        val btnActividad1 = findViewById<Button>(R.id.btnActividad1)
        val btnActividad2 = findViewById<Button>(R.id.btnActividad2)

        btnActividad1.setOnClickListener {
            val intent = Intent(this, Actividad1Activity::class.java)
            startActivity(intent)
        }

        btnActividad2.setOnClickListener {
            val intent = Intent(this, Actividad2Activity::class.java)
            startActivity(intent)
        }
    }
}