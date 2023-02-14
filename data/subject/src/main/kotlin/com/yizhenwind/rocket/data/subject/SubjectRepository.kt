package com.yizhenwind.rocket.data.subject

import com.yizhenwind.rocket.core.common.mapper.ListMapper
import com.yizhenwind.rocket.core.database.mapper.SubjectDtoMapper
import com.yizhenwind.rocket.core.database.mapper.SubjectMapper
import com.yizhenwind.rocket.core.model.Subject
import com.yizhenwind.rocket.data.subject.source.SubjectLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
class SubjectRepository @Inject constructor(
    private val subjectLocalDataSource: SubjectLocalDataSource,
    private val subjectDtoMapper: SubjectDtoMapper,
    private val subjectMapper: SubjectMapper
) {

    fun observeSubjectList(): Flow<List<Subject>> =
        subjectLocalDataSource.observeSubjectList()
            .map { ListMapper(subjectDtoMapper).map(it) }

    fun observeSubjectListByCategoryId(categoryId: Long): Flow<List<Subject>> =
        subjectLocalDataSource.observeSubjectListByCategoryId(categoryId)
            .map { ListMapper(subjectDtoMapper).map(it) }

    fun createSubject(subject: Subject): Flow<Subject> =
        subjectLocalDataSource.createSubject(subjectMapper.map(subject))
            .map { id -> subject.copy(id = id) }

}