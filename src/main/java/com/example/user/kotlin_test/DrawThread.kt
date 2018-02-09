package com.example.user.kotlin_test

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder

const val PERIOD = 30

class DrawThread(private val surfaceHolder: SurfaceHolder, resources: Resources) : Thread() {
    private var runFlag = false

    private val b = Ball(
            Vector3(400.0f, 1000.0f, 0.0f),
            Vector3(0.0f, 0.0f, 0.0f),
//            Vector3(0.0f, 1.0f,0.0f),
            grav,
            resources)


    private var prevTime: Long = 0

    init {
        prevTime = System.currentTimeMillis()
    }

    fun setRunning(run: Boolean) {
        runFlag = run
    }

    override fun run() {
        var canvas: Canvas
        val pV = Paint()
        pV.color = Color.RED
        pV.strokeWidth = 10f
        val pA = Paint()
        pA.color = Color.BLUE
        pA.strokeWidth = 10f
        while (runFlag) {
            val now = System.currentTimeMillis()
            val elapsedTime = now - prevTime
            if (elapsedTime > PERIOD) {
                prevTime = now
                draw()

            }
            canvas = surfaceHolder.lockCanvas(null)


            synchronized(surfaceHolder) {

                canvas.drawColor(Color.BLACK)
                canvas.drawBitmap(b.picture, b.matrix, null)

                canvas.drawLine(
                        b.mToPx(b.pos.x) + 120,
                        b.mToPx(b.pos.y) + 120,
                        b.mToPx(b.pos.x) + b.v.x*100 + 120,
                        b.mToPx(b.pos.y) + b.v.y*100 + 120,
                        pV)

                canvas.drawLine(
                        b.mToPx(b.pos.x) + 120,
                        b.mToPx(b.pos.y) + 120,
                        b.mToPx(b.pos.x) - b.a.x*10 + 120,
                        b.mToPx(b.pos.y) + b.a.y*10 + 120,
                        pA)
            }

            surfaceHolder.unlockCanvasAndPost(canvas)

        }
    }

    private fun draw(){
        b.draw()
    }
}
