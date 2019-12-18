package org.devconmyanmar.devconyangon.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetSponsorResponse(
	@Json(name = "sponser")
	val sponsor: List<SponsorItem>
)

@JsonClass(generateAdapter = true)
data class SponsorItem(
	@Json(name = "sponsor_title")
	val sponsorTitle: String,
	@Json(name = "sponser_type")
	val sponserType: String,
	@Json(name = "updated_at")
	val updatedAt: String,
	@Json(name = "created_at")
	val createdAt: String,
	@Json(name = "id")
	val id: Int,
	val sponsorLogo: String,
	@Json(name = "sponser_id")
	val sponsorId: String,
	@Json(name = "sponsor_detail")
	val sponsorDetail: String
)



