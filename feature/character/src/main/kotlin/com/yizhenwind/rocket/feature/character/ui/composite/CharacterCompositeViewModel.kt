package com.yizhenwind.rocket.feature.character.ui.composite

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.Character
import com.yizhenwind.rocket.domain.character.DeleteCharacterUseCase
import com.yizhenwind.rocket.feature.character.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
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
class CharacterCompositeViewModel @Inject constructor(
    private val deleteCharacterUseCase: DeleteCharacterUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<CharacterCompositeViewState, CharacterCompositeSideEffect>() {

    override val container =
        container<CharacterCompositeViewState, CharacterCompositeSideEffect>(
            CharacterCompositeViewState()
        )

    val character: Character
        get() = container.stateFlow.value.character

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

    fun deleteCharacter(character: Character) {
        intent {
            deleteCharacterUseCase(character)
                .catch {
                    logger.e(it)
                    postSideEffect(CharacterCompositeSideEffect.ShowError(R.string.error_delete_character_failed))
                }
                .collect {
                    postSideEffect(CharacterCompositeSideEffect.DeleteCharacterSuccess)
                }
        }
    }

}