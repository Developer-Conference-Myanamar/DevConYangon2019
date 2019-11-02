package org.devconmyanmar.devconyangon.feature.agenda

import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment

/**
 * Created by Vincent on 2019-11-02
 */
class MyAgendaFragment : MvpFragment<MyAgendaView, MyAgendaViewModel>(), MyAgendaView {

  override val viewModel: MyAgendaViewModel by contractedViewModel()

  override val layoutId: Int
    get() = R.layout.fragment_my_agenda

}