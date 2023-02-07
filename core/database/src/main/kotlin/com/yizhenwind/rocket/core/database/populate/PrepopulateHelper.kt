package com.yizhenwind.rocket.core.database.populate

import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IOScope
import com.yizhenwind.rocket.core.common.usecase.SequenceUseCase
import com.yizhenwind.rocket.core.logger.ILogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
class PrepopulateHelper @Inject constructor(
    @IOScope private val ioScope: CoroutineScope,
    private val contactTypePrepopulateUseCase: ContactTypePrepopulateUseCase,
    private val zonePrepopulateUseCase: ZonePrepopulateUseCase,
    private val serverPrepopulateUseCase: ServerPrepopulateUseCase,
    private val sectPrepopulateUseCase: SectPrepopulateUseCase,
    private val internalPrepopulateUseCase: InternalPrepopulateUseCase,
    private val categoryPrepopulateUseCase: CategoryPrepopulateUseCase,
    private val periodPrepopulateUseCase: PeriodPrepopulateUseCase,
    private val orderStatusPrepopulateUseCase: OrderStatusPrepopulateUseCase,
    private val paymentMethodPrepopulateUseCase: PaymentMethodPrepopulateUseCase,
    private val paymentStatusPrepopulateUseCase: PaymentStatusPrepopulateUseCase,
    private val logger: ILogger
) {

    fun doPopulate() {
        val sequence = SequenceUseCase().apply {
            add(contactTypePrepopulateUseCase)
            add(zonePrepopulateUseCase)
            add(serverPrepopulateUseCase)
            add(sectPrepopulateUseCase)
            add(internalPrepopulateUseCase)
            add(categoryPrepopulateUseCase)
            add(periodPrepopulateUseCase)
            add(orderStatusPrepopulateUseCase)
            add(paymentMethodPrepopulateUseCase)
            add(paymentStatusPrepopulateUseCase)
        }
        ioScope.launch {
            sequence.execute().collect {
                logger.d("prepopulate database done %s", it)
            }
        }
    }

}