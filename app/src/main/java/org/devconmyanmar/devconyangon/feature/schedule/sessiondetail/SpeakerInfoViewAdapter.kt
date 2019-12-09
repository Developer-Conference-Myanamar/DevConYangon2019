package org.devconmyanmar.devconyangon.feature.schedule.sessiondetail

import android.view.View
import android.view.ViewGroup
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.recyclerview.*
import org.devconmyanmar.devconyangon.base.helper.inflater
import org.devconmyanmar.devconyangon.databinding.ItemSessionBinding
import org.devconmyanmar.devconyangon.databinding.ItemSpeakerBinding
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId

interface OnSessionSpeakerItemClickListener {
    fun onSessionSpeakerItemClick(speakerId: SpeakerId, position: Int)
}

class SpeakerInfoViewAdapter(
    private val onSessionItemClickListener: OnSessionSpeakerItemClickListener
) : BaseRecyclerViewAdapter<Speaker, SpeakerInfoViewAdapter.SessionSpeakerViewHolder>(
    diffCallback = diffCallBackWith(areItemTheSame = { item1, item2 ->
        item1.speakerId == item2.speakerId
    }, areContentsTheSame = { item1, item2 ->
        item1 == item2
    })
) {

    private val recyclerViewItemClickListener = recyclerViewItemClickListener { view, position ->
        when (view.id) {
            R.id.speakerContentInfo -> {
                onSessionItemClickListener.onSessionSpeakerItemClick(
                    getItem(position).speakerId,
                    position
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionSpeakerViewHolder {
        val itemView = parent.inflater().inflate(R.layout.item_speaker, parent, false)
        return SessionSpeakerViewHolder(itemView, recyclerViewItemClickListener)
    }

    class SessionSpeakerViewHolder(
        itemView: View,
        private val recyclerViewItemClickListener: RecyclerViewItemClickListener
    ) : BaseViewHolder<Speaker>(itemView) {

        private val binding = ItemSpeakerBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                recyclerViewItemClickListener.onItemClick(it, adapterPosition)
            }

            binding.speakerContentInfo.setOnClickListener {
                recyclerViewItemClickListener.onItemClick(
                    binding.speakerContentInfo,
                    adapterPosition
                )
            }
        }

        override fun bind(item: Speaker) {
            binding.speakerContentImage.setImageResource(R.drawable.ic_account_circle)
            binding.speakerContentName.text = item.name
            binding.speakerContentCompany.text = "Nexlabs"
        }
    }

}