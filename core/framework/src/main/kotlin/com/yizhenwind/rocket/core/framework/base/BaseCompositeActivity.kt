package com.yizhenwind.rocket.core.framework.base

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
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

    private var tabScrimsAreShown = false
    private val tabScrimAnimator by lazy {
        ValueAnimator().apply {
            interpolator = FastOutLinearInInterpolator()
        }
    }

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
            collapsingToolbar.isTitleEnabled = false
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

    protected open fun onActionClicked(currentIndex: Int) {

    }

    private fun setupWithCollapsing() {
        binding.apply {
            tab.background.alpha = DEFAULT_SCRIM_ALPHA
            appBar.addOnOffsetChangedListener { _, verticalOffset ->
                val shown =
                    collapsingToolbar.height + verticalOffset < collapsingToolbar.scrimVisibleHeightTrigger
                if (tabScrimsAreShown != shown) {
                    animateTabScrim(
                        if (shown) 0xFF else DEFAULT_SCRIM_ALPHA,
                        collapsingToolbar.scrimAnimationDuration
                    )
                    tabScrimsAreShown = shown
                }
            }
        }
    }

    private fun animateTabScrim(targetAlpha: Int, duration: Long) {
        tabScrimAnimator.addUpdateListener { animator ->
            setTabScrimAlpha(animator.animatedValue as Int)
        }

        if (tabScrimAnimator.isRunning) {
            tabScrimAnimator.cancel()
        }

        tabScrimAnimator.duration = duration
        tabScrimAnimator.setIntValues(binding.tab.background.alpha, targetAlpha)
        tabScrimAnimator.start()
    }

    private fun setTabScrimAlpha(alpha: Int) {
        binding.tab.apply {
            if (alpha != background.alpha) {
                background.alpha = alpha
                ViewCompat.postInvalidateOnAnimation(this)
            }
        }
    }

    companion object {

        // 50%透明度
        private const val DEFAULT_SCRIM_ALPHA = 0x80

    }
}