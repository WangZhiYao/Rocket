package com.yizhenwind.rocket.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.yizhenwind.rocket.core.datastore.ext.rocketDataStore
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

}