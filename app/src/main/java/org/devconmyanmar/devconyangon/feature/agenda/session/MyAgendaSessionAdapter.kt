package org.devconmyanmar.devconyangon.feature.agenda.session

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
import org.devconmyanmar.devconyangon.databinding.ItemAgendaHeaderBinding
import org.devconmyanmar.devconyangon.databinding.ItemAgendaSessionBinding
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionAdapter.MyAgendaViewHolder
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionAdapter.MyAgendaViewHolder.MyAgendaViewHolderHeader
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionAdapter.MyAgendaViewHolder.MyAgendaViewHolderSession
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionViewItem.MyAgendaSessionViewItemHeader
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionViewItem.MyAgendaSessionViewItemSession

/**
 * Created by Vincent on 11/5/19
 */
interface MyAgendaItemClickListener {

  fun onSessionItemClick(sessionId: SessionId, position: Int)

  fun onFavoriteClick(sessionId: SessionId, position: Int)

}

class MyAgendaSessionAdapter(private val myAgendaItemClickListener: MyAgendaItemClickListener) :
  BaseRecyclerViewAdapter<MyAgendaSessionViewItem, MyAgendaViewHolder>(
    diffCallback = diffCallBackWith(areItemTheSame = { item1, item2 ->
      if (item1 is MyAgendaSessionViewItemSession && item2 is MyAgendaSessionViewItemSession) {
        return@diffCallBackWith item1.sessionId == item2.sessionId
      }

      if (item1 is MyAgendaSessionViewItemHeader && item2 is MyAgendaSessionViewItemHeader) {
        return@diffCallBackWith item1.time == item2.time
      }

      item1 == item2
    }, areContentsTheSame = { item1, item2 ->
      item1 == item2
    })
  ) {

  companion object {
    private const val VIEW_TYPE_HEADER = 0
    private const val VIEW_TYPE_SESSION = 1
  }

  private val recyclerViewItemClickListener = recyclerViewItemClickListener { view, position ->

    val itemAtIndex = getItem(position)
    if (itemAtIndex is MyAgendaSessionViewItemSession) {
      when (view.id) {
        R.id.ivFavorite -> {
          myAgendaItemClickListener.onFavoriteClick(itemAtIndex.sessionId, position)
        }
        else -> {
          myAgendaItemClickListener.onSessionItemClick(itemAtIndex.sessionId, position)
        }
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAgendaViewHolder {
    return when (viewType) {
      VIEW_TYPE_HEADER -> {
        val itemView = parent.inflater().inflate(R.layout.item_agenda_header, parent, false)
        MyAgendaViewHolderHeader(itemView)
      }
      VIEW_TYPE_SESSION -> {
        val itemView = parent.inflater().inflate(R.layout.item_agenda_session, parent, false)
        MyAgendaViewHolderSession(itemView, recyclerViewItemClickListener)
      }
      else -> throw IllegalStateException()
    }

  }

  override fun getItemViewType(position: Int): Int {
    val itemAtIndex = getItem(position)

    return when (itemAtIndex) {
      is MyAgendaSessionViewItemHeader -> VIEW_TYPE_HEADER
      is MyAgendaSessionViewItemSession -> VIEW_TYPE_SESSION
    }

  }

  //region: View Holder
  sealed class MyAgendaViewHolder(itemView: View) :
    BaseViewHolder<MyAgendaSessionViewItem>(itemView) {

    class MyAgendaViewHolderHeader(itemView: View) : MyAgendaViewHolder(itemView) {

      private val binding = ItemAgendaHeaderBinding.bind(itemView)

      override fun bind(item: MyAgendaSessionViewItem) {
        if (item is MyAgendaSessionViewItemHeader) {
          binding.tvTime.text = item.timeInString
        }
      }
    }

    class MyAgendaViewHolderSession(
      itemView: View,
      private val recyclerViewItemClickListener: RecyclerViewItemClickListener
    ) : MyAgendaViewHolder(itemView) {

      private val binding = ItemAgendaSessionBinding.bind(itemView)

      init {
        binding.cardViewAgendaSession.setOnClickListener {
          recyclerViewItemClickListener.onItemClick(it, adapterPosition)
        }
        binding.ivFavorite.setOnClickListener {
          recyclerViewItemClickListener.onItemClick(it, adapterPosition)
        }
      }

      override fun bind(item: MyAgendaSessionViewItem) {

        if (item is MyAgendaSessionViewItemSession) {

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

  }

  //endregion

}