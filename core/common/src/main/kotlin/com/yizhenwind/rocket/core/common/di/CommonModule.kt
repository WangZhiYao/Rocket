package com.yizhenwind.rocket.core.common.di

import com.squareup.moshi.Moshi
import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IOScope
import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.MainDispatcher
import com.yizhenwind.rocket.core.common.di.qualifier.formatter.MoneyFormatter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.text.DecimalFormat
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    @Singleton
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .build()

    @Provides
    @Singleton
    @IOScope
    fun provideIOScope(@IODispatcher dispatcher: CoroutineDispatcher): CoroutineScope =
        CoroutineScope(SupervisorJob() + dispatcher)

    @Provides
    @Singleton
    @MoneyFormatter
    fun provideMoneyFormatter(): DecimalFormat = DecimalFormat("0.00")

}