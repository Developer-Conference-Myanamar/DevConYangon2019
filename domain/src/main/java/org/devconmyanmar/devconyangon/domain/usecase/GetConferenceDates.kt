package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.threeten.bp.LocalDate
import org.threeten.bp.Month
import javax.inject.Inject

/**
 * Created by Vincent on 2019-11-02
 */
class GetConferenceDates @Inject constructor() : CoroutineUseCase<Unit, List<LocalDate>>() {

  override suspend fun provide(params: Unit): List<LocalDate> {
    return listOf(
      LocalDate.of(2019, Month.DECEMBER, 21),
      LocalDate.of(2019, Month.DECEMBER, 22)
    )
  }

}