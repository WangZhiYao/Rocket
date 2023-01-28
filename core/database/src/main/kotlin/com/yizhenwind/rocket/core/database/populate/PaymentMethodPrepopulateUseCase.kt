package com.yizhenwind.rocket.core.database.populate

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.common.usecase.ExecuteResult
import com.yizhenwind.rocket.core.database.dao.PaymentMethodDao
import com.yizhenwind.rocket.core.database.entity.PaymentMethodEntity
import com.yizhenwind.rocket.core.database.mapper.PaymentMethodMapper
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.PaymentMethod
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
class PaymentMethodPrepopulateUseCase @Inject constructor(
    @ApplicationContext context: Context,
    private val moshi: Moshi,
    private val paymentMethodMapper: PaymentMethodMapper,
    private val daoProvider: Provider<PaymentMethodDao>,
    private val logger: ILogger,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : PrepopulateUseCase<PaymentMethodEntity>(context) {

    override fun transformSource(source: String): List<PaymentMethodEntity>? =
        moshi.adapter<List<PaymentMethod>>()
            .fromJson(source)?.map { paymentMethodMapper.toEntity(it) }

    override fun insert(data: List<PaymentMethodEntity>): Flow<List<Long>> =
        flow {
            emit(daoProvider.get().insert(data))
        }

    override fun execute(): Flow<ExecuteResult> =
        doPopulate(FILE_DEFAULT_PAYMENT_METHOD)
            .catch {
                logger.e(it)
                emit(emptyList())
            }
            .map {
                ExecuteResult.CONTINUE
            }
            .flowOn(dispatcher)

    companion object {

        private const val FILE_DEFAULT_PAYMENT_METHOD = "default_payment_method.json"

    }
}