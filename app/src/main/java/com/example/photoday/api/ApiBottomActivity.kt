package com.example.photoday.api

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.photoday.R
import com.example.photoday.databinding.ApiActivityBottomBinding


class ApiBottomActivity : AppCompatActivity() {

    private lateinit var binding: ApiActivityBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        binding = ApiActivityBottomBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottom_api, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_earth -> {

            }
            R.id.action_mars -> {

            }
            R.id.action_system -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }


}