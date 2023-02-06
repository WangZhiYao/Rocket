package com.yizhenwind.rocket.feature.character.ui.composite.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.rocket.core.framework.base.IFragmentArgs
import com.yizhenwind.rocket.core.framework.base.IFragmentArgsDeserializer

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
data class CharacterOrderArgs(val characterId: Long) : IFragmentArgs {

    override fun newInstance(): Fragment =
        CharacterOrderFragment().apply {
            arguments = Bundle().apply {
                putLong(KEY_CHARACTER_ID, characterId)
            }
        }

    companion object : IFragmentArgsDeserializer<CharacterOrderArgs> {

        private const val KEY_CHARACTER_ID = "characterId"

        @JvmStatic
        override fun deserialize(arguments: Bundle): CharacterOrderArgs =
            arguments.run { CharacterOrderArgs(getLong(KEY_CHARACTER_ID)) }

    }
}