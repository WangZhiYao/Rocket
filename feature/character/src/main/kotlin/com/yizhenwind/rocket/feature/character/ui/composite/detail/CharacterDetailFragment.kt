package com.yizhenwind.rocket.feature.character.ui.composite.detail

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.common.ext.formatDate
import com.yizhenwind.rocket.core.common.util.ClipboardHelper
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.fragmentArgs
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.mediator.account.IAccountService
import com.yizhenwind.rocket.core.mediator.client.IClientService
import com.yizhenwind.rocket.feature.character.R
import com.yizhenwind.rocket.feature.character.databinding.FragmentCharacterDetailBinding
import com.yizhenwind.rocket.feature.character.ui.composite.CharacterCompositeViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding>(FragmentCharacterDetailBinding::inflate),
    IMVIHost<CharacterDetailViewState, CharacterDetailSideEffect> {

    private val activityViewModel by activityViewModels<CharacterCompositeViewModel>()
    private val viewModel by viewModels<CharacterDetailViewModel>()
    private val args by fragmentArgs<CharacterDetailArgs>()

    @Inject
    lateinit var clientService: IClientService

    @Inject
    lateinit var accountService: IAccountService

    override fun init() {
        initData()
        initView()
    }

    override fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
            observeCharacterById(args.characterId)
        }
    }

    override fun initView() {
        binding.apply {
            viewModel.apply {
                clCharacterDetailClient.setThrottleClickListener {
                    if (clientId != Constant.DEFAULT_ID) {
                        clientService.launchClientComposite(requireContext(), clientId)
                    }
                }
                clCharacterDetailAccount.setThrottleClickListener {
                    if (clientId != Constant.DEFAULT_ID && accountId != Constant.DEFAULT_ID) {
                        accountService.launchAccountComposite(
                            requireContext(),
                            clientId,
                            accountId
                        )
                    }
                }
                clCharacterDetailZone.setThrottleClickListener {
                    if (zoneId != Constant.DEFAULT_ID) {
                        // TODO: open search by zone
                    }
                }
                clCharacterDetailServer.setThrottleClickListener {
                    if (serverId != Constant.DEFAULT_ID) {
                        // TODO: open search by server
                    }
                }
                clCharacterDetailSect.setThrottleClickListener {
                    if (sectId != Constant.DEFAULT_ID) {
                        // TODO: open search by sect
                    }
                }
                clCharacterDetailInternal.setThrottleClickListener {
                    if (internalId != Constant.DEFAULT_ID) {
                        // TODO: open search by internal
                    }
                }
                clCharacterDetailSecurityLock.setOnLongClickListener {
                    val securityLock = securityLock
                    return@setOnLongClickListener if (!securityLock.isNullOrBlank() && ClipboardHelper.copy(
                            requireContext(),
                            securityLock
                        )
                    ) {
                        root.showSnack(R.string.character_detail_copy_security_lock_to_clipboard_success)
                        true
                    } else {
                        false
                    }
                }

            }
        }
    }

    override suspend fun render(state: CharacterDetailViewState) {
        state.character.apply {
            if (id != Constant.DEFAULT_ID) {
                activityViewModel.setTitle(name)
                binding.apply {
                    tvCharacterDetailClient.text = client.name
                    tvCharacterDetailZone.text = zone.name
                    tvCharacterDetailServer.text = server.name
                    tvCharacterDetailAccount.text = account.username
                    tvCharacterDetailSect.text = sect.name
                    tvCharacterDetailInternal.text = internal.name
                    tvCharacterDetailSecurityLock.text =
                        if (securityLock.isNullOrBlank()) getString(R.string.empty_security_lock) else securityLock
                    tvCharacterDetailRemark.text =
                        if (remark.isNullOrBlank()) getString(R.string.empty_remark) else remark
                    tvCharacterDetailCreateTime.text = createTime.formatDate()
                }
            }
        }
    }

    override fun handleSideEffect(sideEffect: CharacterDetailSideEffect) {
        activityViewModel.showError(sideEffect.resId)
    }
}