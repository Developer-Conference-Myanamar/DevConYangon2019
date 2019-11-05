package org.devconmyanmar.devconyangon.feature.agenda

import androidx.lifecycle.LiveData
import org.devconmyanmar.devconyangon.base.core.mvp.Viewable

/**
 * Created by Vincent on 2019-11-02
 */
interface MyAgendaView : Viewable {

  fun subscribeToViewItemListLiveData(viewItemListLiveData: LiveData<List<MyAgendaViewItem>>)
  
  fun scrollToIndex(first: Int, second: Int)

}