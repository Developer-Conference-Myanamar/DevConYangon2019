package org.devconmyanmar.devconyangon.domain.exception

import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap

/**
 * Created by Vincent on 11/27/19
 */
interface ExceptionToStringMapper : UnidirectionalMap<Throwable, String> {

}