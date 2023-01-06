package com.yizhenwind.rocket.feature.client.ui.composite

import androidx.activity.viewModels
import androidx.navigation.navArgs
import com.google.android.material.bottomappbar.BottomAppBar
import com.yizhenwind.rocket.core.framework.base.BaseBottomAppBarMVIActivity
import com.yizhenwind.rocket.core.framework.ext.makeSnack
import com.yizhenwind.rocket.core.framework.widget.AlertDialogFragment
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.feature.client.R
import com.yizhenwind.rocket.feature.client.ui.composite.widget.ClientCompositeBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/20
 */
@AndroidEntryPoint
class ClientCompositeActivity :
    BaseBottomAppBarMVIActivity<ClientCompositeViewState, ClientCompositeSideEffect>() {

    private val viewModel by viewModels<ClientCompositeViewModel>()
    private val args by navArgs<ClientCompositeActivityArgs>()

    override fun init() {
        initData()
        initView()
    }

    private fun initData() {
        viewModel.apply {
            observe(this@ClientCompositeActivity, sideEffect = ::handleSideEffect)
            getClientById(args.clientId)
        }
    }

    private fun initView() {
        binding.apply {
            setupToolbar(toolbar)
            bottomAppBar.apply {
                setNavigationIcon(R.drawable.ic_round_menu_white_24dp)
                inflateMenu(R.menu.menu_client_composite)
                fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                setNavigationOnClickListener {
                    ClientCompositeBottomSheetDialog.Builder(viewModel.client)
                        .build()
                        .show(supportFragmentManager)
                }
                setOnMenuItemClickListener { item ->
                    return@setOnMenuItemClickListener when (item.itemId) {
                        R.id.action_delete -> {
                            showDeleteClientDialog(viewModel.client)
                            true
                        }
                        else -> false
                    }
                }
            }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                fab.setImageResource(
                    when (destination.id) {
                        R.id.nav_client_detail -> R.drawable.ic_round_edit_white_24dp
                        else -> R.drawable.ic_round_add_white_24dp
                    }
                )
            }
        }
        setNavGraph(R.navigation.navigation_client_composite)
    }

    override fun handleSideEffect(sideEffect: ClientCompositeSideEffect) {
        when (sideEffect) {
            ClientCompositeSideEffect.DeleteClientSuccess -> finish()
            ClientCompositeSideEffect.DeleteClientFailed ->
                binding.root.makeSnack(R.string.error_client_detail_delete_failed)
                    .setAnchorView(R.id.fab)
                    .show()
        }
    }

    private fun showDeleteClientDialog(client: Client) {
        AlertDialogFragment.Builder(this)
            .setTitle(R.string.dialog_title_hint)
            .setMessage(
                getString(
                    R.string.dialog_delete_client_message,
                    client.name
                )
            )
            .setPositiveButton(R.string.dialog_action_positive) { dialog, _ ->
                dialog?.dismiss()
                viewModel.attemptDeleteClient(client)
            }
            .setNegativeButton(R.string.dialog_action_negative) { dialog, _ ->
                dialog?.dismiss()
            }
            .build()
            .show(supportFragmentManager)
    }
}