package com.c2m.storyviewer.screen

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.viewpager.widget.ViewPager.SCROLL_STATE_DRAGGING
import androidx.viewpager.widget.ViewPager.SCROLL_STATE_SETTLING
import androidx.viewpager2.widget.ViewPager2

abstract class PageChangeListener : ViewPager2.OnPageChangeCallback() {

    private var pageBeforeDragging = 0
    private var currentPage = 0
    private var lastTime = DEBOUNCE_TIMES + 1L

    override fun onPageScrollStateChanged(state: Int) {
        when (state) {
            ViewPager2.SCROLL_STATE_IDLE -> {
                Log.d("onPageScrollState", " SCROLL_STATE_IDLE")
                val now = System.currentTimeMillis()
                if (now - lastTime < DEBOUNCE_TIMES) {
                    return
                }
                lastTime = now
                Handler(Looper.getMainLooper()).postDelayed({
                    if (pageBeforeDragging == currentPage) {
                        onPageScrollCanceled(position = currentPage)
                    }
                }, 300L)
            }
            ViewPager2.SCROLL_STATE_DRAGGING -> {
                Log.d("onPageScrollState", " SCROLL_STATE_DRAGGING")
                pageBeforeDragging = currentPage
            }
            ViewPager2.SCROLL_STATE_SETTLING -> {
                Log.d("onPageScrollState", " SCROLL_STATE_SETTLING")
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        Log.d("onPageScrollState", "onPageSelected(): position($position)")
        currentPage = position
    }

    abstract fun onPageScrollCanceled(position: Int)

    companion object {
        private const val DEBOUNCE_TIMES = 500L
    }
}
