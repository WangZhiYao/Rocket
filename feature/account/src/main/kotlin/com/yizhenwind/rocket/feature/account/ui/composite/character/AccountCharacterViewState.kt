package com.yizhenwind.rocket.feature.account.ui.composite.character

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.CharacterProfile

/**
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
data class AccountCharacterViewState(
    val characterProfileList: List<CharacterProfile> = emptyList()
) : IViewState