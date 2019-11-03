package org.devconmyanmar.devconyangon

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader

/**
 * Created by Vincent on 2019-10-21
 */
object FlipperInitializer {

  fun initFlipper(context: Context) {
    if (FlipperUtils.shouldEnableFlipper(context)) {

      SoLoader.init(context, false)

      val client = AndroidFlipperClient.getInstance(context)
      client.addPlugin(InspectorFlipperPlugin(context, DescriptorMapping.withDefaults()))
      client.addPlugin(DatabasesFlipperPlugin(context))
      client.start()
    }
  }

}