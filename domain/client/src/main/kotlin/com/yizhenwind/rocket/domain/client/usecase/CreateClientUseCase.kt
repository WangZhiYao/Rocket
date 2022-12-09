package com.yizhenwind.rocket.domain.client.usecase

import com.yizhenwind.rocket.core.common.model.Client
import com.yizhenwind.rocket.domain.client.repository.ClientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/7
 */
class CreateClientUseCase @Inject constructor(
    private val clientRepository: ClientRepository
) {

    operator fun invoke(
        name: String,
        remark: String?
    ): Flow<Client> =
        clientRepository.createClient(name, remark)

}