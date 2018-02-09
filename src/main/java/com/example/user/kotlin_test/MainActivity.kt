package com.example.user.kotlin_test

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import java.util.*


class MainActivity : Activity() {
    private val timer = Timer()
    lateinit var gs: GravSensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DrawView(this))

        gs = GravSensor(this)
    }

    override fun onResume() {
        super.onResume()
        gs.resume()

        val task = object : TimerTask() {
            @SuppressLint("SetTextI18n")
            override fun run() {
                runOnUiThread {
                }
            }
        }
        timer.schedule(task, 0, 100)
    }


    override fun onPause() {
        super.onPause()
        gs.pause()
        timer.cancel()
    }


}