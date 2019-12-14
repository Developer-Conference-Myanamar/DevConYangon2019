package org.devconmyanmar.devconyangon.feature.schedule.session

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.databinding.FragmentSessionBinding
import org.devconmyanmar.devconyangon.domain.helper.Zones
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.feature.schedule.ScheduleFragmentDirections
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate

/**
 * Created by Vincent on 2019-11-02
 */
class SessionFragment() : MvpFragment<FragmentSessionBinding, SessionView, SessionViewModel>(),
  SessionView, OnSessionItemClickListener {

  companion object {

    private const val ARG_DATE = "date"

    fun newInstance(localDate: LocalDate): SessionFragment {
      val fragment = SessionFragment()
      val bundle = bundleOf(
        ARG_DATE to localDate.atStartOfDay().atZone(Zones.YANGON).toInstant().toEpochMilli()
      )
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun bindView(inflater: LayoutInflater): FragmentSessionBinding =
    FragmentSessionBinding.inflate(inflater)

  private val date by lazy {
    val timestamp = requireArguments().getLong(ARG_DATE)
    Instant.ofEpochMilli(timestamp).atZone(Zones.YANGON).toLocalDate()
  }

  override val viewModel: SessionViewModel by contractedViewModel()

  private val sessionRecyclerViewAdapter by lazy {
    SessionRecyclerViewAdapter(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.setDate(date)
    viewModel.loadSessions()

    binding.rvSession.adapter = sessionRecyclerViewAdapter
    binding.rvSession.layoutManager = LinearLayoutManager(requireContext())

  }

  override fun subscribeToSessionList(sessionListLiveData: LiveData<List<SessionViewItem>>) {
    sessionListLiveData.observe(viewLifecycleOwner, Observer {
      sessionRecyclerViewAdapter.submitList(it)
    })
  }

  override fun onSessionItemClick(sessionId: SessionId, position: Int) {
    val action = ScheduleFragmentDirections.actionToSessionDetail(sessionId.value)
    findNavController().navigate(action)
  }

  override fun onFavoriteClick(sessionId: SessionId, position: Int) {
    viewModel.toggleFavoriteStatus(sessionId)
  }

}