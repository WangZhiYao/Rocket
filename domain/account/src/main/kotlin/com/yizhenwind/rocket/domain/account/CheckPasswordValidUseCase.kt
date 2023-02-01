package com.yizhenwind.rocket.domain.account

import com.yizhenwind.rocket.core.common.constant.RegexRule
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/1
 */
class CheckPasswordValidUseCase @Inject constructor() {

    operator fun invoke(password: String): Boolean = password.matches(RegexRule.PASSWORD)

}