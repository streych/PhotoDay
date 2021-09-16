package com.example.photoday.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photoday.R
import com.example.photoday.view.picture.PODFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PODFragment.newInstance())
                .commitNow()
        }
    }
}