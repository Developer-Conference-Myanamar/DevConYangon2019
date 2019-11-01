package org.devconmyanmar.devconyangon.base.core.mvp

/**
 * Created by Vincent on 1/29/19
 */
class InvalidMvpImplementationException : Throwable() {

  override val message: String?
    get() = "MVP implementation is not correctly implemented"

}