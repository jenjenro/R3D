package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class Module3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modulo3)
        val bundle2 = intent.extras
        var user = bundle2?.getSerializable("user") as User

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val intent = Intent(this@Module3Activity, MenuActivity::class.java)
                var bundle = Bundle()
                bundle.putSerializable("user", user)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        val btnNivel5 = findViewById<Button>(R.id.btnLevel5)
        val btnNivel6 = findViewById<Button>(R.id.btnLevel6)

        btnNivel5.setOnClickListener {
            val intent = Intent(this, Nivel5Activity::class.java)
            var bundle = Bundle()
            bundle.putSerializable("user", user)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        btnNivel6.setOnClickListener {
            val intent = Intent(this, Nivel6Activity::class.java)
            var bundle = Bundle()
            bundle.putSerializable("user", user)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}