package com.yizhenwind.rocket.domain.contacttype

import com.yizhenwind.rocket.core.model.ContactType
import com.yizhenwind.rocket.data.contacttype.ContactTypeRepository
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
class GetContactTypeByNameUseCase @Inject constructor(
    private val contactTypeRepository: ContactTypeRepository,
) {

    suspend operator fun invoke(name: String): ContactType =
        contactTypeRepository.getContactTypeByName(name) ?: ContactType()

}