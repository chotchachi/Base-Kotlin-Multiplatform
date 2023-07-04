package com.base.shared.presentation.scan

sealed class ScanIntent {
    object Retry : ScanIntent()
    data class GetImageID(val image: ByteArray) : ScanIntent() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as GetImageID

            if (!image.contentEquals(other.image)) return false

            return true
        }

        override fun hashCode(): Int {
            return image.contentHashCode()
        }
    }
    data class GetDetectResult(val firstImageId: String, val secondImageId: String) : ScanIntent()
}