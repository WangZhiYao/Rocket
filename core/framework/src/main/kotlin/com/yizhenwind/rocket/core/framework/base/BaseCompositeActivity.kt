package com.yizhenwind.rocket.core.framework.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.yizhenwind.rocket.core.framework.databinding.ActivityBaseCompositeBinding
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.setupFragmentWithTab
import com.yizhenwind.rocket.core.framework.ext.viewBindings

/**
 *
 * @author WangZhiYao
 * @since 2023/2/1
 */
abstract class BaseCompositeActivity : BaseActivity() {

    protected val binding by viewBindings<ActivityBaseCompositeBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initData()
        initView()
    }

    protected open fun initData() {

    }

    protected open fun initView() {
        binding.apply {
            toolbar.setNavigationOnClickListener { finish() }
            viewPager.apply {
                setupFragmentWithTab(
                    this@BaseCompositeActivity,
                    tab,
                    getTitles(),
                    getFragments()
                )
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                       this@BaseCompositeActivity.onPageSelected(position)
                    }
                })
                fab.setThrottleClickListener { onActionClicked(currentItem) }
            }
        }
    }

    abstract fun getTitles(): List<Int>

    abstract fun getFragments(): List<Fragment>

    protected open fun onPageSelected(position: Int) {

    }

    protected open fun onActionClicked(currentIndex:Int) {

    }
}