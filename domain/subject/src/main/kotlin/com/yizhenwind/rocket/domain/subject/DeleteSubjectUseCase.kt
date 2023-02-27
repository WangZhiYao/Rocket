package com.yizhenwind.rocket.domain.subject

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.Subject
import com.yizhenwind.rocket.data.subject.SubjectRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/27
 */
class DeleteSubjectUseCase @Inject constructor(
    private val subjectRepository: SubjectRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(subject: Subject): Flow<Int> =
        subjectRepository.deleteSubject(subject).flowOn(dispatcher)

}