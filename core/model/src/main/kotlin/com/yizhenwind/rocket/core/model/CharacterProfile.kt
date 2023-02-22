package com.yizhenwind.rocket.core.model

import androidx.recyclerview.widget.DiffUtil

/**
 *
 * @author WangZhiYao
 * @since 2023/1/20
 */
data class CharacterProfile(
    val id: Long = 0,
    val zone: Zone = Zone(),
    val server: Server = Server(),
    val name: String = "",
    val sect: Sect = Sect(),
    val remark: String = "",
    val createTime: Long = System.currentTimeMillis()
) {

    companion object {

        val COMPARATOR =
            object : DiffUtil.ItemCallback<CharacterProfile>() {

                override fun areItemsTheSame(
                    oldItem: CharacterProfile,
                    newItem: CharacterProfile
                ): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: CharacterProfile,
                    newItem: CharacterProfile
                ): Boolean =
                    oldItem == newItem

            }
    }

}