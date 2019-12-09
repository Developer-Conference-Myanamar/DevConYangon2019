package org.devconmyanmar.devconyangon.feature.schedule.sessiondetail

import android.util.Log
import org.devconmyanmar.devconyangon.domain.helper.asDelimitedString
import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SessionListing
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.feature.schedule.session.SessionViewItem
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

data class SessionDetailItem(
    val sessionId: SessionId,
    val sessionTitle: String,
    val timeInMilliSecond:Long,
    val timeInString: String,
    val amPmOfTime: String,
    val roomName: String,
    val speakers: List<Speaker>,
    val speakerNames: String,
    val shouldShowTime: Boolean,
    val isFavorite: Boolean
)

class SessionViewItemMapper @Inject constructor() :
    UnidirectionalMap<SessionListing, SessionDetailItem> {


    /**
     * This set controls whether shouldShowTime has already set to true
     * Generally we want first item among all others item with same time to be set to true
     */
    private val timeSet = mutableSetOf<LocalTime>()
    private var dateFormatter = DateTimeFormatter.ofPattern("MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
    private val timeFormatter = DateTimeFormatter.ofPattern("hh:mm", Locale.ENGLISH)
    private val amPmFormatter = DateTimeFormatter.ofPattern("a", Locale.ENGLISH)


    override fun map(item: SessionListing): SessionDetailItem {
        val sessionTime = item.dateTime.toLocalTime()
        val shouldShowTime = timeSet.add(sessionTime)

        val date=item.dateTime.format(dateFormatter)
        val timeInMilliseconds =item.dateTime.toInstant().toEpochMilli()
        Log.e("timeMilli",timeInMilliseconds.toString())

        return SessionDetailItem(
            sessionId = item.sessionId,
            sessionTitle = item.sessionTitle,
            timeInMilliSecond = timeInMilliseconds,
            timeInString = sessionTime.format(timeFormatter),
            amPmOfTime = sessionTime.format(amPmFormatter),
            shouldShowTime = shouldShowTime,
            speakers = item.speakers,
            roomName = item.room.roomName,
            speakerNames = item.speakers.map { it.name }.asDelimitedString(','),
            isFavorite = item.isFavorite
        )
    }

}
