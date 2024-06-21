package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConexionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conexion)


        val btnBluethooth = findViewById<Button>(R.id.btnBlue)
        val btnMenu = findViewById<Button>(R.id.btnMenu)
        val button5 = findViewById<Button>(R.id.button5)

        val bundle = intent.extras
        val user = bundle?.getSerializable("user") as User

        btnBluethooth.setOnClickListener {
            val intent = Intent(this, ControlActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
        btnMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }

    }
}