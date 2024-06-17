package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MenuActivity : AppCompatActivity() {
    private val bluetoothViewModel: BluetoothViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        val bundle = intent?.extras
        val user = bundle?.getSerializable("user") as User

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val intent = Intent(this@MenuActivity, ConexionActivity::class.java)
                var bundle = Bundle()
                bundle.putSerializable("user", user)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        val btnModule1 = findViewById<Button>(R.id.btnModule1)
        val btnModule2 = findViewById<Button>(R.id.btnModule2)
        val btnModule3 = findViewById<Button>(R.id.btnModule3)

        Log.i("BluetoothViewModel>", bluetoothViewModel.data.value.toString())
        bluetoothViewModel.data.observe(this, Observer { data ->
            btnModule1.text = data
        })

        btnModule1.setOnClickListener {
            val intent = Intent(this, Module1Activity::class.java)
            var bundle = Bundle()
            bundle.putSerializable("user", user)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        btnModule2.setOnClickListener {
            val intent = Intent(this, Module2Activity::class.java)
            var bundle = Bundle()
            bundle.putSerializable("user", user)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        btnModule3.setOnClickListener {
            val intent = Intent(this, Module3Activity::class.java)
            var bundle = Bundle()
            bundle.putSerializable("user", user)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}