package com.yizhenwind.rocket.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.yizhenwind.rocket.core.datastore.DataStoreManager
import com.yizhenwind.rocket.core.datastore.ext.rocketDataStore
import com.yizhenwind.rocket.core.infra.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideRocketDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        appContext.rocketDataStore

    @Provides
    @Singleton
    fun provideDataStoreManager(
        logger: Logger,
        dataStore: DataStore<Preferences>
    ): DataStoreManager =
        DataStoreManager(logger, dataStore)

}