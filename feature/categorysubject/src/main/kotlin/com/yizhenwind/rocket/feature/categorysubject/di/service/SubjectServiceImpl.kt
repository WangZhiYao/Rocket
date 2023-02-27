package com.yizhenwind.rocket.feature.categorysubject.di.service

import com.yizhenwind.rocket.core.mediator.subject.ISubjectService
import com.yizhenwind.rocket.core.model.Subject
import com.yizhenwind.rocket.domain.subject.ObserveSubjectListByCategoryIdUseCase
import com.yizhenwind.rocket.domain.subject.ObserveSubjectListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
class SubjectServiceImpl @Inject constructor(
    private val observeSubjectListUseCase: ObserveSubjectListUseCase,
    private val observeSubjectListByCategoryIdUseCase: ObserveSubjectListByCategoryIdUseCase
) : ISubjectService {

    override fun observeSubjectList(): Flow<List<Subject>> =
        observeSubjectListUseCase()

    override fun observeSubjectListByCategoryId(categoryId: Long): Flow<List<Subject>> =
        observeSubjectListByCategoryIdUseCase(categoryId)

}