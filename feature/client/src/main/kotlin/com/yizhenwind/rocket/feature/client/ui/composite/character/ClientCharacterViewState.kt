package com.yizhenwind.rocket.feature.client.ui.composite.character

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.CharacterProfile

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
data class ClientCharacterViewState(
    val characterProfileList: List<CharacterProfile> = emptyList()
) : IViewState