package com.yizhenwind.rocket.core.framework.mvi

import com.yizhenwind.rocket.core.framework.base.BaseViewModel
import org.orbitmvi.orbit.ContainerHost

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/22
 */
abstract class BaseMVIViewModel<STATE : IViewState, SIDE_EFFECT : ISideEffect> :
    ContainerHost<STATE, SIDE_EFFECT>, BaseViewModel()