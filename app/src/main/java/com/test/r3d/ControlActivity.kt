package com.test.r3d

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.io.InputStream
import java.util.UUID

const val REQUEST_ENABLE_BT = 1
class ControlActivity : AppCompatActivity() {
    lateinit var mBtAdapter: BluetoothAdapter
    var mAddressDevices: String? = null
    var mNameDevices: String? = null
    private lateinit var m_bluetoothService: BluetoothConection

    private val bluetoothViewModel: BluetoothViewModel by viewModels()

    companion object{
        var m_myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        private var m_bluetoothSocket: BluetoothSocket? = null
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
        val btnDevice = findViewById<Button>(R.id.btnDevice)
        val button3 = findViewById<Button>(R.id.button3)
        val btnStop = findViewById<Button>(R.id.btnStop)

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.BLUETOOTH_CONNECT,
                android.Manifest.permission.BLUETOOTH,
                android.Manifest.permission.BLUETOOTH_ADMIN,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.BLUETOOTH_SCAN), REQUEST_ENABLE_BT)

        }
        if (m_bluetoothSocket == null) {
            Log.i("Bluetooth", "Not Connected")

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
                    }
                    m_bluetoothService = BluetoothConection(m_bluetoothSocket!!)
                    m_bluetoothService.startListening { message ->
                        bluetoothViewModel.setData(message)
                        Log.i("BluetoothViewModel", bluetoothViewModel.data.value.toString())
                        Log.i("Bluetooth", message)
                        btnDevice.text = message
                    }
                } catch (e: IOException) {
                    Log.d("Bluetooth", "Error")
                }
            }.start()
        }





        /*
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
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.BLUETOOTH_CONNECT,
                        android.Manifest.permission.BLUETOOTH,
                        android.Manifest.permission.BLUETOOTH_ADMIN,
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.BLUETOOTH_SCAN), REQUEST_ENABLE_BT)

                }
                someActivityResultLauncher.launch(enableBtIntent)
            }
            if (mBtAdapter.isEnabled){

                val pairedDevices: Set<BluetoothDevice>? = mBtAdapter?.bondedDevices

                pairedDevices?.filter {device ->
                    device.name == "ESP32_CONTROl"
                }?.forEach { device ->
                    mNameDevices = device.name
                    mAddressDevices = device.address

                }
                btnDevice.text = mNameDevices
            }  else {
                val noDevices = "No Devices Found"
                mAddressDevices = noDevices
                mNameDevices = noDevices
                Toast.makeText(this, "Bluetooth is not on", Toast.LENGTH_SHORT).show()
            }

        }
        btnDevice.setOnClickListener {
            try {

                if (m_bluetoothSocket == null || !m_isConnected) {
                    m_address = mAddressDevices?.toString().toString()
                    mBtAdapter.cancelDiscovery()
                    val mmDevice: BluetoothDevice = mBtAdapter.getRemoteDevice(m_address)
                    m_bluetoothSocket = mmDevice.createInsecureRfcommSocketToServiceRecord(m_myUUID)
                    println(m_bluetoothSocket)
                    m_bluetoothSocket!!.connect()
                    println(m_bluetoothSocket)
                    if (m_bluetoothSocket != null) {
                        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show()
                Log.i("Data", "Connection Failed")
            }

        }
        */
        btnUp.setOnClickListener {
            m_bluetoothService.write("a")
        }
        btnDown.setOnClickListener {
            m_bluetoothService.write("e")
        }
        btnLeft.setOnClickListener {
            m_bluetoothService.write("d")
        }
        btnRight.setOnClickListener {
            m_bluetoothService.write("b")
        }
        btnStop.setOnClickListener {
            m_bluetoothService.write("c")
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