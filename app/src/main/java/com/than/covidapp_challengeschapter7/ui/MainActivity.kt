package com.than.covidapp_challengeschapter7.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.addCallback
import com.than.covidapp_challengeschapter7.R
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var backPressed = false
        this.onBackPressedDispatcher.addCallback(this) {
            if (backPressed) {
                exitProcess(0)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Tekan sekali lagi untuk keluar!",
                    Toast.LENGTH_SHORT
                ).show()
                backPressed = true
                Handler().postDelayed({
                    backPressed = false
                }, 2000)
            }
        }
    }
}