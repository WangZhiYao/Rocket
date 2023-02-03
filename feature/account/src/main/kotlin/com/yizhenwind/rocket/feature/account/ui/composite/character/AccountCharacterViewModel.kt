package com.yizhenwind.rocket.feature.account.ui.composite.character

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
 * @since 2023/2/2
 */
@HiltViewModel
class AccountCharacterViewModel @Inject constructor(
    private val characterService: ICharacterService
) : BaseMVIViewModel<AccountCharacterViewState, Nothing>() {

    override val container =
        container<AccountCharacterViewState, Nothing>(AccountCharacterViewState())

    fun observeCharacterProfileByAccountId(accountId: Long) {
        intent {
            characterService.observeCharacterProfileByAccountId(accountId)
                .collect { characterProfileList ->
                    reduce {
                        state.copy(characterProfileList = characterProfileList)
                    }
                }
        }
    }

}