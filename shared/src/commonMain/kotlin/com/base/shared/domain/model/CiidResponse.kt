package com.base.shared.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CiidResponse(
    @SerialName("ciid") val ciid: String
)