package com.yizhenwind.rocket.core.database.populate

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.common.usecase.ExecuteResult
import com.yizhenwind.rocket.core.database.dao.PaymentStatusDao
import com.yizhenwind.rocket.core.database.entity.PaymentStatusEntity
import com.yizhenwind.rocket.core.database.mapper.PaymentStatusMapper
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.PaymentStatus
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
class PaymentStatusPrepopulateUseCase @Inject constructor(
    @ApplicationContext context: Context,
    private val moshi: Moshi,
    private val paymentStatusMapper: PaymentStatusMapper,
    private val daoProvider: Provider<PaymentStatusDao>,
    private val logger: ILogger,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : PrepopulateUseCase<PaymentStatusEntity>(context) {

    override fun transformSource(source: String): List<PaymentStatusEntity>? =
        moshi.adapter<List<PaymentStatus>>()
            .fromJson(source)?.map { paymentStatusMapper.toEntity(it) }

    override fun insert(data: List<PaymentStatusEntity>): Flow<List<Long>> =
        flow {
            emit(daoProvider.get().insert(data))
        }

    override fun execute(): Flow<ExecuteResult> =
        doPopulate(FILE_DEFAULT_PAYMENT_STATUS)
            .catch {
                logger.e(it)
                emit(emptyList())
            }
            .map {
                ExecuteResult.CONTINUE
            }
            .flowOn(dispatcher)

    companion object {

        private const val FILE_DEFAULT_PAYMENT_STATUS = "default_payment_status.json"

    }
}