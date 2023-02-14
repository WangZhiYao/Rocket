package com.yizhenwind.rocket.core.authenticate.usecase

import androidx.biometric.BiometricPrompt
import com.yizhenwind.rocket.core.authenticate.CryptographyManager
import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/31
 */
class BiometricDecryptUseCase @Inject constructor(
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(
        authResult: BiometricPrompt.AuthenticationResult,
        ciphertext: String
    ): Flow<String> =
        flowOf(authResult.cryptoObject)
            .filterNotNull()
            .map { cryptoObject ->
                cryptoObject.cipher
            }
            .filterNotNull()
            .map { cipher ->
                CryptographyManager.decryptData(ciphertext, cipher)
            }
            .flowOn(dispatcher)

}