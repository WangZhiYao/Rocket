package com.yizhenwind.rocket.core.mediator.client

import android.content.Context
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.core.model.ClientProfile
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/11
 */
interface IClientService {

    fun observeClientList(): Flow<List<Client>>

    fun observeClientProfile(): Flow<List<ClientProfile>>

    fun launchClientComposite(context: Context, clientId: Long)

}