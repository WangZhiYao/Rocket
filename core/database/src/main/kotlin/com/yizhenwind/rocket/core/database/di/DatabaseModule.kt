package com.yizhenwind.rocket.core.database.di

import android.content.Context
import androidx.paging.PagingConfig
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.yizhenwind.rocket.core.database.RocketDatabase
import com.yizhenwind.rocket.core.database.dao.*
import com.yizhenwind.rocket.core.database.di.qualifier.DatabasePagingConfig
import com.yizhenwind.rocket.core.database.populate.PrepopulateHelper
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
        prepopulateHelper: PrepopulateHelper,
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
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    prepopulateHelper.doPopulate()
                }
            })
            .build()

    @Provides
    @Singleton
    @DatabasePagingConfig
    fun provideDatabasePagingConfig(): PagingConfig =
        PagingConfig(
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
    fun provideContactTypeDao(database: RocketDatabase): ContactTypeDao =
        database.contactTypeDao()

    @Provides
    @Singleton
    fun provideAccountDao(database: RocketDatabase): AccountDao =
        database.accountDao()

    @Provides
    @Singleton
    fun provideCharacterDao(database: RocketDatabase): CharacterDao =
        database.characterDao()

    @Provides
    @Singleton
    fun provideZoneDao(database: RocketDatabase): ZoneDao =
        database.zoneDao()

    @Provides
    @Singleton
    fun provideServerDao(database: RocketDatabase): ServerDao =
        database.serverDao()

    @Provides
    @Singleton
    fun provideSectDao(database: RocketDatabase): SectDao =
        database.sectDao()

    @Provides
    @Singleton
    fun provideInternalDao(database: RocketDatabase): InternalDao =
        database.internalDao()

    @Provides
    @Singleton
    fun provideCategoryDao(database: RocketDatabase): CategoryDao =
        database.categoryDao()

    @Provides
    @Singleton
    fun provideSubjectDao(database: RocketDatabase): SubjectDao =
        database.subjectDao()

    @Provides
    @Singleton
    fun provideOrderDao(database: RocketDatabase): OrderDao =
        database.orderDao()

    @Provides
    @Singleton
    fun provideBillingCycleDao(database: RocketDatabase): BillingCycleDao =
        database.billingCycleDao()

    @Provides
    @Singleton
    fun provideOrderStatusDao(database: RocketDatabase): OrderStatusDao =
        database.orderStatusDao()

    @Provides
    @Singleton
    fun providePaymentMethodDao(database: RocketDatabase): PaymentMethodDao =
        database.paymentMethodDao()

    @Provides
    @Singleton
    fun providePaymentStatusDao(database: RocketDatabase): PaymentStatusDao =
        database.paymentStatusDao()

}