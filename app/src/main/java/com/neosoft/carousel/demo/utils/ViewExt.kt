package com.neosoft.carousel.demo.utils

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.neosoft.carousel.demo.R
import kotlin.math.abs

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}
fun ViewPager2.setCarouselEffects(){
    offscreenPageLimit = 1

    val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
    val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
    val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
    val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
        page.translationX = -pageTranslationX * position
        page.scaleY = 1 - (0.25f * abs(position))
        page.alpha = 0.5f + (1 - abs(position))
    }
    setPageTransformer(pageTransformer)
}