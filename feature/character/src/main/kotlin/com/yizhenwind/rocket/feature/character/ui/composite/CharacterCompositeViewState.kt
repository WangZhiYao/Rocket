package com.yizhenwind.rocket.feature.character.ui.composite

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.Character

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
data class CharacterCompositeViewState(val character: Character = Character()) : IViewState