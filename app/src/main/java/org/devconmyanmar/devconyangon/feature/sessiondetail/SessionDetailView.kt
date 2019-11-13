package org.devconmyanmar.devconyangon.feature.sessiondetail

import androidx.lifecycle.LiveData
import org.devconmyanmar.devconyangon.base.core.mvp.Viewable
import org.devconmyanmar.devconyangon.domain.model.SessionId

/**
 * Created by Vincent on 11/11/19
 */
interface SessionDetailView : Viewable {

  fun subscribeToViewItemLiveData(viewItemLiveData: LiveData<SessionDetailViewItem>)

  fun getSessionId(): SessionId

}