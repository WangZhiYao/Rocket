package com.yizhenwind.rocket.data.subject.source

import com.yizhenwind.rocket.core.database.dao.SubjectDao
import com.yizhenwind.rocket.core.database.dto.SubjectDto
import com.yizhenwind.rocket.core.database.entity.SubjectEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
class SubjectLocalDataSource @Inject constructor(
    private val subjectDao: SubjectDao
) {

    fun observeSubjectList(): Flow<List<SubjectDto>> =
        subjectDao.observeSubjectList()

    fun observeSubjectListByCategoryId(categoryId: Long): Flow<List<SubjectDto>> =
        subjectDao.observeSubjectListByCategoryId(categoryId)

    fun createSubject(subjectEntity: SubjectEntity): Flow<Long> =
        flow {
            emit(subjectDao.insert(subjectEntity))
        }

}