package org.devconmyanmar.devconyangon.base.core.mvp

/**
 * Android contract for every MVP Presenter
 */
interface Presentable<V : Viewable> {

  /**
   * Every Presentable must attach a Viewable
   *
   * @param viewable Viewable
   */
  fun attachView(viewable: V)

  /**
   * Every Presentable must detach its Viewable
   */
  fun detachView()

}