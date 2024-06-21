package com.test.r3d

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.io.InputStream
import java.util.UUID

const val REQUEST_ENABLE_BT = 1

@AndroidEntryPoint
class ControlActivity : AppCompatActivity() {
    lateinit var mBtAdapter: BluetoothAdapter
    private lateinit var m_bluetoothService: BluetoothConection

    companion object{
        var m_myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        private var m_bluetoothSocket: BluetoothSocket? = null

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.control)

        val bundle = intent.extras
        val user = bundle?.getSerializable("user") as User

        val btnUp = findViewById<Button>(R.id.btnUp)
        val btnDown = findViewById<Button>(R.id.btnDown)
        val btnLeft = findViewById<Button>(R.id.btnLeft)
        val btnRight = findViewById<Button>(R.id.btnRight)
        val btnOn = findViewById<Button>(R.id.btnOn)
        val btnDevice = findViewById<Button>(R.id.btnDevice)
        val button3 = findViewById<Button>(R.id.button3)
        val btnStop = findViewById<Button>(R.id.btnStop)
        val button21 = findViewById<Button>(R.id.button21)

        GlobalVariable.myVariable.observe(this, Observer { data ->
            button3.text = data
            Log.i("ControlActivity", data)
        })

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.BLUETOOTH_CONNECT,
                android.Manifest.permission.BLUETOOTH,
                android.Manifest.permission.BLUETOOTH_ADMIN,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.BLUETOOTH_SCAN), REQUEST_ENABLE_BT)

        }
        mBtAdapter = BluetoothAdapter.getDefaultAdapter()
        val device: BluetoothDevice = mBtAdapter.getRemoteDevice("E0:5A:1B:A7:21:26")

        btnOn.setOnClickListener {
            Thread {
                try {
                    m_bluetoothSocket = device.createRfcommSocketToServiceRecord(m_myUUID)
                    m_bluetoothSocket!!.connect()
                    if (m_bluetoothSocket!!.isConnected){
                        Log.d("Bluetooth", "Success")
                        runOnUiThread {
                            btnDevice.text = "R3D esta conectado"
                        }

                    }

                    m_bluetoothService = BluetoothConection(m_bluetoothSocket!!)

                    m_bluetoothService.startListening { message ->
                        //Se ejecuta cada vez que se recibe un mensaje

                        GlobalVariable.updateVariable(message)
                    }

                } catch (e: IOException) {
                    runOnUiThread {
                        btnDevice.text = "R3D no esta conectado"
                    }
                    Log.d("Bluetooth", "Error")
                }
            }.start()
        }
        button21.setOnClickListener {
            Log.i("BluetoothViewModel", GlobalVariable.myVariable.value.toString())
        }

        btnUp.setOnClickListener {
            try{
                if (m_bluetoothSocket != null) {
                    m_bluetoothService.write("a")
                } else {
                    Toast.makeText(this, "R3D no esta conectado", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        btnDown.setOnClickListener {
            try {
                if (m_bluetoothSocket != null) {
                    m_bluetoothService.write("e")
                } else {
                    Toast.makeText(this, "R3D no esta conectado", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        btnLeft.setOnClickListener {
            try {
                if (m_bluetoothSocket != null) {
                    m_bluetoothService.write("d")
                } else {
                    Toast.makeText(this, "R3D no esta conectado", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        btnRight.setOnClickListener {
            try {
                if (m_bluetoothSocket != null) {
                    m_bluetoothService.write("b")
                } else {
                    Toast.makeText(this, "R3D no esta conectado", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        btnStop.setOnClickListener {
            try {
                if (m_bluetoothSocket != null) {
                    m_bluetoothService.write("c")
                } else {
                    Toast.makeText(this, "R3D no esta conectado", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun sendCommand(command: String) {
        if (m_bluetoothSocket != null) {
            try {
                m_bluetoothSocket!!.outputStream.write(command.toByteArray())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    private fun readDataFromSocket() {

        val inputStream: InputStream? = m_bluetoothSocket?.inputStream
        Toast.makeText(this, inputStream.toString(), Toast.LENGTH_SHORT).show()
        val buffer = ByteArray(1024)
        var bytes: Int
        try {
            bytes = inputStream!!.read(buffer)
            val incomingMessage = String(buffer, 0, bytes)
            Toast.makeText(this, incomingMessage, Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    private fun turnOnBluetooth() {
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.BLUETOOTH_CONNECT,
                android.Manifest.permission.BLUETOOTH,
                android.Manifest.permission.BLUETOOTH_ADMIN,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.BLUETOOTH_SCAN), REQUEST_ENABLE_BT)
        }
        startActivity(enableBtIntent)
    }
}