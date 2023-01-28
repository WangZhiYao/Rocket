package com.yizhenwind.rocket.core.database.populate

import android.content.Context
import com.yizhenwind.rocket.core.common.usecase.ExecuteResult
import com.yizhenwind.rocket.core.common.usecase.IUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
abstract class PrepopulateUseCase<ENTITY>(
    private val context: Context
) : IUseCase<Flow<ExecuteResult>> {

    abstract fun transformSource(source: String): List<ENTITY>?

    abstract fun insert(data: List<ENTITY>): Flow<List<Long>>

    @OptIn(FlowPreview::class)
    fun doPopulate(
        fileName: String
    ): Flow<List<Long>> =
        flow {
            emit(
                context.resources.assets.open(fileName)
                    .use { inputStream -> inputStream.bufferedReader().readText() }
            )
        }
            .map {
                transformSource(it)
            }
            .filterNotNull()
            .filter { it.isNotEmpty() }
            .flatMapConcat {
                insert(it)
            }

}