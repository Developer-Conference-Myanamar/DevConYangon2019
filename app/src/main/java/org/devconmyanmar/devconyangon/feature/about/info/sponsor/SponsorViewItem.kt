package org.devconmyanmar.devconyangon.feature.about.info.sponsor

import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.Sponsor
import org.devconmyanmar.devconyangon.domain.model.SponsorId
import javax.inject.Inject

data class SponsorViewItem(
  val id: SponsorId,
  val name: String,
  val type: String,
  val sponsorLogo: String
)

class SponsorViewItemMapper @Inject constructor() : UnidirectionalMap<Sponsor, SponsorViewItem> {
  override fun map(item: Sponsor): SponsorViewItem {
    return SponsorViewItem(
      id = item.id,
      name = item.name,
      type = item.type,
      sponsorLogo = item.logo
    )
  }

}