package com.yizhenwind.rocket.feature.character.ui.composite

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.model.Character
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
class CharacterCompositeViewModel @Inject constructor() :
    BaseMVIViewModel<CharacterCompositeViewState, CharacterCompositeSideEffect>() {

    override val container =
        container<CharacterCompositeViewState, CharacterCompositeSideEffect>(
            CharacterCompositeViewState()
        )

    val clientId: Long
        get() = container.stateFlow.value.character.client.id
    val accountId: Long
        get() = container.stateFlow.value.character.account.id

    fun setCharacter(character: Character) {
        intent {
            reduce {
                state.copy(character = character)
            }
        }
    }

    fun showError(@StringRes resId: Int) {
        intent {
            postSideEffect(CharacterCompositeSideEffect.ShowError(resId))
        }
    }

}