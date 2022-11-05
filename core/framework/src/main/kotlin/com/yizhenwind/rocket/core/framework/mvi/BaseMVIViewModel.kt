package com.yizhenwind.rocket.core.framework.mvi

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/22
 */
abstract class BaseMVIViewModel<STATE : ViewState, SIDE_EFFECT : SideEffect> :
    ContainerHost<STATE, SIDE_EFFECT>, ViewModel()