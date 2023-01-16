package com.yizhenwind.rocket.core.database.di

import android.content.Context
import androidx.paging.PagingConfig
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yizhenwind.rocket.core.database.RocketDatabase
import com.yizhenwind.rocket.core.database.dao.*
import com.yizhenwind.rocket.core.database.di.qualifier.DatabasePagingConfig
import com.yizhenwind.rocket.core.logger.ILogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * 数据库模块依赖
 *
 * @author WangZhiYao
 * @since 2022/3/1
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "rocket.db"

    @Provides
    @Singleton
    fun provideRocketDatabase(
        @ApplicationContext appContext: Context,
        logger: ILogger
    ): RocketDatabase =
        Room.databaseBuilder(
            appContext,
            RocketDatabase::class.java,
            DATABASE_NAME
        )
            .setQueryCallback(
                object : RoomDatabase.QueryCallback {
                    override fun onQuery(sqlQuery: String, bindArgs: List<Any?>) {
                        logger.d(
                            String.format(
                                sqlQuery.replace("?", "%s"),
                                *bindArgs.toTypedArray()
                            )
                        )
                    }
                },
                Executors.newSingleThreadExecutor()
            )
            .build()

    @Provides
    @Singleton
    @DatabasePagingConfig
    fun provideDatabasePagingConfig(): PagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 3,
        enablePlaceholders = false
    )

    @Provides
    @Singleton
    fun provideClientDao(database: RocketDatabase): ClientDao =
        database.clientDao()

    @Provides
    @Singleton
    fun provideContactDao(database: RocketDatabase): ContactDao =
        database.contactDao()

}