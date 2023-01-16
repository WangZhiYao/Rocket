package com.yizhenwind.rocket.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * 代练项目
 *
 * @author WangZhiYao
 * @since 2021/10/27
 */
@Entity(
    tableName = "subject",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class SubjectEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    /**
     * 代练类型
     */
    @ColumnInfo(name = "category_id", index = true)
    val categoryId: Long,

    /**
     * 代练内容
     */
    val content: String,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long = System.currentTimeMillis()
)