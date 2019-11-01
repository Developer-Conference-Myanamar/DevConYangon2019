package org.devconmyanmar.devconyangon.domain.mapper

/**
 * Created by Vincent on 2019-06-16
 */
interface UnidirectionalMap<F, T> {

  fun map(item: F): T
}

