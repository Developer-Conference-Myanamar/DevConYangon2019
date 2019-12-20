package org.devconmyanmar.devconyangon.feature.about.info.sponsor

import android.view.View
import android.view.ViewGroup
import coil.ImageLoader
import coil.api.load
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.recyclerview.BaseRecyclerViewAdapter
import org.devconmyanmar.devconyangon.base.core.recyclerview.BaseViewHolder
import org.devconmyanmar.devconyangon.base.core.recyclerview.diffCallBackWith
import org.devconmyanmar.devconyangon.base.helper.inflater
import org.devconmyanmar.devconyangon.databinding.ItemSponsorBinding

class SponsorRecyclerViewAdapter(
  private val imageLoader: ImageLoader
) : BaseRecyclerViewAdapter<SponsorViewItem, SponsorViewHolder>(
  diffCallBackWith(areItemTheSame = { oldItem, newItem ->
    oldItem.id == newItem.id
  }, areContentsTheSame = { oldItem, newItem ->
    oldItem == newItem
  })
) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorViewHolder {
    val itemView = parent.inflater().inflate(R.layout.item_sponsor, parent, false)
    return SponsorViewHolder(itemView, imageLoader)
  }
}

class SponsorViewHolder(
  itemView: View,
  private val imageLoader: ImageLoader
) : BaseViewHolder<ItemSponsorBinding, SponsorViewItem>(itemView) {
  override val binding: ItemSponsorBinding = ItemSponsorBinding.bind(itemView)

  override fun bind(item: SponsorViewItem) {

    binding.tvSponsorType.text = "${item.type} Sponsor"
    binding.tvSponsorName.text = item.name

    imageLoader.load(itemView.context, item.sponsorLogo) {
      crossfade(true)
      placeholder(R.drawable.ic_placeholder_speaker)
      target(binding.imgSponsorLogo)
    }
  }

}