package com.yizhenwind.rocket.feature.client.ui.composite.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.customview.widget.Openable
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.yizhenwind.rocket.core.common.model.Client
import com.yizhenwind.rocket.core.framework.widget.BaseBottomSheetDialogFragment
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
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.apply {
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
        binding.root.apply {
            setupWithNavController(findNavController())
            setNavigationItemSelectedListener { item ->
                var handled = item.onNavDestinationSelected(findNavController())
                if (!handled) {
                    when (item.itemId) {
                        R.id.action_delete -> {
                            builder.onDeleteClientSelectedListener?.invoke(builder.client)
                            handled = true
                        }
                    }
                }
                if (handled) {
                    val parent = parent
                    if (parent is Openable) {
                        parent.close()
                    } else {
                        val bottomSheetBehavior = NavigationUI.findBottomSheetBehavior(this)
                        if (bottomSheetBehavior != null) {
                            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                        }
                    }
                }
                handled
            }
        }
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

        var onDeleteClientSelectedListener: ((Client) -> Unit)? = null
            private set

        fun setOnDeleteClientSelectedListener(onDeleteClientSelectedListener: ((Client) -> Unit)) =
            apply { this.onDeleteClientSelectedListener = onDeleteClientSelectedListener }

        fun build(): ClientCompositeBottomSheetDialog = ClientCompositeBottomSheetDialog(this)

    }

    companion object {

        private const val TAG = "ClientCompositeBottomSheetDialog"

    }

}