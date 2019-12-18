package org.devconmyanmar.devconyangon.feature.about.Infofragments.sponsor

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.base.helper.AsyncViewResource
import org.devconmyanmar.devconyangon.domain.model.SponsorId
import org.devconmyanmar.devconyangon.domain.usecase.GetSponsors
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class SponsorViewModel @Inject constructor(
    private val getSponsors: GetSponsors,
    private val sponsorViewItemMapper: SponsorViewItemMapper
):BaseViewModel<SponsorView>() {

    val sponsorListLD = MutableLiveData<AsyncViewResource<List<SponsorViewItem>>>()
    override fun attachView(viewable: SponsorView) {
        super.attachView(viewable)
        view?.showSponsorListOnScreen(sponsorListLD)
    }

    fun loadSponsors(){
        scope.launch {
            try{
                val sponsor=getSponsors.execute(
                    SponsorId(0)).map(sponsorViewItemMapper::map)
                sponsorListLD.postValue(AsyncViewResource.Success(sponsor))
            }catch (exception:Exception){
                Timber.e(exception)
            }
        }
    }
}