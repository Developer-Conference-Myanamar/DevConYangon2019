package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.notification.SessionNotifier
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

/**
 * Created by Vincent on 2019-11-02
 */
class ToggleSessionFavorite @Inject constructor(
  private val sessionRepository: SessionRepository,
  private val sessionNotifier: SessionNotifier
) :
  CoroutineUseCase<ToggleSessionFavorite.Params, Unit>() {

  override suspend fun provide(params: Params): Unit {
    sessionRepository.toggleFavoriteStatus(params.sessionId)

    val session = sessionRepository.getSession(params.sessionId)

    if (session.isFavorite) {
//      val notificationDateTime = session.date.atTime(session.startTime).minusMinutes(10)
      val notificationDateTime = LocalDateTime.now().plusSeconds(5)

      sessionNotifier.addSessionNotification(session, notificationDateTime)
    } else {
      sessionNotifier.removeSessionNotification(session)
    }
  }

  data class Params(
    val sessionId: SessionId
  )
}