package com.yizhenwind.rocket.core.framework.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yizhenwind.rocket.core.framework.base.BaseFragmentStateAdapter

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
fun ViewPager2.setupWithFragmentList(activity: FragmentActivity, fragments: List<Fragment>) {
    adapter = BaseFragmentStateAdapter(activity, fragments)
    (getChildAt(0) as RecyclerView).apply {
        overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        isNestedScrollingEnabled = false
    }
}

fun ViewPager2.setupWithFragmentList(fragment: Fragment, fragments: List<Fragment>) {
    adapter = BaseFragmentStateAdapter(fragment, fragments)
    (getChildAt(0) as RecyclerView).apply {
        overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        isNestedScrollingEnabled = false
    }
}

fun ViewPager2.setupFragmentWithTab(
    activity: FragmentActivity,
    tabLayout: TabLayout,
    titles: List<Int>,
    fragments: List<Fragment>
) {
    setupWithFragmentList(activity, fragments)
    TabLayoutMediator(
        tabLayout,
        this
    ) { tab, position ->
        tab.setText(titles[position])
    }.attach()
}

fun ViewPager2.setupFragmentWithTab(
    fragment: Fragment,
    tabLayout: TabLayout,
    titles: List<Int>,
    fragments: List<Fragment>
) {
    setupWithFragmentList(fragment, fragments)
    TabLayoutMediator(
        tabLayout,
        this
    ) { tab, position ->
        tab.setText(titles[position])
    }.attach()
}