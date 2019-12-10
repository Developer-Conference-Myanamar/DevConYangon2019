package org.devconmyanmar.devconyangon.feature.about.Infofragments

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.databinding.FragmentAboutBinding
import org.devconmyanmar.devconyangon.feature.about.webview.CustomTabHelper
import org.devconmyanmar.devconyangon.feature.about.webview.WebViewActivity

class AboutFragment :Fragment(){

    private val customTabHelper: CustomTabHelper by lazy { CustomTabHelper() }
    private val DEV_CON_URL="https://demo1.devconmyanmar.org/"
    private val aboutBinding by lazy{
        FragmentAboutBinding.bind(view!!)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_about,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aboutBinding.intentToWeb.setOnClickListener {
            goCustomTab()
        }
    }

    private fun goCustomTab() {
        val builder = CustomTabsIntent.Builder()
        val anotherCustomTab = CustomTabsIntent.Builder().build()
        val requestCode = 100
        val intent = anotherCustomTab.intent
        intent.data = Uri.parse(DEV_CON_URL)

        val pendingIntent = PendingIntent.getActivity(
            context!!,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        // modify toolbar color
        builder.setToolbarColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
        // add share button to overflow men
        builder.addDefaultShareMenuItem()
        // add menu item to oveflow
        builder.addMenuItem("devcon 2019", pendingIntent)
        // show website title
        builder.setShowTitle(true)


        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_keyboard_arrow_left)
        // modify back button icon
        builder.setCloseButtonIcon(bitmap)
        // menu item icon
        builder.setActionButton(bitmap, "Android", pendingIntent, true)
        val customTabsIntent = builder.build()
        // check is chrom available
        val packageName = customTabHelper.getPackageNameToUse(context!!, DEV_CON_URL)
        if (packageName == null) {
            // if chrome not available open in web view
            val intentOpenUri = Intent(context!!, WebViewActivity::class.java)
            intentOpenUri.putExtra(WebViewActivity.EXTRA_URL, Uri.parse(DEV_CON_URL).toString())
            startActivity(intentOpenUri)
        } else {
            customTabsIntent.intent.setPackage(packageName)
            customTabsIntent.launchUrl(context, Uri.parse(DEV_CON_URL))
        }
    }
}