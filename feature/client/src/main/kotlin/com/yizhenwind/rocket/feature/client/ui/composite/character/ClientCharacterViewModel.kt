package com.yizhenwind.rocket.feature.client.ui.composite.character

import com.yizhenwind.rocket.core.framework.mvi.BaseMVIViewModel
import com.yizhenwind.rocket.core.mediator.character.ICharacterService
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/4
 */
@HiltViewModel
class ClientCharacterViewModel @Inject constructor(
    private val characterService: ICharacterService
) : BaseMVIViewModel<ClientCharacterViewState, ClientCharacterSideEffect>() {

    override val container =
        container<ClientCharacterViewState, ClientCharacterSideEffect>(ClientCharacterViewState())

    fun observeCharacterListByClientId(clientId: Long) {
        intent {
            characterService.observeCharacterListByClientId(clientId)
                .collect { characterList ->
                    reduce {
                        state.copy(characterList = characterList)
                    }
                }
        }
    }
}