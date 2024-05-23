package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Module1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modulo1)


        val btnNivel1 = findViewById<Button>(R.id.btnLevel1)
        val btnNivel2 = findViewById<Button>(R.id.btnLevel2)

        btnNivel1.setOnClickListener {
            val intent = Intent(this, Nivel1Activity::class.java)
            startActivity(intent)
        }

        btnNivel2.setOnClickListener {
            val intent = Intent(this, Nivel2Activity::class.java)
            startActivity(intent)
        }

    }
}