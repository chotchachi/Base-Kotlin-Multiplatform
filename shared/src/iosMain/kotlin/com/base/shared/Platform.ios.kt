package com.base.shared

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import platform.darwin.NSObject

actual typealias KMMContext = NSObject
actual interface CommonParcelable

actual fun setupNapier() {
    Napier.base(DebugAntilog())
}

actual class AESDecrypt {
    actual fun decrypt(
        textToDecrypt: String,
        secretKey: String,
        initializationVector: String
    ): ByteArray {
        TODO("Not yet implemented")
    }
}

actual fun isDebug(): Boolean = Platform.isDebugBinary
