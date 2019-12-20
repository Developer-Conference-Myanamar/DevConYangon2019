package org.devconmyanmar.devconyangon.domain.model

data class Sponsor(
  val id: SponsorId,
  val name: String,
  val title: String,
  val type: String,
  val logo: String,
  val detail: String
)

inline class SponsorId(val value: Long)