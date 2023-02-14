package com.yizhenwind.rocket.core.database.populate

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IOScope
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
    private val paymentMethodPrepopulateUseCase: PaymentMethodPrepopulateUseCase,
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
            add(paymentMethodPrepopulateUseCase)
        }
        ioScope.launch {
            sequence.execute().collect {
                logger.d("prepopulate database status: %s", it.name)
            }
        }
    }

}