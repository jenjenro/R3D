package com.test.r3d

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.test.r3d.ui.theme.R3DTheme
import android.view.Window
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val btnSMessage : Button = findViewById(R.id.btnNextScreen)//.boton>equivocado
        btnSMessage.setOnClickListener {
            val message : String? = "No te rindas!"
            showCustomDialogBox(message)
        }


        val btnPrueba = findViewById<Button>(R.id.btnNextScreen)
        btnPrueba.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
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