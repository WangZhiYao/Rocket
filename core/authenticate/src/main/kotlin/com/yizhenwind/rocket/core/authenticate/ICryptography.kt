package com.yizhenwind.rocket.core.authenticate

import com.yizhenwind.rocket.core.authenticate.model.CipherData
import javax.crypto.Cipher

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
interface ICryptography {

    /**
     * This method first gets or generates an instance of SecretKey and then initializes the Cipher
     * with the key. The secret key uses [ENCRYPT_MODE][Cipher.ENCRYPT_MODE] is used.
     */
    fun getEncryptionCipher(keyName: String): Cipher

    /**
     * This method first gets or generates an instance of SecretKey and then initializes the Cipher
     * with the key. The secret key uses [DECRYPT_MODE][Cipher.DECRYPT_MODE] is used.
     */
    fun getDecryptionCipher(keyName: String, iv: String): Cipher

    /**
     * The Cipher created with [getEncryptionCipher] is used here
     */
    fun encryptData(plaintext: String, cipher: Cipher): CipherData

    /**
     * The Cipher created with [getDecryptionCipher] is used here
     */
    fun decryptData(ciphertext: String, cipher: Cipher): String

}