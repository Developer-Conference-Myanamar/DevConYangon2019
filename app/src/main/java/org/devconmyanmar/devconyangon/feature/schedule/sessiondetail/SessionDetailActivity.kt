package org.devconmyanmar.devconyangon.feature.schedule.sessiondetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.databinding.ActivityScheduleDetailBinding

class SessionDetailActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding= ActivityScheduleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }



}