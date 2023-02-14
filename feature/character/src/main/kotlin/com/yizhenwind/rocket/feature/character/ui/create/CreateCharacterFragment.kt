package com.yizhenwind.rocket.feature.character.ui.create

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.framework.widget.*
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

    private val clientAdapter = SimpleClientDropDownAdapter()

    private val zoneAdapter = ZoneDropDownAdapter()

    private val serverAdapter = ServerDropDownAdapter()

    private val accountAdapter = SimpleAccountDropDownAdapter()

    private val sectAdapter = SectDropDownAdapter()

    private val internalAdapter = InternalDropDownAdapter()

    override fun init() {
        initData()
        initView()
    }

    override fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
            observeClientAccountList(args.clientId, args.accountId)
        }
    }

    override fun initView() {
        binding.apply {
            actvCreateCharacterClient.apply {
                setAdapter(clientAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    clientAdapter.getItem(position)
                        .let { simpleClient -> viewModel.onClientSelected(simpleClient) }
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
            actvCreateCharacterAccount.apply {
                setAdapter(accountAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    accountAdapter.getItem(position)
                        .let { simpleAccount -> viewModel.onAccountSelected(simpleAccount) }
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
                actvCreateCharacterClient.setText(simpleClient.name, false)
                clientAdapter.submitList(simpleClientList)
                zoneAdapter.submitList(zoneList)
                actvCreateCharacterServer.setText(server.name, false)
                serverAdapter.submitList(serverList)
                actvCreateCharacterAccount.setText(simpleAccount.username, false)
                accountAdapter.submitList(simpleAccountList)
                actvCreateCharacterSect.setText(sect.name, false)
                sectAdapter.submitList(sectList)
                actvCreateCharacterInternal.setText(internal.name, false)
                internalAdapter.submitList(internalList)
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
                        findNavController().navigate(
                            CreateCharacterFragmentDirections.actionCreateCharacterToCharacterComposite(
                                sideEffect.character.id
                            )
                        )
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