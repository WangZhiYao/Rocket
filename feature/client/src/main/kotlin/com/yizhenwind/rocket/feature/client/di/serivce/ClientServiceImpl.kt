package com.yizhenwind.rocket.feature.client.di.serivce

import android.content.Context
import android.content.Intent
import androidx.paging.PagingData
import com.yizhenwind.rocket.core.mediator.client.IClientService
import com.yizhenwind.rocket.core.model.ClientProfile
import com.yizhenwind.rocket.domain.client.ObserveClientProfileUseCase
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
    private val observeClientProfileUseCase: ObserveClientProfileUseCase
) : IClientService {

    override fun observeClientProfile(): Flow<PagingData<ClientProfile>> =
        observeClientProfileUseCase()

    override fun launchClientComposite(context: Context, clientId: Long) {
        context.startActivity(Intent(context, ClientCompositeActivity::class.java).apply {
            replaceExtras(ClientCompositeActivityArgs(clientId).toBundle())
        })
    }

}