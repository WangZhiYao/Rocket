package com.yizhenwind.rocket.core.framework.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import com.yizhenwind.rocket.core.framework.R
import com.yizhenwind.rocket.core.framework.databinding.LayoutTwoLineTextViewBinding
import com.yizhenwind.rocket.core.framework.ext.inflate
import com.yizhenwind.rocket.core.infra.ext.sp

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/30
 */
class TwoLineTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding =
        LayoutTwoLineTextViewBinding.bind(inflate(R.layout.layout_two_line_text_view, true))

    var title: String?
        get() = binding.tvTwoLineTextViewTitle.text?.toString()
        set(value) {
            binding.tvTwoLineTextViewTitle.text = value
        }

    var titleTextSize: Float
        get() = binding.tvTwoLineTextViewTitle.textSize
        set(value) {
            binding.tvTwoLineTextViewTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
        }

    var titleTextColor: Int
        get() = binding.tvTwoLineTextViewTitle.currentTextColor
        set(value) {
            binding.tvTwoLineTextViewTitle.setTextColor(value)
        }

    var titleMaxLine: Int
        get() = binding.tvTwoLineTextViewTitle.maxLines
        set(value) {
            binding.tvTwoLineTextViewTitle.maxLines = value
        }

    var titleEllipsize: TextUtils.TruncateAt?
        get() = binding.tvTwoLineTextViewTitle.ellipsize
        set(value) {
            binding.tvTwoLineTextViewTitle.ellipsize = value
        }

    var content: String?
        get() = binding.tvTwoLineTextViewContent.text?.toString()
        set(value) {
            binding.tvTwoLineTextViewContent.text = value
        }

    var contentTextSize: Float
        get() = binding.tvTwoLineTextViewContent.textSize
        set(value) {
            binding.tvTwoLineTextViewContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
        }

    var contentTextColor: Int
        get() = binding.tvTwoLineTextViewContent.currentTextColor
        set(value) {
            binding.tvTwoLineTextViewContent.setTextColor(value)
        }

    var contentMaxLine: Int
        get() = binding.tvTwoLineTextViewContent.maxLines
        set(value) {
            binding.tvTwoLineTextViewContent.maxLines = value
        }

    var contentEllipsize: TextUtils.TruncateAt?
        get() = binding.tvTwoLineTextViewContent.ellipsize
        set(value) {
            binding.tvTwoLineTextViewContent.ellipsize = value
        }

    init {
        attrs?.apply {
            context.obtainStyledAttributes(
                this,
                R.styleable.TwoLineTextView,
                defStyleAttr,
                defStyleRes
            ).use { ta ->
                ta.apply {
                    binding.apply {
                        title = getString(R.styleable.TwoLineTextView_tltv_title)
                        titleTextSize = getDimensionPixelSize(
                            R.styleable.TwoLineTextView_tltv_titleTextSize,
                            16.sp
                        ).toFloat()
                        titleTextColor = getColor(
                            R.styleable.TwoLineTextView_tltv_titleTextColor,
                            ContextCompat.getColor(context, R.color.color_text_primary)
                        )
                        val titleTextAppearance =
                            getResourceId(
                                R.styleable.TwoLineTextView_tltv_titleTextAppearance,
                                0
                            )
                        if (titleTextAppearance != 0) {
                            setTitleTextAppearance(titleTextAppearance)
                        }
                        titleMaxLine = getInt(
                            R.styleable.TwoLineTextView_tltv_titleMaxLine,
                            Int.MAX_VALUE
                        )
                        titleEllipsize = getEllipsize(
                            getInt(
                                R.styleable.TwoLineTextView_tltv_titleEllipsize,
                                ELLIPSIZE_NONE
                            )
                        )

                        content = getString(R.styleable.TwoLineTextView_tltv_content)
                        contentTextSize = getDimensionPixelSize(
                            R.styleable.TwoLineTextView_tltv_contentTextSize,
                            14.sp
                        ).toFloat()
                        contentTextColor = getColor(
                            R.styleable.TwoLineTextView_tltv_contentTextColor,
                            ContextCompat.getColor(context, R.color.color_text_secondary)
                        )
                        val contentTextAppearance =
                            getResourceId(
                                R.styleable.TwoLineTextView_tltv_contentTextAppearance,
                                0
                            )
                        if (contentTextAppearance != 0) {
                            setContentTextAppearance(contentTextAppearance)
                        }
                        contentMaxLine = getInt(
                            R.styleable.TwoLineTextView_tltv_contentMaxLine,
                            Int.MAX_VALUE
                        )
                        contentEllipsize = getEllipsize(
                            getInt(
                                R.styleable.TwoLineTextView_tltv_contentEllipsize,
                                ELLIPSIZE_NONE
                            )
                        )
                    }
                }
            }
        }
    }

    fun setTitleTextAppearance(@StyleRes resId: Int) {
        binding.tvTwoLineTextViewTitle.setTextAppearance(resId)
    }

    fun setContentTextAppearance(@StyleRes resId: Int) {
        binding.tvTwoLineTextViewContent.setTextAppearance(resId)
    }

    private fun getEllipsize(ellipsize: Int): TextUtils.TruncateAt? {
        return when (ellipsize) {
            ELLIPSIZE_START -> TextUtils.TruncateAt.START
            ELLIPSIZE_MIDDLE -> TextUtils.TruncateAt.MIDDLE
            ELLIPSIZE_END -> TextUtils.TruncateAt.END
            ELLIPSIZE_MARQUEE -> TextUtils.TruncateAt.MARQUEE
            else -> null
        }
    }

    companion object {

        private const val ELLIPSIZE_NONE = 0

        private const val ELLIPSIZE_START = 1

        private const val ELLIPSIZE_MIDDLE = 2

        private const val ELLIPSIZE_END = 3

        private const val ELLIPSIZE_MARQUEE = 4

    }
}