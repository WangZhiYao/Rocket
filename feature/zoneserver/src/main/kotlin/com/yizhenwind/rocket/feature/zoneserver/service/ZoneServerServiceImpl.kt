package com.yizhenwind.rocket.feature.zoneserver.service

import com.yizhenwind.rocket.core.mediator.zoneserver.IZoneServerService
import com.yizhenwind.rocket.core.model.ZoneServer
import com.yizhenwind.rocket.domain.zone.ObserveZoneServerUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
class ZoneServerServiceImpl @Inject constructor(
    private val observeZoneServerUseCase: ObserveZoneServerUseCase
) : IZoneServerService {

    override fun observeZoneServer(): Flow<List<ZoneServer>> =
        observeZoneServerUseCase()

}