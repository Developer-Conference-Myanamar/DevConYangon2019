package org.devconmyanmar.devconyangon.feature.schedule.sessiondetail

import androidx.lifecycle.LiveData
import org.devconmyanmar.devconyangon.base.core.mvp.Viewable
import org.devconmyanmar.devconyangon.feature.schedule.ScheduleDateViewItem

interface SessionDetailView:Viewable {
    fun subscribeToSessionDateViewItem(scheduleDateViewItemLD:LiveData<ScheduleDateViewItem>)
}