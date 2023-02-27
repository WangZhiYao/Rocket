package com.yizhenwind.rocket.core.model

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/26
 */
data class CategorySubject(
    val category: Category = Category(),
    val subjectList: List<Subject> = emptyList()
)