package com.yizhenwind.rocket.feature.character.ui.create

import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.model.*
import com.yizhenwind.rocket.feature.character.R
import com.yizhenwind.rocket.feature.character.databinding.FragmentCreateCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
@AndroidEntryPoint
class CreateCharacterFragment :
    BaseFragment<FragmentCreateCharacterBinding>(FragmentCreateCharacterBinding::inflate),
    IMVIHost<CreateCharacterViewState, CreateCharacterSideEffect> {

    private val viewModel by viewModels<CreateCharacterViewModel>()
    private val args by navArgs<CreateCharacterFragmentArgs>()

    private val clientAdapter: ArrayAdapter<Client> by lazy {
        ArrayAdapter<Client>(requireContext(), android.R.layout.simple_dropdown_item_1line)
    }

    private val zoneAdapter: ArrayAdapter<Zone> by lazy {
        ArrayAdapter<Zone>(requireContext(), android.R.layout.simple_dropdown_item_1line)
    }

    private val serverAdapter: ArrayAdapter<Server> by lazy {
        ArrayAdapter<Server>(requireContext(), android.R.layout.simple_dropdown_item_1line)
    }

    private val accountAdapter: ArrayAdapter<Account> by lazy {
        ArrayAdapter<Account>(requireContext(), android.R.layout.simple_dropdown_item_1line)
    }

    private val sectAdapter: ArrayAdapter<Sect> by lazy {
        ArrayAdapter<Sect>(requireContext(), android.R.layout.simple_dropdown_item_1line)
    }

    private val internalAdapter: ArrayAdapter<Internal> by lazy {
        ArrayAdapter<Internal>(requireContext(), android.R.layout.simple_dropdown_item_1line)
    }

    override fun init() {
        initData()
        initView()
    }

    override fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
            if (args.clientId == Constant.DEFAULT_ID) {
                observeClientList()
            } else {
                observeAccountListByClientId(args.clientId, args.accountId)
            }
        }
    }

    override fun initView() {
        binding.apply {
            tilCreateCharacterClient.isVisible = args.clientId == Constant.DEFAULT_ID
            actvCreateCharacterClient.apply {
                setAdapter(clientAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onClientSelected(position)
                }
            }
            actvCreateCharacterZone.apply {
                setAdapter(zoneAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onZoneSelected(position)
                }
            }
            actvCreateCharacterServer.apply {
                setAdapter(serverAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onServerSelected(position)
                }
            }
            tilCreateCharacterAccount.isVisible = args.accountId == Constant.DEFAULT_ID
            actvCreateCharacterAccount.apply {
                setAdapter(accountAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onAccountSelected(position)
                }
            }
            actvCreateCharacterSect.apply {
                setAdapter(sectAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onSectSelected(position)
                }
            }
            actvCreateCharacterInternal.apply {
                setAdapter(internalAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onInternalSelected(position)
                }
            }
            fab.setThrottleClickListener { attemptCreateCharacter() }
        }
    }

    override suspend fun render(state: CreateCharacterViewState) {
        binding.apply {
            state.apply {
                clientAdapter.apply {
                    clear()
                    addAll(clientList)
                }
                zoneAdapter.apply {
                    clear()
                    addAll(zoneList)
                }
                actvCreateCharacterServer.setText(server.name, false)
                serverAdapter.apply {
                    clear()
                    addAll(serverList)
                }
                actvCreateCharacterAccount.setText(account.username, false)
                accountAdapter.apply {
                    clear()
                    addAll(accountList)
                }
                actvCreateCharacterSect.setText(sect.name, false)
                sectAdapter.apply {
                    clear()
                    addAll(sectList)
                }
                actvCreateCharacterInternal.setText(internal.name, false)
                internalAdapter.apply {
                    clear()
                    addAll(internalList)
                }
            }
        }
    }

    override fun handleSideEffect(sideEffect: CreateCharacterSideEffect) {
        binding.apply {
            when (sideEffect) {
                is CreateCharacterSideEffect.ShowClientError ->
                    tilCreateCharacterClient.error = getString(sideEffect.resId)
                CreateCharacterSideEffect.HideClientError ->
                    tilCreateCharacterClient.error = null
                is CreateCharacterSideEffect.ShowZoneError ->
                    tilCreateCharacterZone.error = getString(sideEffect.resId)
                CreateCharacterSideEffect.HideZoneError ->
                    tilCreateCharacterZone.error = null
                is CreateCharacterSideEffect.ShowServerError ->
                    tilCreateCharacterServer.error = getString(sideEffect.resId)
                CreateCharacterSideEffect.HideServerError ->
                    tilCreateCharacterServer.error = null
                is CreateCharacterSideEffect.ShowAccountError ->
                    tilCreateCharacterAccount.error = getString(sideEffect.resId)
                CreateCharacterSideEffect.HideAccountError ->
                    tilCreateCharacterAccount.error = null
                is CreateCharacterSideEffect.ShowNameError ->
                    tilCreateCharacterName.error = getString(sideEffect.resId)
                CreateCharacterSideEffect.HideNameError ->
                    tilCreateCharacterName.error = null
                is CreateCharacterSideEffect.ShowSectError ->
                    tilCreateCharacterSect.error = getString(sideEffect.resId)
                CreateCharacterSideEffect.HideSectError ->
                    tilCreateCharacterSect.error = null
                is CreateCharacterSideEffect.ShowInternalError ->
                    tilCreateCharacterInternal.error = getString(sideEffect.resId)
                CreateCharacterSideEffect.HideInternalError ->
                    tilCreateCharacterInternal.error = null
                is CreateCharacterSideEffect.ShowSnack ->
                    root.showSnack(sideEffect.resId)
                is CreateCharacterSideEffect.CreateCharacterSuccess -> {
                    root.showSnack(
                        resId = R.string.create_character_success,
                        Snackbar.LENGTH_INDEFINITE,
                        actionResId = R.string.create_character_success_to_composite
                    ) {
                        // TODO: to character composite
                    }
                }
            }
        }
    }

    private fun attemptCreateCharacter() {
        binding.apply {
            val securityLock = tietCreateCharacterSecurityLock.text?.toString()
            val characterName = tietCreateCharacterName.text?.toString()
            val remark = tietCreateCharacterRemark.text?.toString()
            viewModel.attemptCreateCharacter(securityLock, characterName, remark)
        }
    }

}