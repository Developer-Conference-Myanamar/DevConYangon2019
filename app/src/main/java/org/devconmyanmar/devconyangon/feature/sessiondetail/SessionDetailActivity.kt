package org.devconmyanmar.devconyangon.feature.sessiondetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpActivity
import org.devconmyanmar.devconyangon.databinding.ActivitySessionDetailBinding
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import javax.inject.Inject

/**
 * Created by Vincent on 11/11/19
 */
class SessionDetailActivity : MvpActivity<SessionDetailView, SessionDetailViewModel>(),
  SessionDetailView, SessionDetailSpeakerItemClickListener {

  companion object {

    private const val IE_SESSION_ID = "SESSION_ID"

    fun newIntent(context: Context, sessionId: SessionId): Intent {
      val intent = Intent(context, SessionDetailActivity::class.java)
      intent.putExtra(IE_SESSION_ID, sessionId.value)
      return intent
    }
  }

  override val viewModel: SessionDetailViewModel by contractedViewModels()

  override val layoutResId: Int
    get() = R.layout.activity_session_detail

  private val binding: ActivitySessionDetailBinding by lazy {
    ActivitySessionDetailBinding.inflate(layoutInflater)
  }

  private val arguments: SessionDetailActivityArgs by navArgs()

  override fun getSessionId(): SessionId = SessionId(arguments.sessionId)

  private val sessionDetailSpeakerRecyclerViewAdapter by lazy {
    SessionDetailSpeakerRecyclerViewAdapter(imageLoader, this)
  }

  @Inject
  lateinit var imageLoader: ImageLoader

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //Override super class content View with binding's root
    //Probably want to remove super class inflation method to reduce CPU cost
    setContentView(binding.root)
    setSupportActionBar(binding.toolBar)
    supportActionBar?.title = ""
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    setSupportActionBar(binding.toolBar)
    supportActionBar?.title = ""
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    binding.rvSpeaker.apply {
      adapter = sessionDetailSpeakerRecyclerViewAdapter
      layoutManager = LinearLayoutManager(this@SessionDetailActivity, RecyclerView.VERTICAL, false)
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
        ContextCompat.getDrawable(this@SessionDetailActivity, R.drawable.ic_favorite_accent_24dp)
      } else {
        ContextCompat.getDrawable(
          this@SessionDetailActivity,
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
    //TODO: Navigate to Speaker Detail
  }
}