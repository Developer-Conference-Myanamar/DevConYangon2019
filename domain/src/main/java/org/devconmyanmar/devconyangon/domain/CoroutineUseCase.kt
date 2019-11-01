package org.devconmyanmar.devconyangon.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Vincent on 2019-06-20
 */
abstract class CoroutineUseCase<I, O> {

  suspend fun execute(params: I): O {
    return withContext(Dispatchers.IO) {
      provide(params)
    }
  }

  protected abstract suspend fun provide(params: I): O

}