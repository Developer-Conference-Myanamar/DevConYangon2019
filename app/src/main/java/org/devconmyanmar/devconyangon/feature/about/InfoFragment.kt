package org.devconmyanmar.devconyangon.feature.about

import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment

/**
 * Created by Vincent on 2019-11-02
 */
class InfoFragment : MvpFragment<InfoView, InfoViewModel>(), InfoView {

  override val viewModel: InfoViewModel by contractedViewModel()

  override val layoutId: Int
    get() = R.layout.fragment_info

}