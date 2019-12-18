package org.devconmyanmar.devconyangon.feature.about.Infofragments.sponsor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.base.helper.AsyncViewResource
import org.devconmyanmar.devconyangon.databinding.FragmentSponsorBinding
import javax.inject.Inject

class SponsorFragment:MvpFragment<FragmentSponsorBinding,SponsorView,SponsorViewModel>(),SponsorView{

    override val viewModel: SponsorViewModel by contractedViewModel()

    override fun bindView(inflater: LayoutInflater): FragmentSponsorBinding
        = FragmentSponsorBinding.inflate(layoutInflater)

    @Inject
    lateinit var imageLoader: ImageLoader

    private val sponsorAdapter by lazy{
        SponsorRecyclerViewAdapter(imageLoader)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerSponsorLit.apply {
            adapter = sponsorAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

        viewModel.loadSponsors()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun showSponsorListOnScreen(sponsorListLD: LiveData<AsyncViewResource<List<SponsorViewItem>>>) {
        sponsorListLD.observe(this, Observer {
            when(it){
                is AsyncViewResource.Success->{
                    binding.apply {
                        sponsorAdapter.submitList(it.value)
                    }
                }
            }
        })
    }
}