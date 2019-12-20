package org.devconmyanmar.devconyangon.feature.about.webview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.devconmyanmar.devconyangon.R

/**
 * Created by ptut on
 * 08, February, 2019
 **/

class WebViewActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_URL = "extra.url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
    }
}