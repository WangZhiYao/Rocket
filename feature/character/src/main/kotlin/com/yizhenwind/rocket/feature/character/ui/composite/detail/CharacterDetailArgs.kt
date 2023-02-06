package com.yizhenwind.rocket.feature.character.ui.composite.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.rocket.core.framework.base.IFragmentArgs
import com.yizhenwind.rocket.core.framework.base.IFragmentArgsDeserializer

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
data class CharacterDetailArgs(val characterId: Long) : IFragmentArgs {

    override fun newInstance(): Fragment =
        CharacterDetailFragment().apply {
            arguments = Bundle().apply {
                putLong(KEY_CHARACTER_ID, characterId)
            }
        }

    companion object : IFragmentArgsDeserializer<CharacterDetailArgs> {

        private const val KEY_CHARACTER_ID = "characterId"

        @JvmStatic
        override fun deserialize(arguments: Bundle): CharacterDetailArgs =
            arguments.run { CharacterDetailArgs(getLong(KEY_CHARACTER_ID)) }

    }
}