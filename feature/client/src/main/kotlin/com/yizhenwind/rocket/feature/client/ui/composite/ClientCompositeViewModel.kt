package com.yizhenwind.rocket.feature.client.ui.composite

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
 * @since 2023/1/20
 */
@HiltViewModel
class ClientCompositeViewModel @Inject constructor() :
    BaseMVIViewModel<ClientCompositeViewState, ClientCompositeSideEffect>() {

    override val container =
        container<ClientCompositeViewState, ClientCompositeSideEffect>(ClientCompositeViewState())

    fun setTitle(title: CharSequence) {
        intent {
            reduce {
                state.copy(title = title)
            }
        }
    }

    fun showError(@StringRes resId: Int) {
        intent {
            postSideEffect(ClientCompositeSideEffect.ShowError(resId))
        }
    }
}