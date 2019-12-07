package org.devconmyanmar.devconyangon.feature

import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.databinding.ActivityGettigStartedBinding
import org.devconmyanmar.devconyangon.feature.home.HomeActivity
import org.devconmyanmar.devconyangon.utils.openActivity

class GettingStartActivity:AppCompatActivity() {

    val startBtn: Button by lazy{
        findViewById<Button>(R.id.startBtn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gettig_started)


        startBtn.setOnClickListener {
            openActivity(HomeActivity::class.java)
        }
    }
}