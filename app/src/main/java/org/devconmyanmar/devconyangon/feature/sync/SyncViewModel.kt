package org.devconmyanmar.devconyangon.feature.sync

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.exception.ExceptionToStringMapper
import org.devconmyanmar.devconyangon.domain.usecase.RequireToSyncData
import org.devconmyanmar.devconyangon.domain.usecase.SyncData
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vincent on 12/15/19
 */
class SyncViewModel @Inject constructor(
  private val requireToSyncData: RequireToSyncData,
  private val syncData: SyncData,
  private val exceptionToStringMapper: ExceptionToStringMapper
) : BaseViewModel<SyncView>() {

  fun syncData() {
    viewModelScope.launch {
      if (requireToSyncData.execute(Unit)) {
        view?.showSyncing()

        val result = this.runCatching {
          syncData.execute(Unit)
        }

        if (result.isSuccess) {
          view?.navigateToHome()
        } else if (result.isFailure) {
          val exception = result.exceptionOrNull()
          Timber.e(exception)
          if (exception != null)
            view?.showError(exceptionToStringMapper.map(exception))
        }
      } else {
        view?.navigateToHome()
      }

    }
  }

}