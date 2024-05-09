package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ConexionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conexion)
        val btnBluethooth = findViewById<Button>(R.id.btnBlue)
        val btnMenu = findViewById<Button>(R.id.btnMenu)

        btnBluethooth.setOnClickListener {
            val intent = Intent(this, ControlActivity::class.java)
            startActivity(intent)
        }
        btnMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}