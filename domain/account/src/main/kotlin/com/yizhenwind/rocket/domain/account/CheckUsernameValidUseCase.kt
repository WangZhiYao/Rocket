package com.yizhenwind.rocket.domain.account

import com.yizhenwind.rocket.core.common.constant.RegexRule
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/1
 */
class CheckUsernameValidUseCase @Inject constructor() {

    operator fun invoke(username: String): Boolean = username.run {
        matches(RegexRule.EMAIL) || matches(RegexRule.PHONE_NUMBER) || matches(RegexRule.CUSTOM_ACCOUNT)
    }

}