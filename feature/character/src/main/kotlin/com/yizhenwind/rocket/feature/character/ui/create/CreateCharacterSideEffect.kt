package com.yizhenwind.rocket.feature.character.ui.create

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.model.Character

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
sealed class CreateCharacterSideEffect {

    data class ShowClientError(@StringRes val resId: Int) : CreateCharacterSideEffect()

    object HideClientError : CreateCharacterSideEffect()

    data class ShowZoneError(@StringRes val resId: Int) : CreateCharacterSideEffect()

    object HideZoneError : CreateCharacterSideEffect()

    data class ShowServerError(@StringRes val resId: Int) : CreateCharacterSideEffect()

    object HideServerError : CreateCharacterSideEffect()

    data class ShowAccountError(@StringRes val resId: Int) : CreateCharacterSideEffect()

    object HideAccountError : CreateCharacterSideEffect()

    data class ShowNameError(@StringRes val resId: Int) : CreateCharacterSideEffect()

    object HideNameError : CreateCharacterSideEffect()

    data class ShowSectError(@StringRes val resId: Int) : CreateCharacterSideEffect()

    object HideSectError : CreateCharacterSideEffect()

    data class ShowInternalError(@StringRes val resId: Int) : CreateCharacterSideEffect()

    object HideInternalError : CreateCharacterSideEffect()

    data class ShowSnack(@StringRes val resId: Int) : CreateCharacterSideEffect()

    data class CreateCharacterSuccess(val character: Character) : CreateCharacterSideEffect()

}