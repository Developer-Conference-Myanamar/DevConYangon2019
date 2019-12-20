package org.devconmyanmar.devconyangon.feature.sessiondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.databinding.FragmentSessionDetailBinding
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Vincent on 11/11/19
 */
class SessionDetailFragment :
  MvpFragment<FragmentSessionDetailBinding, SessionDetailView, SessionDetailViewModel>(),
  SessionDetailView, SessionDetailSpeakerItemClickListener {

  override val viewModel: SessionDetailViewModel by contractedViewModel()

  override fun bindView(inflater: LayoutInflater): FragmentSessionDetailBinding =  FragmentSessionDetailBinding.inflate(layoutInflater)

  private val arguments: SessionDetailFragmentArgs by navArgs()

  override fun getSessionId(): SessionId = SessionId(arguments.sessionId)

  private val sessionDetailSpeakerRecyclerViewAdapter by lazy {
    SessionDetailSpeakerRecyclerViewAdapter(imageLoader, this)
  }

  @Inject
  lateinit var imageLoader: ImageLoader

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

    binding.rvSpeaker.apply {
      adapter = sessionDetailSpeakerRecyclerViewAdapter
      layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    binding.fabFavorite.setOnClickListener {
      viewModel.toggleFavorite()
    }

    viewModel.loadSessionDetail()
  }

  override fun subscribeToViewItemLiveData(viewItemLiveData: LiveData<SessionDetailViewItem>) {
//    binding.tvSessionTitle.text = "WHAT THE FUCK"
    viewItemLiveData.observe(this, Observer {

      val favoriteDrawable = if (it.isFavorite) {
        ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_accent_24dp)
      } else {
        ContextCompat.getDrawable(requireContext(),
          R.drawable.ic_favorite_outline_accent_24dp
        )
      }

      binding.apply {
        tvSessionTitle.text = it.title
        tvTime.text = it.dateTime
        tvRoom.text = it.roomName
        tvAbstract.text = it.abstract
        fabFavorite.setImageDrawable(favoriteDrawable)
      }
      sessionDetailSpeakerRecyclerViewAdapter.submitList(it.speakers)
    })
  }

  override fun onItemClick(speakerId: SpeakerId, position: Int) {
    val directions = SessionDetailFragmentDirections.actionSessionDetailFragmentToSpeakerDetailFragment(speakerId.value)
    findNavController().navigate(directions)

  }
}