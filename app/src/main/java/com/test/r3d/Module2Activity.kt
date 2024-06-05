package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class Module2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modulo2)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val intent = Intent(this@Module2Activity, MenuActivity::class.java)
                startActivity(intent)
            }
        })

        val btnNivel3 = findViewById<Button>(R.id.btnLevel3)
        val btnNivel4 = findViewById<Button>(R.id.btnLevel4)

        btnNivel3.setOnClickListener {
            val intent = Intent(this, Nivel3Activity::class.java)
            startActivity(intent)
        }

        btnNivel4.setOnClickListener {
            val intent = Intent(this, Nivel4Activity::class.java)
            startActivity(intent)
        }
    }
}