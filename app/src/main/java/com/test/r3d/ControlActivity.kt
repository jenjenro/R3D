package com.test.r3d

import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

abstract class ControlActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.control)


        /*
        val btnON = findViewById<Button>(R.id.btnON)
        val btnOFF = findViewById<Button>(R.id.btnDevice)

        val btnUp = findViewById<Button>(R.id.btnUP)
        val btnDown = findViewById<Button>(R.id.btnDOWN)
        val btnLeft = findViewById<Button>(R.id.btnLEFT)
        val btnRight = findViewById<Button>(R.id.btnRIGHT)


        btnUp.setOnClickListener(this)
        btnDown.setOnClickListener(this)
        btnRight.setOnClickListener(this)
        btnLeft.setOnClickListener(this)


        fun updateStatusView(flag: Int) {
            if (flag == 1) {
                status.text = "ON"
            } else if (flag == 0) {
                status.text = "OFF"
            }
        }


        fun onClick(v: View) {
            when (v.id) {
                R.id.btnON -> {
                    val handlerON = android.os.Handler(Looper.getMainLooper())
                    Thread {
                        try {
                            val url = URL("http://192.168.1.7/on")
                            val urlConnection = url.openConnection() as HttpURLConnection
                            val inputStream = BufferedInputStream(urlConnection.inputStream)
                            val reader = BufferedReader(InputStreamReader(inputStream))
                            val data = reader.readLine()
                            val objON = JSONObject(data)
                            handlerON.post {
                                try {
                                    updateStatusView(objON.getInt("status"))
                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                }
                            }
                        } catch (e: MalformedURLException) {
                            e.printStackTrace()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }.start()
                }

                R.id.btnDevice -> {
                    val handlerOFF = android.os.Handler(Looper.getMainLooper())
                    Thread {
                        try {
                            val url = URL("http://192.168.1.7/off")
                            val urlConnection = url.openConnection() as HttpURLConnection
                            val inputStream = BufferedInputStream(urlConnection.inputStream)
                            val reader = BufferedReader(InputStreamReader(inputStream))
                            val data = reader.readLine()
                            val objOFF = JSONObject(data)
                            handlerOFF.post {
                                try {
                                    updateStatusView(objOFF.getInt("status"))
                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                }
                            }
                        } catch (e: MalformedURLException) {
                            e.printStackTrace()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }.start()
                }
            }
        }

    }
    */


    }
}





