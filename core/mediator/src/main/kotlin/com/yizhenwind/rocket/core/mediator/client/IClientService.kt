package com.yizhenwind.rocket.core.mediator.client

import android.content.Context
import androidx.paging.PagingData
import com.yizhenwind.rocket.core.model.ClientProfile
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/11
 */
interface IClientService {

    fun observeClientProfile(): Flow<PagingData<ClientProfile>>

    fun launchClientComposite(context: Context, clientId: Long)

}