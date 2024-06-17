package com.test.r3d

import android.bluetooth.BluetoothSocket
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
class BluetoothConection(private val socket: BluetoothSocket) : Thread() {
    private val inputStream: InputStream = socket.inputStream
    private val outputStream: OutputStream = socket.outputStream
    fun startListening(onMessageReceived: (String) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val buffer = ByteArray(1024)
            var bytes: Int

            // Mantén escuchando el InputStream hasta que ocurra una excepción
            while (true) {
                try {
                    // Lee del InputStream
                    bytes = inputStream.read(buffer)
                    val readMessage = String(buffer, 0, bytes)
                    Log.d("BluetoothConnection", "Message received: $readMessage")

                    // Ejecuta el callback en el hilo principal
                    withContext(Dispatchers.Main) {
                        onMessageReceived(readMessage)
                    }

                } catch (e: IOException) {
                    Log.e("BluetoothConnection", "Error reading from input stream", e)
                    break
                }
            }
        }
    }


    fun write(message: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                outputStream.write(message.toByteArray())
            } catch (e: IOException) {
                Log.e("Bluetooth", "Error writing output stream", e)
            }
        }
    }

    fun cancel(){
        try {
            socket.close()
        } catch (e: IOException) {
            Log.e("Bluetooth", "Error closing socket", e)
        }
    }
}