package org.devconmyanmar.devconyangon.domain.model

import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.Sponsor
import javax.inject.Inject

data class Sponsor(
    val sponsorTitle: String,
    val sponserType: String,
    val updatedAt: String,
    val createdAt: String,
    val id: SponsorId,
    val sponsorLogo: String,
    val sponsorName: String,
    val sponsorDetail: String
)

inline class SponsorId(val value: Long)