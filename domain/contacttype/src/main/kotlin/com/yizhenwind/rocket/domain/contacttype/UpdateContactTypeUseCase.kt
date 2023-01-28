package com.yizhenwind.rocket.domain.contacttype

import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.model.ContactType
import com.yizhenwind.rocket.data.contacttype.ContactTypeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
class UpdateContactTypeUseCase @Inject constructor(
    private val contactTypeRepository: ContactTypeRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(contactType: ContactType): Flow<ContactType> =
        contactTypeRepository.updateContactType(contactType)
            .map { contactType }
            .flowOn(dispatcher)

}