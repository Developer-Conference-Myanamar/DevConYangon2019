package org.devconmyanmar.devconyangon.feature.about

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.feature.about.viewholder.AboutViewHolder
import org.devconmyanmar.devconyangon.feature.about.viewholder.OnBasedViewHolder
import org.devconmyanmar.devconyangon.feature.about.viewholder.SettingViewHolder
import org.devconmyanmar.devconyangon.feature.about.viewholder.SponsorViewHolder

class InfoViewPagerAdapter : RecyclerView.Adapter<OnBasedViewHolder>() {

  private val settingView: Int = 1
  private val aboutView: Int = 2
  private val sponsorView: Int = 3

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBasedViewHolder {
    return when (viewType) {
      settingView -> {
        SettingViewHolder(
          LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_setting,
            parent,
            false
          )
        )
      }
      aboutView -> {
        AboutViewHolder(
          LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_about,
            parent,
            false
          )
        )
      }
      sponsorView -> {
        SponsorViewHolder(
          LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_demo,
            parent,
            false
          )
        )
      }
      else -> SponsorViewHolder(
        LayoutInflater.from(parent.context).inflate(
          R.layout.fragment_demo,
          parent,
          false
        )
      )

    }
  }

  override fun getItemViewType(position: Int): Int {
    return when (position) {
      0 -> settingView
      1 -> aboutView
      2 -> sponsorView
      else -> sponsorView
    }
  }

  override fun getItemCount() = 3
  @SuppressLint("SetTextI18n")
  override fun onBindViewHolder(holder: OnBasedViewHolder, position: Int) {

  }

}