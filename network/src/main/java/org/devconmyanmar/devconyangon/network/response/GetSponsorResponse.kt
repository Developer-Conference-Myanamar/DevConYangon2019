package org.devconmyanmar.devconyangon.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetSponsorResponse(
	@Json(name = "state") val state:Boolean,
	@Json(name = "status")
	val status:Int,
	@Json(name = "message")
	val message:String,
	@Json(name = "total_count")
	val totalCount:String,
	@Json(name = "sponser")
	val sponser: List<SponsorItem>
)





