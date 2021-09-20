package com.example.photoday.api

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

private const val EARTH = 0
private const val MARS = 1
private const val SYSTEM = 2

private const val EARTHPT: String = "Earth"
private const val MARSSPT: String = "Mars"
private const val SYSTEMPT: String = "System"

private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f


class ViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), SystemFragment())

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> fragments[EARTH]
        1 -> fragments[MARS]
        2 -> fragments[SYSTEM]
        else -> fragments[EARTH]
    }

    override fun getPageTitle(position: Int): String = when (position) {
        0 -> EARTHPT
        1 -> MARSSPT
        2 -> SYSTEMPT
        else -> EARTHPT
    }

}