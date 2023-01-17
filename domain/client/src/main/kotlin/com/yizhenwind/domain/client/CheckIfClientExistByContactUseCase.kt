package com.yizhenwind.domain.client

import com.yizhenwind.rocket.core.common.constant.ContactType
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/17
 */
class CheckIfClientExistByContactUseCase @Inject constructor(
    private val getClientByContactUseCase: GetClientByContactUseCase
) {

    suspend operator fun invoke(contactType: ContactType, contact: String): Boolean =
        getClientByContactUseCase(contactType, contact) != null

}