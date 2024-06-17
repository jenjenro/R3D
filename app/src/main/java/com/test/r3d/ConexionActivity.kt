package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ConexionActivity : AppCompatActivity() {

    private val bluetoothViewModel: BluetoothViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conexion)

        val btnBluethooth = findViewById<Button>(R.id.btnBlue)
        val btnMenu = findViewById<Button>(R.id.btnMenu)

        lifecycleScope.launch {
            bluetoothViewModel.data.observe(this@ConexionActivity, Observer { data ->
                btnMenu.text = data
                Log.i("BluetoothViewModel>sdds", bluetoothViewModel.data.value.toString())
            })
        }



        val bundle = intent.extras
        val user = bundle?.getSerializable("user") as User

        btnBluethooth.setOnClickListener {
            val intent = Intent(this, ControlActivity::class.java)
            startActivity(intent)
        }
        btnMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }

    }
}