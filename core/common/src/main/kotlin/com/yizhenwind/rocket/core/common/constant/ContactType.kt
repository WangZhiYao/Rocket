package com.yizhenwind.rocket.core.common.constant

/**
 * 联系方式类型
 *
 * @author WangZhiYao
 * @since 2022/3/18
 */
enum class ContactType(val index: Int) {

    QQ(0),

    WECHAT(1),

    PHONE(2);

    companion object {

        fun getByIndex(index: Int): ContactType? = values().firstOrNull { it.index == index }
    }
}