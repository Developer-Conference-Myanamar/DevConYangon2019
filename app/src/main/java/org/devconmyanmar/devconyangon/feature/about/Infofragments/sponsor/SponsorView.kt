package org.devconmyanmar.devconyangon.feature.about.Infofragments.sponsor

import androidx.lifecycle.LiveData
import org.devconmyanmar.devconyangon.base.core.mvp.Viewable
import org.devconmyanmar.devconyangon.base.helper.AsyncViewResource

interface SponsorView:Viewable {
    fun showSponsorListOnScreen(sponsorListLD:LiveData<AsyncViewResource<List<SponsorViewItem>>>)
}