package com.yizhenwind.rocket.feature.customer.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.rocket.core.framework.mvi.BaseMVIViewModel
import com.yizhenwind.rocket.data.customer.domain.ObserveCustomerListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/29
 */
@HiltViewModel
class CustomerListViewModel @Inject constructor(
    private val observeCustomerListUseCase: ObserveCustomerListUseCase
) : BaseMVIViewModel<CustomerListViewState, CustomerListSideEffect>() {

    override val container: Container<CustomerListViewState, CustomerListSideEffect> =
        container(CustomerListViewState())

    init {
        intent {
            observeCustomerListUseCase()
                .cachedIn(viewModelScope)
                .collect { customerList ->
                    reduce {
                        state.copy(customerList = customerList)
                    }
                }
        }
    }
}