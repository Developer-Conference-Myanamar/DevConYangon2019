package org.devconmyanmar.devconyangon.feature.agenda.session

import androidx.lifecycle.LiveData
import org.devconmyanmar.devconyangon.base.core.mvp.Viewable

/**
 * Created by Vincent on 11/6/19
 */
interface MyAgendaSessionView : Viewable {
  fun subscribeToMyAgendaSessionViewItemListLiveData(myAgendaSessionViewItemListLiveData: LiveData<List<MyAgendaSessionViewItem>>)

  fun scrollToIndex(indexToScrollTo: Int)

}
