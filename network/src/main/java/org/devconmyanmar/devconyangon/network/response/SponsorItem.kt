package org.devconmyanmar.devconyangon.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SponsorItem(
    @Json(name = "sponsor_title")  val sponsorTitle: String?,
    @Json(name = "sponser_type") val sponserType: String,
    @Json(name = "updated_at") val updatedAt: String?,
    @Json(name = "created_at") val createdAt: String?,
    @Json(name = "id") val id: Int?,
    @Json(name = "sponsor_logo") val sponsorLogo: String?,
    @Json(name = "sponsor_name") val sponsorId: String?,
    @Json(name = "sponsor_detail") val sponsorDetail: String?

)