package org.devconmyanmar.devconyangon.feature.sync

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.usecase.SyncData
import javax.inject.Inject

/**
 * Created by Vincent on 12/15/19
 */
class SyncViewModel @Inject constructor(
  private val syncData: SyncData
) :  BaseViewModel<SyncView>() {


  fun syncData() {
    viewModelScope.launch {
      val result = this.runCatching {
        syncData.execute(Unit)
      }

      if (result.isSuccess) {
        view?.navigateToHome()
      } else if (result.isFailure) {
        val exception = result.exceptionOrNull()
      }

    }
  }

}