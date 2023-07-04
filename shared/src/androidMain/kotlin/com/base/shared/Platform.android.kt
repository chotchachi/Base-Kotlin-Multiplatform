package com.base.shared

import android.app.Application
import android.os.Parcelable
import android.util.Base64
import com.base.kmm.shared.BuildConfig
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.parcelize.Parcelize
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

actual typealias KMMContext = Application
actual typealias CommonParcelize = Parcelize
actual typealias CommonParcelable = Parcelable

actual fun setupNapier() {
    if (BuildConfig.DEBUG) {
        Napier.base(DebugAntilog())
    }
}

actual class AESDecrypt {
    actual fun decrypt(
        textToDecrypt: String,
        secretKey: String,
        initializationVector: String
    ): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")

        val ivSpec = IvParameterSpec(initializationVector.toByteArray())
        val secretKeySpec = SecretKeySpec(secretKey.toByteArray(), "AES")

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec)
        val cipherText = cipher.doFinal(Base64.decode(textToDecrypt, Base64.DEFAULT))

        val sb = StringBuilder()
        for (b in cipherText) {
            sb.append(b.toInt().toChar())
        }
        return cipherText
    }

}

actual fun isDebug(): Boolean = BuildConfig.DEBUG
