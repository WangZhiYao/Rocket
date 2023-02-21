package com.yizhenwind.rocket.core.framework.widget

import android.os.Bundle
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.yizhenwind.rocket.core.framework.databinding.DialogMessageBinding
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/19
 */
class MessageDialog private constructor(private val builder: Builder) :
    BaseBottomSheetDialogFragment<DialogMessageBinding>(DialogMessageBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        builder.apply {
            binding.apply {
                tvMessageTitle.text = title
                if (titleColor != 0) {
                    tvMessageTitle.setTextColor(titleColor)
                }
                tvMessageContent.text = content
                if (contentColor != 0) {
                    tvMessageContent.setTextColor(contentColor)
                }
                btnMessageNegative.apply {
                    isVisible = negative.isNotBlank()
                    if (negative.isNotBlank()) {
                        text = negative
                        if (negativeTextColor != 0) {
                            setTextColor(negativeTextColor)
                        }
                        setThrottleClickListener {
                            onNegativeClickListener?.invoke(this@MessageDialog)
                        }
                    }
                }
                btnMessagePositive.apply {
                    if (positive.isNotBlank()) {
                        text = positive
                    }
                    if (positiveTextColor != 0) {
                        setTextColor(positiveTextColor)
                    }
                    setThrottleClickListener {
                        onPositiveClickListener?.invoke(this@MessageDialog)
                    }
                }
            }
        }
    }

    class Builder(private val fm: FragmentManager) {

        var title: CharSequence = ""
            private set

        @ColorInt
        var titleColor: Int = 0
            private set

        var content: CharSequence = ""
            private set

        @ColorInt
        var contentColor: Int = 0
            private set

        var negative: CharSequence = ""
            private set

        @ColorInt
        var negativeTextColor: Int = 0
            private set

        var onNegativeClickListener: ((dialog: MessageDialog) -> Unit)? = null
            private set

        var positive: CharSequence = ""
            private set

        @ColorInt
        var positiveTextColor: Int = 0
            private set

        var onPositiveClickListener: ((dialog: MessageDialog) -> Unit)? = null
            private set

        fun setTitle(title: CharSequence) =
            apply { this.title = title }

        fun setTitleColor(@ColorInt titleColor: Int) =
            apply { this.titleColor = titleColor }

        fun setContent(content: CharSequence) =
            apply { this.content = content }

        fun setContentColor(@ColorInt contentColor: Int) =
            apply { this.contentColor = contentColor }

        fun setNegative(negative: CharSequence) =
            apply { this.negative = negative }

        fun setNegativeTextColor(@ColorInt negativeTextColor: Int) =
            apply { this.negativeTextColor = negativeTextColor }

        fun setOnNegativeClickListener(onNegativeClickListener: (dialog: MessageDialog) -> Unit) =
            apply { this.onNegativeClickListener = onNegativeClickListener }

        fun setPositive(positive: CharSequence) =
            apply { this.positive = positive }

        fun setPositiveTextColor(@ColorInt positiveTextColor: Int) =
            apply { this.positiveTextColor = positiveTextColor }

        fun setOnPositiveClickListener(onPositiveClickListener: (dialog: MessageDialog) -> Unit) =
            apply { this.onPositiveClickListener = onPositiveClickListener }

        fun show(): MessageDialog = MessageDialog(this).apply { show(fm, TAG) }
    }

    companion object {

        private const val TAG = "MessageDialog"

    }
}