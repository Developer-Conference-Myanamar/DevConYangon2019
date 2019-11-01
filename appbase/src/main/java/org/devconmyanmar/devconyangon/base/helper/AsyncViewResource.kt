package org.devconmyanmar.devconyangon.base.helper

/**
 * Created by Vincent on 12/6/18
 */
sealed class AsyncViewResource<out T> {

  open operator fun invoke(): T? = null

  class Loading<out T> : AsyncViewResource<T>()
  data class Success<out T>(val value: T) : AsyncViewResource<T>()
  data class Error<out T>(val error: Throwable) : AsyncViewResource<T>()

}