package com.yizhenwind.rocket.core.authenticate.ext

import android.os.Build
import androidx.biometric.BiometricPrompt
import androidx.biometric.auth.authenticateWithClass3Biometrics
import androidx.biometric.auth.authenticateWithClass3BiometricsOrCredentials
import androidx.fragment.app.Fragment
import com.yizhenwind.rocket.core.authenticate.CryptographyManager
import com.yizhenwind.rocket.core.authenticate.R

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/31
 */
private suspend fun Fragment.authenticate(
    cryptoObject: BiometricPrompt.CryptoObject,
    title: CharSequence,
    negativeButtonText: CharSequence,
    subtitle: CharSequence? = null,
    description: CharSequence? = null,
    confirmationRequired: Boolean = true,
): BiometricPrompt.AuthenticationResult {

    val supportDeviceCredential = Build.VERSION.SDK_INT < Build.VERSION_CODES.P
            || Build.VERSION.SDK_INT > Build.VERSION_CODES.Q

    return if (supportDeviceCredential) {
        authenticateWithClass3BiometricsOrCredentials(
            cryptoObject,
            title,
            subtitle,
            description,
            confirmationRequired
        )
    } else {
        authenticateWithClass3Biometrics(
            cryptoObject,
            title,
            negativeButtonText,
            subtitle,
            description,
            confirmationRequired
        )
    }
}

suspend fun Fragment.authenticateForEncrypt(keyName: String = "Rocket"): BiometricPrompt.AuthenticationResult =
    authenticate(
        BiometricPrompt.CryptoObject(CryptographyManager.getEncryptionCipher(keyName)),
        getString(R.string.authentication_title_encrypt),
        getString(R.string.negative_button_text),
        getString(R.string.authentication_subtitle_encrypt)
    )
