package org.devconmyanmar.devconyangon.network

import org.devconmyanmar.devconyangon.network.response.GetScheduleResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Vincent on 12/16/19
 */
interface DevconYangonApi {

  @GET("schedules")
  fun getSchedules(): Call<GetScheduleResponse>
}