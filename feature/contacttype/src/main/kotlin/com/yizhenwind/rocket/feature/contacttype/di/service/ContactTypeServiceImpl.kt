package com.yizhenwind.rocket.feature.contacttype.di.service

import com.yizhenwind.rocket.core.mediator.contacttype.IContactTypeService
import com.yizhenwind.rocket.core.model.ContactType
import com.yizhenwind.rocket.domain.contacttype.ObserveContactTypeListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
class ContactTypeServiceImpl @Inject constructor(
    private val observeContactTypeListUseCase: ObserveContactTypeListUseCase
) : IContactTypeService {

    override fun observeContactType(): Flow<List<ContactType>> =
        observeContactTypeListUseCase()

}