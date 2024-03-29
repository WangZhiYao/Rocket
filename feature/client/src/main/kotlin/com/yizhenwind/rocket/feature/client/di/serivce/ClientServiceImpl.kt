package com.yizhenwind.rocket.feature.client.di.serivce

import android.content.Context
import android.content.Intent
import com.yizhenwind.rocket.core.mediator.client.IClientService
import com.yizhenwind.rocket.core.model.ClientTuple
import com.yizhenwind.rocket.domain.client.ObserveClientTupleListUseCase
import com.yizhenwind.rocket.feature.client.ui.composite.ClientCompositeActivity
import com.yizhenwind.rocket.feature.client.ui.composite.ClientCompositeActivityArgs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
class ClientServiceImpl @Inject constructor(
    private val observeClientTupleListUseCase: ObserveClientTupleListUseCase,
) : IClientService {

    override fun observeClientTupleList(): Flow<List<ClientTuple>> =
        observeClientTupleListUseCase()

    override fun launchClientComposite(context: Context, clientId: Long) {
        context.startActivity(Intent(context, ClientCompositeActivity::class.java).apply {
            replaceExtras(ClientCompositeActivityArgs(clientId).toBundle())
        })
    }

}