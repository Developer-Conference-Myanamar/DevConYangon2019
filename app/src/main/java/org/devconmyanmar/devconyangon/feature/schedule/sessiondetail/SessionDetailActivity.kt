package org.devconmyanmar.devconyangon.feature.schedule.sessiondetail

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpActivity
import org.devconmyanmar.devconyangon.databinding.ActivityScheduleDetailBinding
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.feature.speaker.SpeakerInfoActivity
import org.devconmyanmar.devconyangon.utils.Notification.AlarmReceiver
import org.devconmyanmar.devconyangon.utils.convertMillisecond
import org.devconmyanmar.devconyangon.utils.setUpRecycler




class SessionDetailActivity : MvpActivity<SessionDetailView, SessionDetailViewModel>(),
    SessionDetailView, OnSessionSpeakerItemClickListener {

    companion object{
        val SESSION_DETAIL_ID="sessionDetailID"
        fun newInstance(context: Context,sessionId:Long):Intent{
            val intent=Intent(context,SessionDetailActivity::class.java)
            intent.putExtra(SESSION_DETAIL_ID,sessionId)
            return intent
        }
    }
    override val viewModel: SessionDetailViewModel by contractedViewModels()
    override val layoutResId: Int
        get() = R.layout.activity_schedule_detail

    private lateinit var binding: ActivityScheduleDetailBinding
    private val adapter by lazy {
        SpeakerInfoViewAdapter(this)
    }
    private var sessionTime:Long=0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScheduleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sessionId=intent.getLongExtra(SESSION_DETAIL_ID,-1)
        viewModel.loadSessionDetail(sessionId)

        binding.speakerContentInfoRecycler.setUpRecycler(this)
        binding.speakerContentInfoRecycler.adapter = adapter

        binding.clapFab.setOnClickListener {
                scheduleAlarm(sessionTime)
        }
    }

    override fun subscribeToSessionDateViewItem(scheduleDateViewItemLD: LiveData<SessionDetailItem>) {
        scheduleDateViewItemLD.observe(this, Observer { sessionDetailItem ->
            binding.scheduleTitle.text = sessionDetailItem.sessionTitle
            binding.contentTime.text = sessionDetailItem.timeInString
            binding.contentLocation.text = sessionDetailItem.roomName
            adapter.submitList(sessionDetailItem.speakers)

            Log.e("timeMillisecond",sessionDetailItem.timeInMilliSecond.toString())
            sessionTime=sessionDetailItem.timeInMilliSecond
        })
    }

    override fun onSessionSpeakerItemClick(speakerId: SpeakerId, position: Int) {
        startActivity(SpeakerInfoActivity.newInstance(this,speakerId.value))
    }


    private fun scheduleAlarm(alertDelay: Long) {
        val alarmIntent = Intent(this, AlarmReceiver::class.java)
        alarmIntent.putExtra(AlarmReceiver.NOTIFICATION_DATA, "Alarm manager example")
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager


        val alarmTimeAtUTC =SystemClock.elapsedRealtime()+(alertDelay-System.currentTimeMillis())
        Log.e("alarmTime","$alarmTimeAtUTC and $alertDelay and ${System.currentTimeMillis()}")
        when{
            SDK_INT >= VERSION_CODES.M ->{
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    alarmTimeAtUTC, pendingIntent
                )
            }
            VERSION_CODES.KITKAT <= SDK_INT  && SDK_INT < VERSION_CODES.M ->{
                alarmManager.setExact(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    alarmTimeAtUTC, pendingIntent
                )
            }
            SDK_INT < VERSION_CODES.KITKAT ->{
                alarmManager.set(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    alarmTimeAtUTC, pendingIntent
                )
            }
        }
    }




}