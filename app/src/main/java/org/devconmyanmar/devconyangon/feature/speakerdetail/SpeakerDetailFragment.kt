package org.devconmyanmar.devconyangon.feature.speakerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.api.load
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpActivity
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.databinding.FragmentSpeakerDetailBinding
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/19
 */
class SpeakerDetailFragment :
  MvpFragment<FragmentSpeakerDetailBinding, SpeakerDetailView, SpeakerDetailViewModel>(),SpeakerDetailView,
  OnSessionItemClickListener {

  override val viewModel: SpeakerDetailViewModel by contractedViewModel()

  override fun bindView(inflater: LayoutInflater): FragmentSpeakerDetailBinding = FragmentSpeakerDetailBinding.inflate(layoutInflater)

  private val arguments : SpeakerDetailFragmentArgs by navArgs()

  override fun getSpeakerId(): SpeakerId = SpeakerId(arguments.speakerId)

  @Inject
  lateinit var imageLoader: ImageLoader

  private val speakerDetailSessionRecyclerViewAdapter by lazy {
    SpeakerDetailSessionRecyclerViewAdapter(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    //To prevent flickering too fast
    postponeEnterTransition(500L, TimeUnit.MILLISECONDS)
    return super.onCreateView(inflater, container, savedInstanceState)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    if (requireActivity() is AppCompatActivity) {
      (requireActivity() as AppCompatActivity).apply {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
      }
    }

    binding.rvSession.apply {
      adapter = speakerDetailSessionRecyclerViewAdapter
      layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    viewModel.loadSpeakerDetail()
  }

  override fun subscribeToSessionDetailViewItemLiveData(speakerDetailViewItemLiveData: LiveData<SpeakerDetailViewItem>) {
    speakerDetailViewItemLiveData.observe(viewLifecycleOwner, Observer {speakerDetailViewItem ->

      if (requireActivity() is AppCompatActivity) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = speakerDetailViewItem.name
      }

      binding.tvSpeakerBio.text = speakerDetailViewItem.biography

      imageLoader.load(requireContext(), speakerDetailViewItem.imageUrl) {
        this.crossfade(true)
        this.placeholder(R.drawable.placeholder_speaker)
        this.error(R.drawable.placeholder_speaker)
        this.target(binding.ivSpeaker)
      }

      speakerDetailSessionRecyclerViewAdapter.submitList(speakerDetailViewItem.sessionList)

    })
  }

  override fun onSessionItemClick(sessionId: SessionId, position: Int) {
    val directions = SpeakerDetailFragmentDirections.actionSpeakerDetailFragmentToSessionDetailFragment(sessionId.value)
    findNavController().navigate(directions)
  }

  override fun onFavoriteClick(sessionId: SessionId, position: Int) {
    viewModel.toggleFavorite(sessionId)
  }

}