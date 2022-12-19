package com.yizhenwind.rocket.core.framework.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yizhenwind.rocket.core.framework.databinding.DialogBaseBottomSheetBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/17
 */
abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogBaseBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogBaseBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    protected fun inflate(
        position: Position,
        @LayoutRes resId: Int,
        onInflateListener: ((View) -> Unit)? = null
    ) {
        when (position) {
            Position.HEADER -> binding.vsBottomSheetHeader
            Position.CONTENT -> binding.vsBottomSheetContent
            Position.FOOTER -> binding.vsBottomSheetFooter
        }.apply {
            layoutResource = resId
            setOnInflateListener { _, inflated -> onInflateListener?.invoke(inflated) }
            inflate()
        }
    }

    enum class Position {

        HEADER,

        CONTENT,

        FOOTER
    }
}