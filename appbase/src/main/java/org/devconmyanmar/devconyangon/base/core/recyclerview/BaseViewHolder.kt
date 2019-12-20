package org.devconmyanmar.devconyangon.base.core.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by Vincent on 12/6/18
 */
abstract class BaseViewHolder<VB : ViewBinding, itemType> protected constructor(
  itemView: View
) : RecyclerView.ViewHolder(itemView) {

  abstract val binding: VB

  abstract fun bind(item: itemType)
}