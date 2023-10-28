package com.example.einkausliste
// EditTextBlinker.kt

import android.graphics.Color
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.EditText

class TextBlinker(private val editText: EditText) {
    private var isBlinking = false

    fun startBlinkAnimation() {
        if (!isBlinking) {
            val blinkAnimation = AlphaAnimation(0.1f, 1.0f)
            blinkAnimation.duration = 2000
            blinkAnimation.startOffset = 20
            blinkAnimation.repeatCount = Animation.INFINITE

            editText.startAnimation(blinkAnimation)

            val handler = Handler()
            val colors = intArrayOf(Color.TRANSPARENT, Color.TRANSPARENT)
            var colorIndex = 0

            handler.post(object : Runnable {
                override fun run() {
                    if (isBlinking) {
                        editText.setBackgroundColor(colors[colorIndex])
                        colorIndex = (colorIndex + 1) % colors.size
                        handler.postDelayed(this, 500)
                    } else {
                        editText.setBackgroundColor(Color.TRANSPARENT)
                    }
                }
            })

            isBlinking = true
        }
    }

    fun stopBlinkAnimation() {
        isBlinking = false
        editText.clearAnimation()
        editText.setBackgroundColor(Color.TRANSPARENT)
    }
}
