package com.yizhenwind.rocket.core.mediator.client

import android.content.Context
import com.yizhenwind.rocket.core.model.simple.SimpleClient
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/11
 */
interface IClientService {

    fun observeSimpleClientList(): Flow<List<SimpleClient>>

    fun launchClientComposite(context: Context, clientId: Long)

}