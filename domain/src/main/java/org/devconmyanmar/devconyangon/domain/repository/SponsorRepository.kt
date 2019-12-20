package org.devconmyanmar.devconyangon.domain.repository

import org.devconmyanmar.devconyangon.domain.model.Sponsor

interface SponsorRepository{
    suspend fun getSponsorList():List<Sponsor>
}