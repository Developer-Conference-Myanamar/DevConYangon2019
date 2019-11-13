package org.devconmyanmar.devconyangon.base.core.mvp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.plus
import timber.log.Timber

/**
 * Created by Vincent on 12/6/18
 */
abstract class BaseViewModel<viewable : Viewable> : ViewModel(), Presentable<viewable> {

  protected var view: viewable? = null

  val loggingExceptionHandler = CoroutineExceptionHandler { _, t ->
    Timber.e(t)
  }
  protected open var scope = CoroutineScope(Dispatchers.Main) + loggingExceptionHandler

  override fun attachView(viewable: viewable) {
    scope.cancel()
    scope = CoroutineScope(Dispatchers.Main) + loggingExceptionHandler
    this.view = viewable
  }

  override fun detachView() {
    scope.cancel()
    this.view = null
  }

  override fun onCleared() {
    scope.cancel()
    super.onCleared()
  }

}