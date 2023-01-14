package com.yizhenwind.rocket.core.framework.base

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import org.orbitmvi.orbit.ContainerHost

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/22
 */
abstract class BaseMVIViewModel<STATE : IViewState, SIDE_EFFECT : Any> :
    ContainerHost<STATE, SIDE_EFFECT>, BaseViewModel()