package org.devconmyanmar.devconyangon.feature.schedule.session

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.databinding.FragmentSessionBinding
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.threeten.bp.LocalDate

/**
 * Created by Vincent on 2019-11-02
 */
class SessionFragment(
  private val date: LocalDate
) : MvpFragment<SessionView, SessionViewModel>(),
  SessionView, OnSessionItemClickListener {

  override val viewModel: SessionViewModel by contractedViewModel()

  override val layoutId: Int
    get() = R.layout.fragment_session

  private val sessionRecyclerViewAdapter by lazy {
    SessionRecyclerViewAdapter(this)
  }

  private val sessionBinding by lazy {
    FragmentSessionBinding.bind(view!!)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.setDate(date)
    viewModel.loadSessions()

    sessionBinding.rvSession.adapter = sessionRecyclerViewAdapter
    sessionBinding.rvSession.layoutManager = LinearLayoutManager(requireContext())

  }

  override fun subscribeToSessionList(sessionListLiveData: LiveData<List<SessionViewItem>>) {
    sessionListLiveData.observe(viewLifecycleOwner, Observer {
      sessionRecyclerViewAdapter.submitList(it)
    })
  }

  override fun onSessionItemClick(sessionId: SessionId, position: Int) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onFavoriteClick(sessionId: SessionId, position: Int) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}