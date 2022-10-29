package com.yizhenwind.rocket.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yizhenwind.rocket.core.database.dao.CustomerDao
import com.yizhenwind.rocket.core.database.entity.CustomerEntity

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/20
 */
@Database(
    entities = [
        CustomerEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RocketDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

}