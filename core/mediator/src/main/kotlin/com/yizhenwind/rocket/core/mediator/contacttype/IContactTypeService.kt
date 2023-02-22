package com.yizhenwind.rocket.core.mediator.contacttype

import android.content.Context
import com.yizhenwind.rocket.core.model.ContactType
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
interface IContactTypeService {

    fun observeContactType(): Flow<List<ContactType>>

    fun launchContactTypeList(context: Context)

}