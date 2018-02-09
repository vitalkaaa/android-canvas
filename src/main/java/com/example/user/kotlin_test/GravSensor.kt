package com.example.user.kotlin_test

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

var grav = Vector3(0f, 0f, 0f)

class GravSensor(context: Context) {
    private var sensorManager: SensorManager = context.getSystemService(Activity.SENSOR_SERVICE) as SensorManager
    private var sensorGravity: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)



    private var listener: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

        override fun onSensorChanged(event: SensorEvent) {
            grav.x = event.values[0]
            grav.y = event.values[1]
            grav.z = event.values[2]
        }
    }

    fun pause(){
        sensorManager.unregisterListener(listener)
    }

    fun resume(){
        sensorManager.registerListener(listener, sensorGravity, SensorManager.SENSOR_DELAY_NORMAL)

    }
}