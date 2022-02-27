package com.example.photoday.api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val EARTH = 0
private const val MARS = 1
private const val SYSTEM = 2


class ViewPagerAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), SystemFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> fragments[EARTH]
        1 -> fragments[MARS]
        2 -> fragments[SYSTEM]
        else -> fragments[EARTH]
    }

}