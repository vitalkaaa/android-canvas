package com.example.user.kotlin_test

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


data class Vector3(var x: Float, var y: Float, var z: Float){
    fun print() = "x: %f   y: %.2f   z: %.2f".format(x, y, z)

    fun draw(from: Vector3, canvas: Canvas, color: Int = Color.RED) {
        val paint = Paint()
        paint.color = color
        canvas.drawLine(from.x, from.y, x-from.x, y-from.y, paint)
    }
}




