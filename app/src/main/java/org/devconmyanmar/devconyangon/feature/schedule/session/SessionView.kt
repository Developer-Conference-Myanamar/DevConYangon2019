package org.devconmyanmar.devconyangon.feature.schedule.session

import androidx.lifecycle.LiveData
import org.devconmyanmar.devconyangon.base.core.mvp.Viewable

/**
 * Created by Vincent on 2019-11-02
 */
interface SessionView : Viewable {
  fun subscribeToSessionList(sessionListLiveData: LiveData<List<SessionViewItem>>)
}
