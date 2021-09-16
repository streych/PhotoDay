package com.example.photoday.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.photoday.R
import com.example.photoday.databinding.MainActivityBinding
import com.example.photoday.view.picture.PODFragment




class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val i = intent
        val p: Int = i.getIntExtra("SENDER_KEY", 0)
        setTheme(chageStyle(p))
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, PODFragment.newInstance())
                .commitNow()

        }
    }

    private fun chageStyle(numTheme: Int): Int {
        return when(numTheme) {
            1 -> R.style.Theme_PhotoDay
            2 -> R.style.Theme_PhotoDaySuperStar
            else -> 0
        }
    }
}