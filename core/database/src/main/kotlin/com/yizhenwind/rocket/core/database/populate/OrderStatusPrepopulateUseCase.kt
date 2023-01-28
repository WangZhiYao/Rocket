package com.yizhenwind.rocket.core.database.populate

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.common.usecase.ExecuteResult
import com.yizhenwind.rocket.core.database.dao.OrderStatusDao
import com.yizhenwind.rocket.core.database.entity.OrderStatusEntity
import com.yizhenwind.rocket.core.database.mapper.OrderStatusMapper
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.OrderStatus
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
class OrderStatusPrepopulateUseCase @Inject constructor(
    @ApplicationContext context: Context,
    private val moshi: Moshi,
    private val orderStatusMapper: OrderStatusMapper,
    private val daoProvider: Provider<OrderStatusDao>,
    private val logger: ILogger,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : PrepopulateUseCase<OrderStatusEntity>(context) {

    override fun transformSource(source: String): List<OrderStatusEntity>? =
        moshi.adapter<List<OrderStatus>>()
            .fromJson(source)?.map { orderStatusMapper.toEntity(it) }

    override fun insert(data: List<OrderStatusEntity>): Flow<List<Long>> =
        flow {
            emit(daoProvider.get().insert(data))
        }

    override fun execute(): Flow<ExecuteResult> =
        doPopulate(FILE_DEFAULT_ORDER_STATUS)
            .catch {
                logger.e(it)
                emit(emptyList())
            }
            .map {
                ExecuteResult.CONTINUE
            }
            .flowOn(dispatcher)

    companion object {

        private const val FILE_DEFAULT_ORDER_STATUS = "default_order_status.json"

    }
}