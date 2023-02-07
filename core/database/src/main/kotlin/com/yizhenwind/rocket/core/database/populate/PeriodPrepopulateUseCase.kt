package com.yizhenwind.rocket.core.database.populate

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.common.usecase.ExecuteResult
import com.yizhenwind.rocket.core.database.dao.PeriodDao
import com.yizhenwind.rocket.core.database.entity.PeriodEntity
import com.yizhenwind.rocket.core.database.mapper.PeriodMapper
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.Period
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
class PeriodPrepopulateUseCase @Inject constructor(
    @ApplicationContext context: Context,
    private val moshi: Moshi,
    private val periodMapper: PeriodMapper,
    private val daoProvider: Provider<PeriodDao>,
    private val logger: ILogger,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) : PrepopulateUseCase<PeriodEntity>(context) {

    override fun transformSource(source: String): List<PeriodEntity>? =
        moshi.adapter<List<Period>>()
            .fromJson(source)?.map { periodMapper.toEntity(it) }

    override fun insert(data: List<PeriodEntity>): Flow<List<Long>> =
        flow {
            emit(daoProvider.get().insert(data))
        }

    override fun execute(): Flow<ExecuteResult> =
        doPopulate(FILE_DEFAULT_PERIOD)
            .catch {
                logger.e(it)
                emit(emptyList())
            }
            .map {
                ExecuteResult.CONTINUE
            }
            .flowOn(dispatcher)

    companion object {

        private const val FILE_DEFAULT_PERIOD = "default_period.json"
    }


}