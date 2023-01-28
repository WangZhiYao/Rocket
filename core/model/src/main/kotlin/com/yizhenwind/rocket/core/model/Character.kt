package com.yizhenwind.rocket.core.model

/**
 * 角色
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
data class Character(
    val id: Long = 0,
    val client: Client = Client(),
    val zone: Zone = Zone(),
    val server: Server = Server(),
    val account: Account = Account(),
    val securityLock: String? = null,
    val name: String = "",
    val sect: Sect = Sect(),
    val internal: Internal = Internal(),
    val remark: String? = null,
    val enable: Boolean = true,
    val createTime: Long = System.currentTimeMillis()
)