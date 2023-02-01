package com.yizhenwind.rocket.core.authenticate

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.yizhenwind.rocket.core.authenticate.model.CipherData
import com.yizhenwind.rocket.core.common.ext.hexStringToByteArray
import com.yizhenwind.rocket.core.common.ext.toHex
import java.security.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/27
 */
object CryptographyManager : ICryptography {

    private const val ANDROID_KEYSTORE = "AndroidKeyStore"

    private const val ENCRYPTION_BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
    private const val ENCRYPTION_PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
    private const val ENCRYPTION_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES

    private val cipher =
        Cipher.getInstance("$ENCRYPTION_ALGORITHM/$ENCRYPTION_BLOCK_MODE/$ENCRYPTION_PADDING")

    override fun getEncryptionCipher(keyName: String): Cipher {
        return cipher.apply { init(Cipher.ENCRYPT_MODE, getOrGenerateSecretKey(keyName)) }
    }

    override fun getDecryptionCipher(keyName: String, iv: String): Cipher {
        return cipher.apply {
            init(
                Cipher.DECRYPT_MODE,
                getOrGenerateSecretKey(keyName),
                IvParameterSpec(iv.hexStringToByteArray())
            )
        }
    }

    override fun encryptData(plaintext: String, cipher: Cipher): CipherData {
        val ciphertext = cipher.doFinal(plaintext.toByteArray())
        return CipherData(ciphertext.toHex(), cipher.iv.toHex())
    }

    override fun decryptData(ciphertext: String, cipher: Cipher): String {
        return cipher.doFinal(ciphertext.hexStringToByteArray()).decodeToString()
    }

    private fun getOrGenerateSecretKey(keyName: String): SecretKey {
        return getSecretKey(keyName) ?: generateSecretKey(keyName)
    }

    @Throws(
        KeyStoreException::class,
        NoSuchAlgorithmException::class,
        UnrecoverableKeyException::class
    )
    private fun getSecretKey(keyName: String): SecretKey? {
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
        keyStore.load(null)
        return keyStore.getKey(keyName, null)?.run { this as SecretKey }
    }

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchProviderException::class,
        InvalidAlgorithmParameterException::class,
        RuntimeException::class
    )
    private fun generateSecretKey(keyName: String): SecretKey {
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            keyName,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(ENCRYPTION_BLOCK_MODE)
            .setEncryptionPaddings(ENCRYPTION_PADDING)
            .setUserAuthenticationRequired(true)
            .setInvalidatedByBiometricEnrollment(false)
            .build()

        val keyGenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM, ANDROID_KEYSTORE)
        keyGenerator.init(keyGenParameterSpec)
        return keyGenerator.generateKey()
    }

}