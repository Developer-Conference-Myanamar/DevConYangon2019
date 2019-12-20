package org.devconmyanmar.devconyangon.feature.about.info.sponsor

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.base.helper.AsyncViewResource
import org.devconmyanmar.devconyangon.domain.usecase.GetSponsors
import timber.log.Timber
import javax.inject.Inject

class SponsorViewModel @Inject constructor(
  private val getSponsors: GetSponsors,
  private val sponsorViewItemMapper: SponsorViewItemMapper
) : BaseViewModel<SponsorView>() {

  private val sponsorListLiveData = MutableLiveData<AsyncViewResource<List<SponsorViewItem>>>()

  override fun attachView(viewable: SponsorView) {
    super.attachView(viewable)
    view?.showSponsorListOnScreen(sponsorListLiveData)
  }

  fun loadSponsors() {
    scope.launch {
      sponsorListLiveData.postValue(AsyncViewResource.Loading())

      try {
        val sponsor = getSponsors.execute(
          GetSponsors.Params(0L)
        )
          .map(sponsorViewItemMapper::map)
        sponsorListLiveData.postValue(AsyncViewResource.Success(sponsor))
      } catch (exception: Exception) {
        Timber.e(exception)
        sponsorListLiveData.postValue(AsyncViewResource.Error(exception))
      }
    }
  }
}