package com.yizhenwind.rocket.feature.client.ui.composite.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yizhenwind.rocket.core.framework.widget.BaseBottomSheetDialogFragment
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.databinding.DialogClientCompositeBottomSheetBinding
import com.yizhenwind.rocket.feature.client.databinding.NavHeaderClientCompositeBottomSheetBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/3
 */
class ClientCompositeBottomSheetDialog private constructor(
    private val builder: Builder
) : BaseBottomSheetDialogFragment() {

    private var _binding: DialogClientCompositeBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var _headerBinding: NavHeaderClientCompositeBottomSheetBinding? = null
    private val headerBinding get() = _headerBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateView(inflater, container, savedInstanceState).apply {
            inflate(Position.CONTENT, R.layout.dialog_client_composite_bottom_sheet) {
                _binding = DialogClientCompositeBottomSheetBinding.bind(it)
                _headerBinding =
                    NavHeaderClientCompositeBottomSheetBinding.bind(binding.root.getHeaderView(0))
                initMenu()
            }
        }
    }

    private fun initMenu() {
        headerBinding.tvClientCompositeBottomSheetTitle.text = builder.client.name
        binding.root.setupWithNavController(findNavController())
    }

    fun show(manager: FragmentManager) {
        super.show(manager, TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _headerBinding = null
        _binding = null
    }

    data class Builder(val client: Client) {

        fun build(): ClientCompositeBottomSheetDialog = ClientCompositeBottomSheetDialog(this)

    }

    companion object {

        private const val TAG = "ClientCompositeBottomSheetDialog"

    }

}