package com.test.r3d

import android.view.Window
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast

class ConexionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conexion)

        val btnSMessage : Button = findViewById(R.id.btnBlue)//.boton equivocado
        btnSMessage.setOnClickListener {
            val message : String? = "No te rindas!"
            showCustomDialogBox(message)
        }



        val btnBluethooth = findViewById<Button>(R.id.btnBlue)
        val btnMenu = findViewById<Button>(R.id.btnMenu)
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

    private fun showCustomDialogBox(message: String?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dailog_ups)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMessage: Button = dialog.findViewById(R.id.btnBlue)
        val btnSMessage : Button = dialog.findViewById(R.id.sMessage)
        tvMessage.text = message

        btnSMessage.setOnClickListener{
            Toast.makeText(this, "Click on Seguir intentanto", Toast.LENGTH_LONG).show()
        }
        dialog.show()

    }
}