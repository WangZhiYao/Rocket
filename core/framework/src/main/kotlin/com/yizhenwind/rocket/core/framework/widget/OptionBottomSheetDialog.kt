package com.yizhenwind.rocket.core.framework.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yizhenwind.rocket.core.framework.databinding.DialogBottomSheetOptionBinding
import com.yizhenwind.rocket.core.infra.ext.ifTrue

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/9
 */
class OptionBottomSheetDialog : BottomSheetDialogFragment() {

    private var _binding: DialogBottomSheetOptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogBottomSheetOptionBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.apply {
            tvOptionTitle.apply {

            }
            rvOptionList.apply {
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    fun show(manager: FragmentManager) {
        super.show(manager, TAG)
    }

    override fun onDestroyView() {
        binding.rvOptionList.adapter = null
        _binding = null
        super.onDestroyView()
    }

    companion object {

        private const val TAG = "OptionBottomSheetDialog"

    }
}