package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        val bundle = intent.extras
        val user = bundle?.getSerializable("user") as User

        val btnModule1 = findViewById<Button>(R.id.btnModule1)
        val btnModule2 = findViewById<Button>(R.id.btnModule2)
        val btnModule3 = findViewById<Button>(R.id.btnModule3)

        btnModule1.setOnClickListener {
            val intent = Intent(this, Module1Activity::class.java)
            startActivity(intent)
        }

        btnModule2.setOnClickListener {
            val intent = Intent(this, Module2Activity::class.java)
            startActivity(intent)
        }

        btnModule3.setOnClickListener {
            val intent = Intent(this, Module3Activity::class.java)
            startActivity(intent)
        }
    }
}