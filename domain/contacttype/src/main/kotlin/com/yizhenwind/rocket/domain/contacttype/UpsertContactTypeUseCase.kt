package com.yizhenwind.rocket.domain.contacttype

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.ContactType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
class UpsertContactTypeUseCase @Inject constructor(
    private val getContactTypeByNameUseCase: GetContactTypeByNameUseCase,
    private val createContactTypeUseCase: CreateContactTypeUseCase,
    private val updateContactTypeUseCase: UpdateContactTypeUseCase,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    @OptIn(FlowPreview::class)
    operator fun invoke(name: String): Flow<ContactType> =
        flow {
            emit(getContactTypeByNameUseCase(name))
        }
            .flatMapConcat { contactType ->
                if (contactType.id == Constant.DEFAULT_ID) {
                    createContactTypeUseCase(ContactType(name = name))
                } else {
                    updateContactTypeUseCase(contactType.copy(enable = true))
                }
            }
            .flowOn(dispatcher)

}