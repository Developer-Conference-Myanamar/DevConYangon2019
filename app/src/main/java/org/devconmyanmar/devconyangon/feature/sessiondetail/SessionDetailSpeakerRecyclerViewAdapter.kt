package org.devconmyanmar.devconyangon.feature.sessiondetail

import android.view.View
import android.view.ViewGroup
import coil.ImageLoader
import coil.api.load
import coil.transform.CircleCropTransformation
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.recyclerview.BaseRecyclerViewAdapter
import org.devconmyanmar.devconyangon.base.core.recyclerview.BaseViewHolder
import org.devconmyanmar.devconyangon.base.core.recyclerview.RecyclerViewItemClickListener
import org.devconmyanmar.devconyangon.base.core.recyclerview.diffCallBackWith
import org.devconmyanmar.devconyangon.base.core.recyclerview.recyclerViewItemClickListener
import org.devconmyanmar.devconyangon.base.helper.inflater
import org.devconmyanmar.devconyangon.databinding.ItemSpeakerBinding
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.feature.sessiondetail.SessionDetailSpeakerRecyclerViewAdapter.SessionDetailSpeakerViewHolder

/**
 * Created by Vincent on 11/12/19
 */
interface SessionDetailSpeakerItemClickListener {

  fun onItemClick(speakerId: SpeakerId, position: Int)
}

class SessionDetailSpeakerRecyclerViewAdapter(
  private val imageLoader: ImageLoader,
  private val sessionDetailSpeakerItemClickListener: SessionDetailSpeakerItemClickListener
) :
  BaseRecyclerViewAdapter<SessionDetailSpeakerViewItem, SessionDetailSpeakerViewHolder>(
    diffCallBackWith(areItemTheSame = { oldItem, newItem ->
      oldItem.speakerId == newItem.speakerId
    }, areContentsTheSame = { oldItem, newItem ->
      oldItem == newItem
    })
  ) {

  private val itemClickListener = recyclerViewItemClickListener { view, position ->
    sessionDetailSpeakerItemClickListener.onItemClick(getItem(position).speakerId, position)
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): SessionDetailSpeakerViewHolder {
    val itemView = parent.inflater().inflate(R.layout.item_speaker, parent, false)
    return SessionDetailSpeakerViewHolder(itemView, itemClickListener, imageLoader)
  }

  class SessionDetailSpeakerViewHolder(
    itemView: View,
    private val recyclerViewItemClickListener: RecyclerViewItemClickListener,
    private val imageLoader: ImageLoader
  ) :
    BaseViewHolder<ItemSpeakerBinding, SessionDetailSpeakerViewItem>(itemView) {

    override val binding: ItemSpeakerBinding = ItemSpeakerBinding.bind(itemView)

    init {
      itemView.setOnClickListener {
        recyclerViewItemClickListener.onItemClick(it, adapterPosition)
      }
    }

    override fun bind(item: SessionDetailSpeakerViewItem) {
      imageLoader.load(itemView.context, item.imageUrl) {
        crossfade(true)
        placeholder(R.drawable.placeholder_speaker)
        this.error(R.drawable.placeholder_speaker)
        transformations(CircleCropTransformation())
        this.target(binding.ivSpeaker)
      }

      binding.tvSpeaker.text = item.name
      binding.tvSpeakerTitle.text = item.position
    }

  }
}