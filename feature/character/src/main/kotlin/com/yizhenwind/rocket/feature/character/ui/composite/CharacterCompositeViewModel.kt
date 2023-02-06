package com.yizhenwind.rocket.feature.character.ui.composite

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
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
class CharacterCompositeViewModel @Inject constructor(

) : BaseMVIViewModel<CharacterCompositeViewState, CharacterCompositeSideEffect>() {

    override val container =
        container<CharacterCompositeViewState, CharacterCompositeSideEffect>(
            CharacterCompositeViewState()
        )

    fun setTitle(title: CharSequence) {
        intent {
            reduce {
                state.copy(title = title)
            }
        }
    }

    fun showError(@StringRes resId: Int) {
        intent {
            postSideEffect(CharacterCompositeSideEffect.ShowError(resId))
        }
    }

}