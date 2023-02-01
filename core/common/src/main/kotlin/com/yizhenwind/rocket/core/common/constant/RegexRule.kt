package com.yizhenwind.rocket.core.common.constant

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/1
 */
object RegexRule {

    /**
     * 自定义账号
     */
    val CUSTOM_ACCOUNT = "^[a-zA-Z]\\w{3,17}\$".toRegex()

    /**
     * 邮箱
     */
    val EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$".toRegex()

    /**
     * 手机号
     */
    val PHONE_NUMBER = "^(1)[3-9]\\d{9}+$".toRegex()

    /**
     * 密码
     */
    val PASSWORD =
        "^(?![a-z]+\$)(?![A-Z]+\$)(?![0-9]+\$)(?![_!@#\$%^&*`~()-+=]+\$)[a-zA-Z0-9W_!@#\$%^&*`~()-+.<>:;\"|{}?=]{8,32}\$".toRegex()

    /**
     * 角色名
     */
    val CHARACTER_NAME = "^[\\u4E00-\\u9FA5@1-9]+$".toRegex()
}