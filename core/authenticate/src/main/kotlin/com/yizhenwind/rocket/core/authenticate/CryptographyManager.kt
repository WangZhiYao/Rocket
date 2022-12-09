package com.yizhenwind.rocket.core.authenticate

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/27
 */
class CryptographyManager @Inject constructor() {

    private val cipher =
        Cipher.getInstance("$ENCRYPTION_ALGORITHM/$ENCRYPTION_BLOCK_MODE/$ENCRYPTION_PADDING")
    private var ivParameterSpec: IvParameterSpec? = null

    fun getEncryptCipher(keyName: String): Cipher {
        cipher.init(Cipher.ENCRYPT_MODE, getOrGenerateSecretKey(keyName))
        ivParameterSpec = cipher.parameters.getParameterSpec(IvParameterSpec::class.java)
        return cipher
    }

    fun getDecryptCipher(keyName: String): Cipher? {
        ivParameterSpec?.let { it ->
            cipher.init(Cipher.DECRYPT_MODE, getOrGenerateSecretKey(keyName), it)
            return cipher
        }
        return null
    }

    private fun getOrGenerateSecretKey(keyName: String): SecretKey {
        try {
            val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
            keyStore.load(null)
            keyStore.getKey(keyName, null)?.let {
                return it as SecretKey
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            keyName,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).run {
            setBlockModes(ENCRYPTION_BLOCK_MODE)
            setEncryptionPaddings(ENCRYPTION_PADDING)
            setUserAuthenticationRequired(true)
            setInvalidatedByBiometricEnrollment(false)
            build()
        }
        val keyGenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM, ANDROID_KEYSTORE)
        keyGenerator.init(keyGenParameterSpec)
        return keyGenerator.generateKey()
    }

    companion object {

        private const val ANDROID_KEYSTORE = "AndroidKeyStore"

        private const val ENCRYPTION_BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val ENCRYPTION_PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val ENCRYPTION_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES

    }
}