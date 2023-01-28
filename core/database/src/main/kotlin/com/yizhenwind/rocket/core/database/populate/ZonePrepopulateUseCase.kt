package com.yizhenwind.rocket.core.database.populate

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.common.usecase.ExecuteResult
import com.yizhenwind.rocket.core.database.dao.ZoneDao
import com.yizhenwind.rocket.core.database.entity.ZoneEntity
import com.yizhenwind.rocket.core.database.mapper.ZoneMapper
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.Zone
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Provider

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@OptIn(ExperimentalStdlibApi::class)
class ZonePrepopulateUseCase @Inject constructor(
    @ApplicationContext context: Context,
    private val moshi: Moshi,
    private val zoneMapper: ZoneMapper,
    private val daoProvider: Provider<ZoneDao>,
    private val logger: ILogger,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : PrepopulateUseCase<ZoneEntity>(context) {

    override fun transformSource(source: String): List<ZoneEntity>? =
        moshi.adapter<List<Zone>>()
            .fromJson(source)?.map { zoneMapper.toEntity(it) }

    override fun insert(data: List<ZoneEntity>): Flow<List<Long>> =
        flow {
            emit(daoProvider.get().insert(data))
        }

    override fun execute(): Flow<ExecuteResult> =
        doPopulate(FILE_DEFAULT_ZONE)
            .catch {
                logger.e(it)
                emit(emptyList())
            }
            .map {
                ExecuteResult.CONTINUE
            }
            .flowOn(dispatcher)

    companion object {

        private const val FILE_DEFAULT_ZONE = "default_zone.json"
    }
}