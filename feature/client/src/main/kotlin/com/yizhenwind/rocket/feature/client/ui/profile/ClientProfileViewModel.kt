package com.yizhenwind.rocket.feature.client.ui.profile

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.domain.client.ObserveClientProfileUseCase
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
@HiltViewModel
class ClientProfileViewModel @Inject constructor(
    private val observeClientProfileUseCase: ObserveClientProfileUseCase
) : BaseMVIViewModel<ClientProfileViewState, Nothing>() {

    override val container = container<ClientProfileViewState, Nothing>(ClientProfileViewState())

    init {
        intent {
            observeClientProfileUseCase()
                .cachedIn(viewModelScope)
                .collect {
                    reduce {
                        state.copy(clientProfileList = it)
                    }
                }
        }
    }

}