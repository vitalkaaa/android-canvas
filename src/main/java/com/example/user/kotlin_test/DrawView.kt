package com.example.user.kotlin_test

import android.content.Context
import android.view.SurfaceHolder
import android.view.SurfaceView


class DrawView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val drawThread: DrawThread = DrawThread(holder, resources)

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int,
                                height: Int) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        drawThread.setRunning(true)
        drawThread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        drawThread.setRunning(false)
        while (retry) {
            try {
                drawThread.join()
                retry = false
            } catch (e: InterruptedException) {
            }

        }
    }
}

