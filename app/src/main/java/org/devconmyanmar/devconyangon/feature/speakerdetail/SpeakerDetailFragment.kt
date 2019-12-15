package org.devconmyanmar.devconyangon.feature.speakerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import coil.ImageLoader
import org.devconmyanmar.devconyangon.base.core.mvp.MvpActivity
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.databinding.FragmentSpeakerDetailBinding
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/19
 */
class SpeakerDetailFragment :
  MvpFragment<FragmentSpeakerDetailBinding, SpeakerDetailView, SpeakerDetailViewModel>(),SpeakerDetailView {

  override val viewModel: SpeakerDetailViewModel by contractedViewModel()

  override fun bindView(inflater: LayoutInflater): FragmentSpeakerDetailBinding = FragmentSpeakerDetailBinding.inflate(layoutInflater)

//  private val arguments : SpeakerDetailFragment

  @Inject
  lateinit var imageLoader: ImageLoader

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return super.onCreateView(inflater, container, savedInstanceState)

    //To prevent flickering too fast
    postponeEnterTransition(500L, TimeUnit.MILLISECONDS)
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


  }

}