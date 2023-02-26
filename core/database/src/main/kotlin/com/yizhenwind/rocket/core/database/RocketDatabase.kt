package com.yizhenwind.rocket.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yizhenwind.rocket.core.database.dao.*
import com.yizhenwind.rocket.core.database.entity.*

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/20
 */
@Database(
    entities = [
        AccountEntity::class,
        CategoryEntity::class,
        CharacterEntity::class,
        ClientEntity::class,
        InternalEntity::class,
        OrderEntity::class,
        SectEntity::class,
        ServerEntity::class,
        SubjectEntity::class,
        ZoneEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RocketDatabase : RoomDatabase() {

    abstract fun clientDao(): ClientDao

    abstract fun accountDao(): AccountDao

    abstract fun characterDao(): CharacterDao

    abstract fun zoneDao(): ZoneDao

    abstract fun serverDao(): ServerDao

    abstract fun sectDao(): SectDao

    abstract fun internalDao(): InternalDao

    abstract fun categoryDao(): CategoryDao

    abstract fun subjectDao(): SubjectDao

    abstract fun orderDao(): OrderDao

}