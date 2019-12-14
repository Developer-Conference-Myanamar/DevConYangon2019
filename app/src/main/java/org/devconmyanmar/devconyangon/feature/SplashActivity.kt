package org.devconmyanmar.devconyangon.feature

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.utils.openActivity

class SplashActivity:AppCompatActivity() {
    private val splashTime=3000L
    private val handler by lazy { Handler() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler.postDelayed({
            goToGettingStart()
        }, splashTime)
    }

    private fun goToGettingStart(){
        openActivity(GettingStartActivity::class.java)
    }
}