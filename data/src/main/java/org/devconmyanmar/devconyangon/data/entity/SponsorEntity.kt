package org.devconmyanmar.devconyangon.data.entity

import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.Sponsor
import org.devconmyanmar.devconyangon.domain.model.SponsorId
import javax.inject.Inject

data class SponsorEntity(
  val id: SponsorId,
  val name: String,
  val title: String,
  val type: String,
  val logo: String,
  val detail: String
)

class SponsorEntityMapper @Inject constructor() : UnidirectionalMap<SponsorEntity, Sponsor> {
  override fun map(item: SponsorEntity): Sponsor {
    return Sponsor(
      id = item.id,
      name = item.name,
      title = item.title,
      type = item.type,
      logo = item.logo,
      detail = item.detail
    )
  }

}