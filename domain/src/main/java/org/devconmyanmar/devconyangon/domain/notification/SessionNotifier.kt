package org.devconmyanmar.devconyangon.domain.notification

import org.devconmyanmar.devconyangon.domain.model.Session
import org.threeten.bp.LocalDateTime

/**
 * Created by Vincent on 12/15/19
 */
interface SessionNotifier {

  fun addSessionNotification(session: Session, notificationDateTime: LocalDateTime)

  fun removeSessionNotification(session: Session)
}

