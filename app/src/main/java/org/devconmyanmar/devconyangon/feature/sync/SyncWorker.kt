package org.devconmyanmar.devconyangon.feature.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import org.devconmyanmar.devconyangon.di.ChildWorkerFactory
import org.devconmyanmar.devconyangon.domain.usecase.SyncData

/**
 * Created by Vincent on 12/17/19
 */
class SyncWorker @AssistedInject constructor(
  @Assisted private val context: Context,
  @Assisted private val workerParams: WorkerParameters,
  private val syncData: SyncData
) : CoroutineWorker(context, workerParams) {

  companion object {
    val NAME = "SYNC_WORKER"
  }

  override suspend fun doWork(): Result {

    val result = kotlin.runCatching {
      syncData.execute(Unit)
    }

    return if (result.isSuccess) {
      Result.success()
    } else {
      Result.retry()
    }
  }

  @AssistedInject.Factory
  interface Factory : ChildWorkerFactory

}
