package com.example.photoday.view.picture

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.photoday.R
import com.example.photoday.view.MainActivity

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    var handler = Looper.myLooper()?.let { Handler(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashRotation()



    }

    override fun onDestroy() {
        handler?.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    private fun splashRotation() {
        handler?.postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 2000)

        val img = findViewById<ImageView>(R.id.image_view)
        img.animate().scaleXBy(20f).setInterpolator(AccelerateDecelerateInterpolator()).duration =
            3000
    }
}