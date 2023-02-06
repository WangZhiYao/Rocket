package com.yizhenwind.rocket.feature.character.ui.composite.detail

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.domain.character.ObserveCharacterByIdUseCase
import com.yizhenwind.rocket.feature.character.R
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val observeCharacterByIdUseCase: ObserveCharacterByIdUseCase
) : BaseMVIViewModel<CharacterDetailViewState, CharacterDetailSideEffect>() {

    override val container =
        container<CharacterDetailViewState, CharacterDetailSideEffect>(CharacterDetailViewState())

    val clientId: Long
        get() = container.stateFlow.value.character.client.id

    val zoneId: Long
        get() = container.stateFlow.value.character.zone.id

    val serverId: Long
        get() = container.stateFlow.value.character.server.id

    val accountId: Long
        get() = container.stateFlow.value.character.account.id

    val sectId: Long
        get() = container.stateFlow.value.character.sect.id

    val internalId: Long
        get() = container.stateFlow.value.character.internal.id

    val securityLock: String?
        get() = container.stateFlow.value.character.securityLock

    fun observeCharacterById(id: Long) {
        intent {
            observeCharacterByIdUseCase(id)
                .collect { character ->
                    if (character.id == Constant.DEFAULT_ID) {
                        postSideEffect(CharacterDetailSideEffect(R.string.error_character_detail))
                    } else {
                        reduce {
                            state.copy(character = character)
                        }
                    }
                }
        }
    }
}