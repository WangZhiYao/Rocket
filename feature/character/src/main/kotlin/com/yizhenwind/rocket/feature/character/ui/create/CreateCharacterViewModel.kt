package com.yizhenwind.rocket.feature.character.ui.create

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.common.ext.ifNull
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.mediator.account.IAccountService
import com.yizhenwind.rocket.core.mediator.client.IClientService
import com.yizhenwind.rocket.core.mediator.sectinternal.ISectInternalService
import com.yizhenwind.rocket.core.mediator.zoneserver.IZoneServerService
import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.core.model.Character
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.core.model.simple.SimpleAccount
import com.yizhenwind.rocket.core.model.simple.SimpleClient
import com.yizhenwind.rocket.domain.character.CreateCharacterUseCase
import com.yizhenwind.rocket.feature.character.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
@HiltViewModel
class CreateCharacterViewModel @Inject constructor(
    private val clientService: IClientService,
    private val accountService: IAccountService,
    private val zoneServerService: IZoneServerService,
    private val sectInternalService: ISectInternalService,
    private val createCharacterUseCase: CreateCharacterUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<CreateCharacterViewState, CreateCharacterSideEffect>() {

    override val container =
        container<CreateCharacterViewState, CreateCharacterSideEffect>(CreateCharacterViewState())

    init {
        intent {
            zoneServerService.observeZoneServer()
                .combine(sectInternalService.observeSectInternal()) { zoneServerList, sectInternalList ->
                    state.copy(
                        zoneServerList = zoneServerList,
                        zoneList = zoneServerList.map { zoneServer -> zoneServer.zone },
                        sectInternalList = sectInternalList,
                        sectList = sectInternalList.map { sectInternal -> sectInternal.sect }
                    )
                }
                .collect { viewState ->
                    reduce {
                        viewState
                    }
                }
        }
    }

    fun observeClientAccountList(clientId: Long, accountId: Long) {
        intent {
            val simpleAccountListFlow =
                if (clientId == Constant.DEFAULT_ID) flowOf(emptyList()) else accountService.observeSimpleAccountListByClientId(
                    clientId
                )

            clientService.observeSimpleClientList()
                .combine(simpleAccountListFlow) { simpleClientList, simpleAccountList ->
                    val simpleClient =
                        if (clientId == Constant.DEFAULT_ID) null else simpleClientList.find { it.id == clientId }
                    val simpleAccount =
                        if (accountId == Constant.DEFAULT_ID) null else simpleAccountList.find { it.id == accountId }
                    state.copy(
                        simpleClientList = simpleClientList,
                        simpleClient = simpleClient.ifNull { SimpleClient() },
                        simpleAccountList = simpleAccountList,
                        simpleAccount = simpleAccount.ifNull { SimpleAccount() }
                    )
                }
                .collect { viewState ->
                    reduce {
                        viewState
                    }
                }
        }
    }

    fun onClientSelected(simpleClient: SimpleClient) {
        intent {
            if (simpleClient.id == state.simpleClient.id) {
                return@intent
            }
            accountService.observeSimpleAccountListByClientId(simpleClient.id)
                .collect { simpleAccountList ->
                    postSideEffect(CreateCharacterSideEffect.HideClientError)
                    reduce {
                        state.copy(
                            simpleClient = simpleClient,
                            simpleAccountList = simpleAccountList,
                            simpleAccount = SimpleAccount()
                        )
                    }
                }
        }
    }

    fun onZoneSelected(position: Int) {
        intent {
            val zone = state.zoneList[position]
            val serverList = state.zoneServerList.first { it.zone.id == zone.id }.serverList
            postSideEffect(CreateCharacterSideEffect.HideZoneError)
            reduce {
                state.copy(zone = zone, serverList = serverList, server = serverList[0])
            }
        }
    }

    fun onServerSelected(position: Int) {
        intent {
            val server = state.serverList[position]
            postSideEffect(CreateCharacterSideEffect.HideServerError)
            reduce {
                state.copy(server = server)
            }
        }
    }

    fun onAccountSelected(simpleAccount: SimpleAccount) {
        intent {
            if (simpleAccount.id == state.simpleAccount.id) {
                return@intent
            }
            postSideEffect(CreateCharacterSideEffect.HideAccountError)
            reduce {
                state.copy(simpleAccount = simpleAccount)
            }
        }
    }

    fun onSectSelected(position: Int) {
        intent {
            val sect = state.sectList[position]
            val internalList = state.sectInternalList.first { it.sect.id == sect.id }.internalList
            postSideEffect(CreateCharacterSideEffect.HideSectError)
            reduce {
                state.copy(sect = sect, internalList = internalList, internal = internalList[0])
            }
        }
    }

    fun onInternalSelected(position: Int) {
        intent {
            val internal = state.internalList[position]
            postSideEffect(CreateCharacterSideEffect.HideInternalError)
            reduce {
                state.copy(internal = internal)
            }
        }
    }

    fun attemptCreateCharacter(securityLock: String?, characterName: String?, remark: String?) {
        intent {
            state.apply {
                if (simpleClient.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateCharacterSideEffect.ShowClientError(R.string.error_create_character_client_unselected))
                    return@intent
                }

                if (zone.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateCharacterSideEffect.ShowZoneError(R.string.error_create_character_zone_unselected))
                    return@intent
                }

                if (server.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateCharacterSideEffect.ShowServerError(R.string.error_create_character_server_unselected))
                    return@intent
                }

                if (simpleAccount.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateCharacterSideEffect.ShowAccountError(R.string.error_create_character_account_unselected))
                    return@intent
                }

                if (characterName.isNullOrBlank()) {
                    postSideEffect(CreateCharacterSideEffect.ShowNameError(R.string.error_create_character_name_empty))
                    return@intent
                }

                if (sect.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateCharacterSideEffect.ShowSectError(R.string.error_create_character_sect_unselected))
                    return@intent
                }

                if (internal.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateCharacterSideEffect.ShowInternalError(R.string.error_create_character_internal_unselected))
                    return@intent
                }

                createCharacterUseCase(
                    Character(
                        client = Client(simpleClient.id),
                        zone = zone,
                        server = server,
                        account = Account(simpleAccount.id),
                        name = characterName,
                        sect = sect,
                        internal = internal,
                        securityLock = securityLock.ifNull { "" },
                        remark = remark.ifNull { "" }
                    )
                )
                    .catch {
                        logger.e(it)
                        postSideEffect(CreateCharacterSideEffect.ShowSnack(R.string.error_create_character_failed))
                    }
                    .collect { character ->
                        postSideEffect(CreateCharacterSideEffect.CreateCharacterSuccess(character))
                    }
            }

        }
    }
}