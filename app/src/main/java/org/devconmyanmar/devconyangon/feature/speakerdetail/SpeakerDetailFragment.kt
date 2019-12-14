package org.devconmyanmar.devconyangon.feature.speakerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import org.devconmyanmar.devconyangon.base.core.mvp.MvpActivity
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.databinding.FragmentSpeakerDetailBinding

/**
 * Created by Vincent on 12/14/19
 */
class SpeakerDetailFragment :
  MvpFragment<FragmentSpeakerDetailBinding, SpeakerDetailView, SpeakerDetailViewModel>() {

  override val viewModel: SpeakerDetailViewModel by contractedViewModel()

  override fun bindView(inflater: LayoutInflater): FragmentSpeakerDetailBinding = FragmentSpeakerDetailBinding.inflate(layoutInflater)

//  private val arguments : SpeakerDetailFragment

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