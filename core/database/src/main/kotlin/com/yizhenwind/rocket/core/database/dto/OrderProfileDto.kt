package com.yizhenwind.rocket.core.database.dto

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.yizhenwind.rocket.core.common.constant.OrderStatus
import com.yizhenwind.rocket.core.common.constant.PaymentStatus
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
    @ColumnInfo(name = "order_status")
    val orderStatus: OrderStatus,
    @ColumnInfo(name = "payment_status")
    val paymentStatus: PaymentStatus,
    @ColumnInfo(name = "create_time")
    val createTime: Long
)