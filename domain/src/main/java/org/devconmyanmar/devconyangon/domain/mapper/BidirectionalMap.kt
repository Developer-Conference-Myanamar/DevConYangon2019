package org.devconmyanmar.devconyangon.domain.mapper

/**
 * Created by Vincent on 2019-06-16
 */
interface BidirectionalMap<F, T> {

  fun map(item: F): T

  fun reverseMap(item: T): F

}
