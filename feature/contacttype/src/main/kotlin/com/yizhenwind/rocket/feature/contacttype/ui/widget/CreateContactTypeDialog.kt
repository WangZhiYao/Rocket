package com.yizhenwind.rocket.feature.contacttype.ui.widget

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.feature.contacttype.R
import com.yizhenwind.rocket.feature.contacttype.databinding.DialogContentAddContactTypeBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
class CreateContactTypeDialog private constructor(private val builder: Builder) : DialogFragment() {

    private var _binding: DialogContentAddContactTypeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogContentAddContactTypeBinding.inflate(layoutInflater)
        binding.tietAddContactType.apply {
            requestFocus()
            doAfterTextChanged { name ->
                builder.onNameChangeListener?.invoke(name?.toString())
            }
        }
        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setPositiveButton(R.string.dialog_positive_button_text, null)
            .create().apply {
                setOnShowListener {
                    getButton(AlertDialog.BUTTON_POSITIVE).setThrottleClickListener {
                        builder.onPositiveClickListener?.invoke(binding.tietAddContactType.text?.toString())
                    }
                }
            }
    }

    fun setError(@StringRes resId: Int) {
        binding.tilAddContactType.error = getString(resId)
    }

    fun hideError() {
        binding.tilAddContactType.error = null
    }

    fun show() {
        super.show(builder.fm, TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    data class Builder(val fm: FragmentManager) {

        var onNameChangeListener: ((name: String?) -> Unit)? = null
            private set
        var onPositiveClickListener: ((name: String?) -> Unit)? = null
            private set

        fun setOnNameChangeListener(onNameChangeListener: ((name: String?) -> Unit)) =
            apply { this.onNameChangeListener = onNameChangeListener }

        fun setOnPositiveClickListener(onPositiveClickListener: ((name: String?) -> Unit)) =
            apply { this.onPositiveClickListener = onPositiveClickListener }

        fun build(): CreateContactTypeDialog = CreateContactTypeDialog(this)

    }

    companion object {

        private const val TAG = "AddContactTypeDialog"

    }

}