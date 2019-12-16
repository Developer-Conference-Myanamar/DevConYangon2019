package org.devconmyanmar.devconyangon.feature.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import org.devconmyanmar.devconyangon.domain.usecase.SyncData
import javax.inject.Inject
import javax.inject.Provider

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

class DaggerWorkerFactory @Inject constructor(
  private val workerFactories: Map<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>
) : WorkerFactory() {

  override fun createWorker(
    appContext: Context,
    workerClassName: String,
    workerParameters: WorkerParameters
  ): ListenableWorker? {
    val foundEntry =
      workerFactories.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
    val factoryProvider = foundEntry?.value
      ?: throw IllegalArgumentException("unknown worker class name: $workerClassName")
    return factoryProvider.get().create(appContext, workerParameters)
  }
}

interface ChildWorkerFactory {
  fun create(context: Context, workerParams: WorkerParameters): ListenableWorker
}
