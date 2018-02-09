package com.example.user.kotlin_test

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Log
import android.util.TypedValue
import kotlin.math.round



class Ball(var pos: Vector3, var v: Vector3, var a: Vector3, val resources: Resources) {
    val picture: Bitmap = BitmapFactory.decodeResource(resources, android.R.drawable.star_on)
    val matrix: Matrix = Matrix()
    private val dt: Float = PERIOD/ 10000f  // s
    private var t_pos = pos.copy()


    init {
        matrix.postScale(3.0f, 3.0f)
        matrix.postTranslate(pos.x, pos.y)
        pos.x = pxToM(pos.x)
        pos.y = pxToM(pos.y)
        t_pos.x = pxToM(t_pos.x)
        t_pos.y = pxToM(t_pos.y)
    }

    fun mToPx(m: Float): Float = m*TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, 1f, resources.displayMetrics)*1000f
    fun pxToM(px: Float): Float = px/TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, 1f, resources.displayMetrics)/1000f

    fun draw() {
        move()
        Log.d("position", pos.print())
        Log.d("velocity", v.print())
        Log.d("acceleration", a.print())
        matrix.postTranslate(mToPx(pos.x-t_pos.x), mToPx(pos.y-t_pos.y))

    }

    private fun move() {
        t_pos = pos.copy()
        a.x = round(a.x*10)/10
        a.y = round(a.y*10)/10
        a.z = round(a.z*10)/10

        v.x -= a.x*dt
        v.y += a.y*dt

        if(mToPx(pos.x)<0){
            pos.x=pxToM(0f)
            v.x = 0f
        }
        if(mToPx(pos.x)>800){
            pos.x=pxToM(800f)
            v.x = 0f
        }
        if(mToPx(pos.y)<0){
            pos.y=pxToM(0f)
            v.y = 0f
        }
        if(mToPx(pos.y)>1000){
            pos.y=pxToM(1000f)
            v.y = 0f
        }

        pos.x += v.x*dt
        pos.y += v.y*dt
    }
}