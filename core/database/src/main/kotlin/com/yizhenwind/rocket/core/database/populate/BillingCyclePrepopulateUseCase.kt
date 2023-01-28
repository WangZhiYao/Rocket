package com.yizhenwind.rocket.core.database.populate

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.common.usecase.ExecuteResult
import com.yizhenwind.rocket.core.database.dao.BillingCycleDao
import com.yizhenwind.rocket.core.database.entity.BillingCycleEntity
import com.yizhenwind.rocket.core.database.mapper.BillingCycleMapper
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.BillingCycle
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
class BillingCyclePrepopulateUseCase @Inject constructor(
    @ApplicationContext context: Context,
    private val moshi: Moshi,
    private val billingCycleMapper: BillingCycleMapper,
    private val daoProvider: Provider<BillingCycleDao>,
    private val logger: ILogger,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) : PrepopulateUseCase<BillingCycleEntity>(context) {

    override fun transformSource(source: String): List<BillingCycleEntity>? =
        moshi.adapter<List<BillingCycle>>()
            .fromJson(source)?.map { billingCycleMapper.toEntity(it) }

    override fun insert(data: List<BillingCycleEntity>): Flow<List<Long>> =
        flow {
            emit(daoProvider.get().insert(data))
        }

    override fun execute(): Flow<ExecuteResult> =
        doPopulate(FILE_DEFAULT_BILLING_CYCLE)
            .catch {
                logger.e(it)
                emit(emptyList())
            }
            .map {
                ExecuteResult.CONTINUE
            }
            .flowOn(dispatcher)

    companion object {

        private const val FILE_DEFAULT_BILLING_CYCLE = "default_billing_cycle.json"
    }


}