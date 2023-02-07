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
        ClientEntity::class,
        ContactTypeEntity::class,
        AccountEntity::class,
        CharacterEntity::class,
        ZoneEntity::class,
        ServerEntity::class,
        SectEntity::class,
        InternalEntity::class,
        CategoryEntity::class,
        SubjectEntity::class,
        OrderEntity::class,
        PaymentMethodEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RocketDatabase : RoomDatabase() {

    abstract fun clientDao(): ClientDao

    abstract fun contactTypeDao(): ContactTypeDao

    abstract fun accountDao(): AccountDao

    abstract fun characterDao(): CharacterDao

    abstract fun zoneDao(): ZoneDao

    abstract fun serverDao(): ServerDao

    abstract fun sectDao(): SectDao

    abstract fun internalDao(): InternalDao

    abstract fun categoryDao(): CategoryDao

    abstract fun subjectDao(): SubjectDao

    abstract fun orderDao(): OrderDao

    abstract fun paymentMethodDao(): PaymentMethodDao

}