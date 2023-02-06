package com.yizhenwind.rocket.core.database.populate

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.common.usecase.ExecuteResult
import com.yizhenwind.rocket.core.database.dao.ContactTypeDao
import com.yizhenwind.rocket.core.database.entity.ContactTypeEntity
import com.yizhenwind.rocket.core.database.mapper.ContactTypeMapper
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.ContactType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Provider

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@OptIn(ExperimentalStdlibApi::class)
class ContactTypePrepopulateUseCase @Inject constructor(
    @ApplicationContext context: Context,
    private val moshi: Moshi,
    private val contactTypeMapper: ContactTypeMapper,
    private val daoProvider: Provider<ContactTypeDao>,
    private val logger: ILogger,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : PrepopulateUseCase<ContactTypeEntity>(context) {

    override fun transformSource(source: String): List<ContactTypeEntity>? =
        moshi.adapter<List<ContactType>>()
            .fromJson(source)?.map { contactTypeMapper.toEntity(it) }

    override fun insert(data: List<ContactTypeEntity>): Flow<List<Long>> =
        flow {
            emit(daoProvider.get().insert(data))
        }

    override fun execute(): Flow<ExecuteResult> =
        doPopulate(FILE_DEFAULT_CONTACT_TYPE)
            .catch {
                logger.e(it)
                emit(emptyList())
            }
            .map {
                ExecuteResult.CONTINUE
            }
            .flowOn(dispatcher)

    companion object {

        private const val FILE_DEFAULT_CONTACT_TYPE = "default_contact_type.json"

    }
}