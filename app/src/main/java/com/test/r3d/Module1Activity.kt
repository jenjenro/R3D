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

class Module1Activity : AppCompatActivity() {

    private val bluetoothViewModel: BluetoothViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modulo1)

        val bundle2 = intent.extras
        var user = bundle2?.getSerializable("user") as User

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val intent = Intent(this@Module1Activity, MenuActivity::class.java)
                var bundle = Bundle()
                bundle.putSerializable("user", user)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        var textView7 = findViewById<TextView>(R.id.textView7)
        Log.i("BluetoothViewModel>", bluetoothViewModel.data.value.toString())
        bluetoothViewModel.data.observe(this, Observer { data ->
            textView7.text = data
        })

        val btnNivel1 = findViewById<Button>(R.id.btnLevel1)
        val btnNivel2 = findViewById<Button>(R.id.btnLevel2)

        btnNivel1.setOnClickListener {
            val intent = Intent(this, Nivel1Activity::class.java)
            var bundle = Bundle()
            bundle.putSerializable("user", user)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        btnNivel2.setOnClickListener {
            val intent = Intent(this, Nivel2Activity::class.java)
            var bundle = Bundle()
            bundle.putSerializable("user", user)
            intent.putExtras(bundle)
            startActivity(intent)
        }

    }
}