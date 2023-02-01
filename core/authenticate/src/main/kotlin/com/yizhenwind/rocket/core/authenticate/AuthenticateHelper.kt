package com.yizhenwind.rocket.core.authenticate

import android.content.Context
import android.os.Build
import androidx.biometric.BiometricManager

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/27
 */
object AuthenticateHelper {

    fun hasBiometricCapability(context: Context): Boolean {
        val authenticators = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
        } else {
            BiometricManager.Authenticators.BIOMETRIC_WEAK or BiometricManager.Authenticators.DEVICE_CREDENTIAL
        }
        return BiometricManager.from(context)
            .canAuthenticate(authenticators) == BiometricManager.BIOMETRIC_SUCCESS
    }

}