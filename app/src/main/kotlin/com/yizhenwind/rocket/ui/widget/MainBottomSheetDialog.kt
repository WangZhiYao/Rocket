package com.yizhenwind.rocket.ui.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yizhenwind.rocket.R
import com.yizhenwind.rocket.core.framework.widget.BaseBottomSheetDialogFragment
import com.yizhenwind.rocket.databinding.DialogMainBottomSheetBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/17
 */
class MainBottomSheetDialog : BaseBottomSheetDialogFragment() {

    private var _binding: DialogMainBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateView(inflater, container, savedInstanceState).apply {
            inflate(Position.CONTENT, R.layout.dialog_main_bottom_sheet) {
                _binding = DialogMainBottomSheetBinding.bind(it)
                initMenu()
            }
        }
    }

    private fun initMenu() {
        binding.root.setupWithNavController(findNavController())
    }

    fun show(manager: FragmentManager) {
        super.show(manager, TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val TAG = "MainBottomSheetDialog"

    }
}
