package com.yizhenwind.rocket.core.database.dto

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.OrderStatusEntity
import com.yizhenwind.rocket.core.database.entity.PaymentStatusEntity
import com.yizhenwind.rocket.core.database.entity.SubjectEntity

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
data class OrderProfileDto(
    val id: Long,
    @ColumnInfo(name = "subject_id")
    val subjectId: Long,
    @Relation(
        entity = SubjectEntity::class,
        parentColumn = "subject_id",
        entityColumn = "id",
    )
    val subject: SubjectDto,
    val remark: String,
    @ColumnInfo(name = "order_status_id")
    val orderStatusId: Long,
    @Relation(
        parentColumn = "order_status_id",
        entityColumn = "id",
    )
    val orderStatus: OrderStatusEntity,
    @ColumnInfo(name = "payment_status_id")
    val paymentStatusId: Long,
    @Relation(
        parentColumn = "payment_status_id",
        entityColumn = "id",
    )
    val paymentStatus: PaymentStatusEntity,
    @ColumnInfo(name = "create_time")
    val createTime: Long
)