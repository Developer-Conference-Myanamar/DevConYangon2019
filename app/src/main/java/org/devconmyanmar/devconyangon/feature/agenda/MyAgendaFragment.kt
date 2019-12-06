package org.devconmyanmar.devconyangon.feature.agenda

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.databinding.FragmentMyAgendaBinding

/**
 * Created by Vincent on 2019-11-02
 */
class MyAgendaFragment : MvpFragment<MyAgendaView, MyAgendaViewModel>(), MyAgendaView {

  override val viewModel: MyAgendaViewModel by contractedViewModel()

  override val layoutId: Int
    get() = R.layout.fragment_my_agenda

  private val binding by lazy {
    FragmentMyAgendaBinding.bind(view!!)
  }

  private val myAgendaPagerAdapter by lazy {
    MyAgendaPagerAdapter(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.viewPager.adapter = myAgendaPagerAdapter

    TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
      val itemAtIndex = myAgendaPagerAdapter.getItemAtPosition(position)
      tab.text = itemAtIndex.dateAsString
    }.attach()

    viewModel.loadSessions()
  }

  override fun subscribeToViewItemListLiveData(viewItemListLiveData: LiveData<List<MyAgendaDateViewItem>>) {
    viewItemListLiveData.observe(viewLifecycleOwner, Observer {
      if(it.isNotEmpty()){
        myAgendaPagerAdapter.setItems(it)
      }else{

      }

    })
  }

  override fun scrollToIndex(indexToScrollTo: Int) {
    if (indexToScrollTo >= myAgendaPagerAdapter.itemCount - 1) {
      binding.viewPager.setCurrentItem(indexToScrollTo, false)
    }
  }

}