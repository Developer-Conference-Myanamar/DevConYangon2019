package org.devconmyanmar.devconyangon.data.entity

import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.Sponsor
import org.devconmyanmar.devconyangon.domain.model.SponsorId
import javax.inject.Inject

data class SponsorEntity(
    val sponsorTitle: String,
    val sponserType: String,
    val updatedAt: String,
    val createdAt: String,
    val id: SponsorId,
    val sponsorLogo: String,
    val sponsorName: String,
    val sponsorDetail: String
)

class SponsorEntityMapper @Inject constructor() : UnidirectionalMap<SponsorEntity, Sponsor> {
    override fun map(item: SponsorEntity): Sponsor {
        return Sponsor(
            id = item.id,
            sponsorTitle = item.sponsorTitle,
            sponserType = item.sponserType,
            updatedAt = item.updatedAt,
            createdAt = item.createdAt,
            sponsorLogo = item.sponsorLogo,
            sponsorName = item.sponsorName,
            sponsorDetail = item.sponsorDetail
        )
    }

}