package com.yizhenwind.rocket.core.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.yizhenwind.rocket.core.logger.impl.timber.TimberLogger

/**
 * Fragment 基类
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
abstract class BaseFragment<out VB : ViewBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    private var isInitialized = false

    val logger = TimberLogger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.d("%s: %s", this::class.java.simpleName, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        logger.d("%s: %s", this::class.java.simpleName, "onCreateView")
        _binding = this.inflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logger.d("%s: %s", this::class.java.simpleName, "onViewCreated")
        init()
    }

    abstract fun init()

    protected open fun initData() {

    }

    protected open fun initView() {

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        logger.d(
            "%s: %s, savedInstanceState: %s",
            this::class.java.simpleName,
            "onViewStateRestored",
            savedInstanceState?.toString()
        )
    }

    override fun onStart() {
        super.onStart()
        logger.d("%s: %s", this::class.java.simpleName, "onStart")
    }

    override fun onResume() {
        super.onResume()
        logger.d("%s: %s", this::class.java.simpleName, "onResume")
    }

    override fun onPause() {
        super.onPause()
        logger.d("%s: %s", this::class.java.simpleName, "onPause")
    }

    override fun onStop() {
        super.onStop()
        logger.d("%s: %s", this::class.java.simpleName, "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logger.d(
            "%s: %s, outState: %s",
            this::class.java.simpleName,
            "onSaveInstanceState",
            outState.toString()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logger.d("%s: %s", this::class.java.simpleName, "onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.d("%s: %s", this::class.java.simpleName, "onDestroy")
    }
}