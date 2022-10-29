package com.yizhenwind.rocket.data.customer.domain

import androidx.paging.PagingData
import com.yizhenwind.rocket.core.infra.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.data.customer.model.Customer
import com.yizhenwind.rocket.data.customer.repository.CustomerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/29
 */
class ObserveCustomerListUseCase @Inject constructor(
    private val customerRepository: CustomerRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher

) {

    operator fun invoke(): Flow<PagingData<Customer>> =
        customerRepository.observeCustomerList()
            .flowOn(dispatcher)
}