package org.devconmyanmar.devconyangon.network.exception

import okhttp3.ResponseBody
import java.io.IOException

/**
 * Created by Vincent on 2019-09-10
 */
data class NetworkException constructor(
  val errorBody: ResponseBody? = null,
  var errorCode: Int = 0
) : IOException()
