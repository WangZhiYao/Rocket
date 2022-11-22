package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import com.yizhenwind.rocket.core.database.entity.CharacterEntity

/**
 * 角色表操作
 *
 * @author WangZhiYao
 * @since 2021/10/27
 */
@Dao
interface CharacterDao : IDao<CharacterEntity>