package com.yizhenwind.rocket.feature.account.ui.composite

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
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
@HiltViewModel
class AccountCompositeViewModel @Inject constructor() :
    BaseMVIViewModel<AccountCompositeViewState, AccountCompositeSideEffect>() {

    override val container =
        container<AccountCompositeViewState, AccountCompositeSideEffect>(AccountCompositeViewState())

    fun setTitle(title: CharSequence) {
        intent {
            reduce {
                state.copy(title = title)
            }
        }
    }

    fun showError(@StringRes resId: Int) {
        intent {
            postSideEffect(AccountCompositeSideEffect.ShowError(resId))
        }
    }

}