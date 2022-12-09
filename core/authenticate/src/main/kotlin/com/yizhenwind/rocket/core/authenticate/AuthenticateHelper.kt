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
class AuthenticateHelper {

    /*private fun hasBiometricCapability(context: Context): Int {
        val authenticators = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
        } else {
            BiometricManager.Authenticators.BIOMETRIC_WEAK or BiometricManager.Authenticators.DEVICE_CREDENTIAL
        }
        BiometricManager.from(context).run {
            when (canAuthenticate(authenticators)) {
                BiometricManager.BIOMETRIC_SUCCESS -> {
                    TODO()
                }
                BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                    TODO()
                }
                BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                    TODO()
                }
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                    TODO()
                }
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    TODO()
                }
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                    TODO()
                }
                BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                    TODO()
                }
                else -> {}
            }
        }
    }*/
}