package com.yizhenwind.rocket.core.authenticate.usecase

import androidx.biometric.BiometricPrompt
import com.yizhenwind.rocket.core.authenticate.CryptographyManager
import com.yizhenwind.rocket.core.authenticate.model.CipherData
import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/31
 */
class BiometricEncryptUseCase @Inject constructor(
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(
        authResult: BiometricPrompt.AuthenticationResult,
        plaintext: String
    ): Flow<CipherData> =
        flowOf(authResult.cryptoObject)
            .filterNotNull()
            .map { cryptoObject ->
                cryptoObject.cipher
            }
            .filterNotNull()
            .map { cipher ->
                CryptographyManager.encryptData(plaintext, cipher)
            }
            .flowOn(dispatcher)

}