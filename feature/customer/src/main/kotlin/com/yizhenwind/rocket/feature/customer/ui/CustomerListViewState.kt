package com.yizhenwind.rocket.feature.customer.ui

import androidx.paging.PagingData
import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.data.customer.model.Customer

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/29
 */
data class CustomerListViewState(
    val customerList: PagingData<Customer> = PagingData.empty()
) : IViewState