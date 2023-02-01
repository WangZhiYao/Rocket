package com.yizhenwind.rocket.domain.account

import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.data.account.AccountRepository
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/1
 */
class GetAccountByUsernameUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke(username: String): Account? =
        accountRepository.getAccountByUsername(username)

}