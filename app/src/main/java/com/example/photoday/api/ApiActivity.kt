package com.example.photoday.api

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.photoday.R

import com.example.photoday.databinding.ApiActivityBinding
import com.google.android.material.tabs.TabLayoutMediator

private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f

class ApiActivity : AppCompatActivity() {

    private lateinit var binding: ApiActivityBinding

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ApiActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(this)
        //binding.viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer())
        //binding.tabLayout.setupWithViewPager(binding.viewPager)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "OBJECT ${position * 1}"
        }
        //customTabs()

    }

    private fun customTabs() {
        binding.tabLayout.apply {
            getTabAt(0)?.customView =
                layoutInflater.inflate(R.layout.activity_api_tablayout_earth, null)
            getTabAt(1)?.customView =
                layoutInflater.inflate(R.layout.activity_api_tablayout_mars, null)
            getTabAt(2)?.customView =
                layoutInflater.inflate(R.layout.activity_api_tablayout_system, null)

        }
    }


}

class ZoomOutPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 1 -> {
                    val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}
