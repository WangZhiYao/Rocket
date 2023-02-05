package com.yizhenwind.rocket.domain.zone

import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.model.ZoneServer
import com.yizhenwind.rocket.data.zoneserver.ZoneServerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
class ObserveZoneServerUseCase @Inject constructor(
    private val zoneServerRepository: ZoneServerRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<ZoneServer>> =
        zoneServerRepository.observeZoneServer().flowOn(dispatcher)

}