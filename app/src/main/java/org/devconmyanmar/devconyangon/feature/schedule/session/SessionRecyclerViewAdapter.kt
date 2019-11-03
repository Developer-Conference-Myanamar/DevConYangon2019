package org.devconmyanmar.devconyangon.feature.schedule.session

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.recyclerview.BaseRecyclerViewAdapter
import org.devconmyanmar.devconyangon.base.core.recyclerview.BaseViewHolder
import org.devconmyanmar.devconyangon.base.core.recyclerview.RecyclerViewItemClickListener
import org.devconmyanmar.devconyangon.base.core.recyclerview.diffCallBackWith
import org.devconmyanmar.devconyangon.base.core.recyclerview.recyclerViewItemClickListener
import org.devconmyanmar.devconyangon.base.helper.inflater
import org.devconmyanmar.devconyangon.databinding.ItemSessionBinding
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.feature.schedule.session.SessionRecyclerViewAdapter.SessionViewHolder

/**
 * Created by Vincent on 2019-11-02
 */
interface OnSessionItemClickListener {

  fun onSessionItemClick(sessionId: SessionId, position: Int)

  fun onFavoriteClick(sessionId: SessionId, position: Int)
}

class SessionRecyclerViewAdapter(
  private val onSessionItemClickListener: OnSessionItemClickListener
) : BaseRecyclerViewAdapter<SessionViewItem, SessionViewHolder>(
  diffCallback = diffCallBackWith(areItemTheSame = { item1, item2 ->
    item1.sessionId == item2.sessionId
  }, areContentsTheSame = { item1, item2 ->
    item1 == item2
  })
) {

  private val recyclerViewItemClickListener = recyclerViewItemClickListener { view, position ->
    when (view.id) {
      R.id.ivFavorite -> {
        onSessionItemClickListener.onFavoriteClick(getItem(position).sessionId, position)
      }
      else -> {
        onSessionItemClickListener.onSessionItemClick(getItem(position).sessionId, position)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
    val itemView = parent.inflater().inflate(R.layout.item_session, parent, false)
    return SessionViewHolder(itemView, recyclerViewItemClickListener)
  }

  class SessionViewHolder(
    itemView: View,
    private val recyclerViewItemClickListener: RecyclerViewItemClickListener
  ) : BaseViewHolder<SessionViewItem>(itemView) {

    private val binding = ItemSessionBinding.bind(itemView)

    init {
      itemView.setOnClickListener {
        recyclerViewItemClickListener.onItemClick(it, adapterPosition)
      }

      binding.ivFavorite.setOnClickListener {
        recyclerViewItemClickListener.onItemClick(binding.ivFavorite, adapterPosition)
      }
    }

    override fun bind(item: SessionViewItem) {
      if (item.shouldShowTime) {
        binding.tvTime.text = item.timeInString
        binding.tvAmPm.text = item.amPmOfTime
        binding.tvTime.visibility = View.VISIBLE
      } else {
        binding.tvTime.visibility = View.INVISIBLE
      }

      val favoriteDrawable = if (item.isFavorite) {
        ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_accent_24dp)
      } else {
        ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_outline_accent_24dp)
      }

      binding.ivFavorite.setImageDrawable(favoriteDrawable)

      binding.tvRoom.text = item.roomName
      binding.tvSessionTitle.text = item.sessionTitle
      binding.tvSpeaker.text = item.speakerNames
    }
  }

}