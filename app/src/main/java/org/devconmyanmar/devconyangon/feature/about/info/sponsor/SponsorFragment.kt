package org.devconmyanmar.devconyangon.feature.about.info.sponsor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.base.helper.AsyncViewResource
import org.devconmyanmar.devconyangon.base.helper.AsyncViewResource.Error
import org.devconmyanmar.devconyangon.base.helper.AsyncViewResource.Loading
import org.devconmyanmar.devconyangon.base.helper.AsyncViewResource.Success
import org.devconmyanmar.devconyangon.base.helper.setVisible
import org.devconmyanmar.devconyangon.base.helper.showLongToast
import org.devconmyanmar.devconyangon.databinding.FragmentSponsorBinding
import org.devconmyanmar.devconyangon.domain.exception.ExceptionToStringMapper
import javax.inject.Inject

class SponsorFragment : MvpFragment<FragmentSponsorBinding, SponsorView, SponsorViewModel>(),
  SponsorView {

  override val viewModel: SponsorViewModel by contractedViewModel()

  override fun bindView(inflater: LayoutInflater): FragmentSponsorBinding =
    FragmentSponsorBinding.inflate(layoutInflater)

  @Inject
  lateinit var imageLoader: ImageLoader

  @Inject
  lateinit var exceptionToStringMapper: ExceptionToStringMapper

  private val sponsorAdapter by lazy {
    SponsorRecyclerViewAdapter(imageLoader)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.loadSponsors()
    binding.recyclerSponsorLit.apply {
      adapter = sponsorAdapter
      layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
  }

  override fun showSponsorListOnScreen(sponsorListLD: LiveData<AsyncViewResource<List<SponsorViewItem>>>) {
    sponsorListLD.observe(this, Observer {
      when (it) {
        is Success -> {
          sponsorAdapter.submitList(it.value)
          binding.progressBar.setVisible(false)
          binding.recyclerSponsorLit.setVisible(true)
        }
        is Loading -> {
          binding.progressBar.setVisible(true)
          binding.recyclerSponsorLit.setVisible(false)
        }
        is Error -> {
          showLongToast(exceptionToStringMapper.map(it.error))
        }
      }
    })
  }
}