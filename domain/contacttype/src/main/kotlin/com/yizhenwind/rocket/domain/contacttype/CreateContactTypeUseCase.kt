package com.yizhenwind.rocket.domain.contacttype

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
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
class CreateContactTypeUseCase @Inject constructor(
    private val contactTypeRepository: ContactTypeRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(contactType: ContactType): Flow<ContactType> =
        contactTypeRepository.createContactType(contactType)
            .map { id ->
                contactType.copy(id = id)
            }
            .flowOn(dispatcher)

}