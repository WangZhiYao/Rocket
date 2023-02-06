package com.yizhenwind.rocket.feature.character.ui.composite

import androidx.annotation.StringRes

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
sealed class CharacterCompositeSideEffect {

    data class ShowError(@StringRes val resId: Int) : CharacterCompositeSideEffect()

}