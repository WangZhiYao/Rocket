package com.yizhenwind.rocket.feature.client.ui.composite.character

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.Character

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/4
 */
data class ClientCharacterViewState(val characterList: List<Character> = emptyList()) : IViewState