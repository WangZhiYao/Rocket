package com.yizhenwind.rocket.feature.client.ui.composite.account

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.mediator.account.IAccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
@HiltViewModel
class ClientAccountViewModel @Inject constructor(
    private val accountService: IAccountService
) : BaseMVIViewModel<ClientAccountViewState, Nothing>() {

    override val container =
        container<ClientAccountViewState, Nothing>(ClientAccountViewState())

    fun observeAccountProfileByClientId(clientId: Long) {
        intent {
            accountService.observeAccountListByClientId(clientId)
                .collect { accountList ->
                    reduce {
                        state.copy(accountList = accountList)
                    }
                }
        }
    }
}