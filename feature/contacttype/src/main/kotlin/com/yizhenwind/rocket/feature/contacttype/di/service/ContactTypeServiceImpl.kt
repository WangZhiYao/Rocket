package com.yizhenwind.rocket.feature.contacttype.di.service

import android.content.Context
import com.yizhenwind.rocket.core.mediator.contacttype.IContactTypeService
import com.yizhenwind.rocket.core.model.ContactType
import com.yizhenwind.rocket.domain.contacttype.ObserveContactTypeUseCase
import com.yizhenwind.rocket.feature.contacttype.ui.ContactTypeArgs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
class ContactTypeServiceImpl @Inject constructor(
    private val observeContactTypeUseCase: ObserveContactTypeUseCase
) : IContactTypeService {

    override fun observeContactType(): Flow<List<ContactType>> =
        observeContactTypeUseCase()

    override fun launchContactTypeList(context: Context) {
        ContactTypeArgs().launch(context)
    }

}