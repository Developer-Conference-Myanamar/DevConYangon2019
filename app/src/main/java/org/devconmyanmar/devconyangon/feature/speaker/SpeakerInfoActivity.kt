package org.devconmyanmar.devconyangon.feature.speaker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpActivity
import org.devconmyanmar.devconyangon.databinding.ActivitySpeakerInfoBinding

class SpeakerInfoActivity:MvpActivity<SpeakerInfoView,SpeakerViewModel>(),SpeakerInfoView{

    companion object{
        const val SPEAKER_ID="speaker_id"
        fun newInstance(context: Context,id:Long):Intent{
            val intent=Intent(context,SpeakerInfoActivity::class.java)
            intent.putExtra(SPEAKER_ID,id)
            return intent
        }
    }

    override val viewModel: SpeakerViewModel by contractedViewModels()
    override val layoutResId: Int = R.layout.activity_speaker_info
    private lateinit var binding: ActivitySpeakerInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySpeakerInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.speakerInfoToolbar)
        binding.speakerInfoToolbar.title=""
        supportActionBar!!.title=""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.speakerInfoToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val speakerID= intent.getLongExtra(SPEAKER_ID,-1)
        viewModel.loadSpeaker(speakerID)
    }

    override fun subscribeSpeakerInfo(speakerInfoListLD: LiveData<SpeakerInfo>) {
       speakerInfoListLD.observe(this, Observer {
           binding.speakerName.text=it.name
       })
    }

}