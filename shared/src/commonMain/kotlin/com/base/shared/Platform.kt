package com.base.shared

expect class KMMContext

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class CommonParcelize()

expect interface CommonParcelable

expect fun setupNapier()

expect class AESDecrypt {
    fun decrypt(textToDecrypt: String, secretKey: String, initializationVector: String): ByteArray
}

expect fun isDebug(): Boolean
