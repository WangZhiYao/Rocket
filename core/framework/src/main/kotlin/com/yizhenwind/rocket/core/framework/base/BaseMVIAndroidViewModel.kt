package com.yizhenwind.rocket.core.framework.base

import android.app.Application
import com.yizhenwind.rocket.core.framework.mvi.IViewState
import org.orbitmvi.orbit.ContainerHost

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
abstract class BaseMVIAndroidViewModel<STATE : IViewState, SIDE_EFFECT : Any>(application: Application) :
    ContainerHost<STATE, SIDE_EFFECT>, BaseAndroidViewModel(application)