package com.yizhenwind.rocket.core.mediator.subject

import android.content.Context
import com.yizhenwind.rocket.core.model.Subject
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
interface ISubjectService {

    fun observeSubjectList(): Flow<List<Subject>>

    fun observeSubjectListByCategoryId(categoryId: Long): Flow<List<Subject>>

    fun launchCreateSubject(context: Context, categoryId: Long)

}