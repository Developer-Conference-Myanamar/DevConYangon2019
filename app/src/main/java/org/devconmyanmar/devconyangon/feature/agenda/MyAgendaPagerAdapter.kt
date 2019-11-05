package org.devconmyanmar.devconyangon.feature.agenda

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.recyclerview.BaseRecyclerViewAdapter
import org.devconmyanmar.devconyangon.base.core.recyclerview.BaseViewHolder
import org.devconmyanmar.devconyangon.base.core.recyclerview.diffCallBackWith
import org.devconmyanmar.devconyangon.base.helper.inflater
import org.devconmyanmar.devconyangon.databinding.ItemAgendaPagerBinding
import org.devconmyanmar.devconyangon.feature.agenda.MyAgendaPagerAdapter.MyAgendaPagerViewHolder

/**
 * Created by Vincent on 11/4/19
 */
class MyAgendaPagerAdapter(private val myAgendaItemClickListener: MyAgendaItemClickListener) :
  BaseRecyclerViewAdapter<MyAgendaViewItem, MyAgendaPagerViewHolder>(
    diffCallback = diffCallBackWith(areItemTheSame = { item1, item2 ->
      item1.date == item2.date
    }, areContentsTheSame = { item1, item2 ->
      item1 == item2
    })
  ) {

  private val recycledViewPool = RecyclerView.RecycledViewPool()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAgendaPagerViewHolder {
    val itemView = parent.inflater().inflate(R.layout.item_agenda_pager, parent, false)
    return MyAgendaPagerViewHolder(itemView, myAgendaItemClickListener, recycledViewPool)
  }

  fun getItemAtPosition(position: Int): MyAgendaViewItem {
    return getItem(position)
  }

  class MyAgendaPagerViewHolder(
    itemView: View,
    private val myAgendaItemClickListener: MyAgendaItemClickListener,
    private val recycledViewPool: RecyclerView.RecycledViewPool
  ) : BaseViewHolder<MyAgendaViewItem>(itemView) {

    private val binding = ItemAgendaPagerBinding.bind(itemView)
    private val myAgendaSessionAdapter = MyAgendaSessionAdapter(myAgendaItemClickListener)

    init {
      binding.rvSession.apply {
        adapter = myAgendaSessionAdapter
        layoutManager =
          LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
        setRecycledViewPool(this@MyAgendaPagerViewHolder.recycledViewPool)
      }
    }

    override fun bind(item: MyAgendaViewItem) {
      myAgendaSessionAdapter.submitList(item.listItems)
    }
  }

}