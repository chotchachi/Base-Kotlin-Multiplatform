package com.base.shared.domain.model

import com.base.shared.CommonParcelable
import com.base.shared.CommonParcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@CommonParcelize
@Serializable
data class Coin (
    @SerialName("_id") val id: String,
    @SerialName("denomination") val denomination: String? = null,
    @SerialName("metal") val metal: String? = null,
    @SerialName("state") val state: String? = null,
    @SerialName("issueyears") val issueYears: String? = null,
    @SerialName("person") val person: String? = null,
    @SerialName("description") val description: List<String> = emptyList(),
    @SerialName("image") val image: String? = null,
    @SerialName("name") val name: String,
    @SerialName("coinUrl") val coinUrl: String? = null,
    @SerialName("coinIndex") val coinIndex: String? = null,
    @SerialName("countryId") val countryId: String? = null
) : CommonParcelable
