package com.yizhenwind.rocket.feature.client.ui.composite.character

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.mediator.character.ICharacterService
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
@HiltViewModel
class ClientCharacterViewModel @Inject constructor(
    private val characterService: ICharacterService
) : BaseMVIViewModel<ClientCharacterViewState, Nothing>() {

    override val container =
        container<ClientCharacterViewState, Nothing>(ClientCharacterViewState())

    fun observeCharacterProfileByClientId(clientId: Long) {
        intent {
            characterService.observeCharacterProfileByClientId(clientId)
                .collect { characterProfileList ->
                    reduce {
                        state.copy(characterProfileList = characterProfileList)
                    }
                }
        }
    }
}