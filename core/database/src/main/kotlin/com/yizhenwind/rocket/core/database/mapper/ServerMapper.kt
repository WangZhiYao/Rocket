package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.ServerEntity
import com.yizhenwind.rocket.core.model.Server
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class ServerMapper @Inject constructor() : IEntityMapper<ServerEntity, Server> {

    override fun fromEntity(entity: ServerEntity): Server =
        entity.run { Server(id, zoneId, name) }

    override fun toEntity(model: Server): ServerEntity =
        model.run { ServerEntity(id, zoneId, name) }

}