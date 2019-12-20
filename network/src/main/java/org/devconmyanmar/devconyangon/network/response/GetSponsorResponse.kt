package org.devconmyanmar.devconyangon.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetSponsorResponse(
  @Json(name = "sponser") val sponsorList: List<GetSponsorResponseSponsor>
)

@JsonClass(generateAdapter = true)
data class GetSponsorResponseSponsor(
  @Json(name = "id") val id: Long,
  @Json(name = "sponsor_title") val sponsorTitle: String,
  @Json(name = "sponser_type") val sponsorType: String,
  @Json(name = "sponsor_logo") val sponsorLogo: String,
  @Json(name = "sponsor_name") val sponsorName: String,
  @Json(name = "sponsor_detail") val sponsorDetail: String
)