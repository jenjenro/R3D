package com.test.r3d

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.UUID

const val REQUEST_ENABLE_BT = 1
class ControlActivity : AppCompatActivity() {
    lateinit var mBtAdapter: BluetoothAdapter
    var mAddress: String? = null
    var mName: String? = null

    companion object{
        var m_myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        private var m_bluetoothSocket: BluetoothAdapter? = null
        var m_isConnected: Boolean = false
        lateinit var m_address: String
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.control)
        val btnUp = findViewById<Button>(R.id.btnUp)
        val btnDown = findViewById<Button>(R.id.btnDown)
        val btnLeft = findViewById<Button>(R.id.btnLeft)
        val btnRight = findViewById<Button>(R.id.btnRight)
        val btnOn = findViewById<Button>(R.id.btnOn)

        val someActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == REQUEST_ENABLE_BT) {
                Log.i("MainActivity",("Bluetooth has been enabled"))
            }
        }

        mBtAdapter = (getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter

        if (mBtAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show()
        } else{
            Toast.makeText(this, "Bluetooth is available", Toast.LENGTH_LONG).show()
        }

        btnOn.setOnClickListener {
            if (mBtAdapter.isEnabled) {
                Toast.makeText(this, "Bluetooth is already on", Toast.LENGTH_SHORT).show()
            } else {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.BLUETOOTH_CONNECT), REQUEST_ENABLE_BT)
                }
                someActivityResultLauncher.launch(enableBtIntent)
            }
        }
    }
}