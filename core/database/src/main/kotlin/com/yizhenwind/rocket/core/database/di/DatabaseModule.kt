package com.yizhenwind.rocket.core.database.di

import android.content.Context
import androidx.room.Room
import com.yizhenwind.rocket.core.database.RocketDatabase
import com.yizhenwind.rocket.core.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    fun provideBoosterDatabase(@ApplicationContext appContext: Context): RocketDatabase =
        Room.databaseBuilder(
            appContext,
            RocketDatabase::class.java,
            DATABASE_NAME
        )
            .build()

    @Provides
    @Singleton
    fun provideCustomerDao(database: RocketDatabase): CustomerDao =
        database.customerDao()

}