package org.devconmyanmar.devconyangon.feature.schedule.sessiondetail

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpActivity
import org.devconmyanmar.devconyangon.base.helper.setUpRecycler
import org.devconmyanmar.devconyangon.databinding.ActivityScheduleDetailBinding
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.feature.speaker.SpeakerInfoActivity
import org.devconmyanmar.devconyangon.utils.Notification.AlarmReceiver


class SessionDetailActivity : MvpActivity<SessionDetailView, SessionDetailViewModel>(),
    SessionDetailView, OnSessionSpeakerItemClickListener {

    companion object {
        val SESSION_DETAIL_ID = "sessionDetailID"
        fun newInstance(context: Context, sessionId: Long): Intent {
            val intent = Intent(context, SessionDetailActivity::class.java)
            intent.putExtra(SESSION_DETAIL_ID, sessionId)
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
    private var sessionTime: Long = 0L
    private var sessionId:Long=0L
    private var isFavourite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScheduleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.contentToolbar)
        binding.contentToolbar.title = ""
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.contentToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        sessionId = intent.getLongExtra(SESSION_DETAIL_ID, -1)
        viewModel.loadSessionDetail(sessionId)

        binding.speakerContentInfoRecycler.setUpRecycler(this)
        binding.speakerContentInfoRecycler.adapter = adapter

        binding.clapFab.setOnClickListener {
            toggleFavourite(isFavourite)
            scheduleAlarm(sessionTime)
        }

        binding.contentBottomAppBar.replaceMenu(R.menu.schedule_detail_menu)
        binding.contentBottomAppBar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.schedule_detail_share->{
                    shareTextUrl()
                    true
                }
                R.id.schedule_detail_feedback->{
                    feedback()
                    true
                }
                else ->false
            }
        }
    }

    override fun subscribeToSessionDateViewItem(scheduleDateViewItemLD: LiveData<SessionDetailItem>) {
        scheduleDateViewItemLD.observe(this, Observer { sessionDetailItem ->
            binding.scheduleTitle.text = sessionDetailItem.sessionTitle
            binding.contentTime.text = sessionDetailItem.timeInString
            binding.contentLocation.text = sessionDetailItem.roomName
            adapter.submitList(sessionDetailItem.speakers)

            Log.e("timeMillisecond", sessionDetailItem.timeInMilliSecond.toString())
            sessionTime = sessionDetailItem.timeInMilliSecond

            isFavourite = sessionDetailItem.isFavorite
            setFavourite(isFavourite)
        })
    }

    override fun onSessionSpeakerItemClick(speakerId: SpeakerId, position: Int) {
        startActivity(SpeakerInfoActivity.newInstance(this, speakerId.value))
    }


    private fun scheduleAlarm(alertDelay: Long) {
        val alarmIntent = Intent(this, AlarmReceiver::class.java)
        alarmIntent.putExtra(AlarmReceiver.NOTIFICATION_DATA, "Alarm manager example")
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager


        val alarmTimeAtUTC =
            SystemClock.elapsedRealtime() + (alertDelay - System.currentTimeMillis())
        Log.e("alarmTime", "$alarmTimeAtUTC and $alertDelay and ${System.currentTimeMillis()}")
        when {
            SDK_INT >= VERSION_CODES.M -> {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    alarmTimeAtUTC, pendingIntent
                )
            }
            VERSION_CODES.KITKAT <= SDK_INT && SDK_INT < VERSION_CODES.M -> {
                alarmManager.setExact(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    alarmTimeAtUTC, pendingIntent
                )
            }
            SDK_INT < VERSION_CODES.KITKAT -> {
                alarmManager.set(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    alarmTimeAtUTC, pendingIntent
                )
            }
        }
    }

    fun setFavourite(isFavourite: Boolean) {
        if (isFavourite) {
            binding.clapFab.setImageResource(R.drawable.ic_favorite_accent_24dp)
        } else {
           binding.clapFab.setImageResource(R.drawable.ic_favorite_outline_black_24dp)
        }
    }

    fun toggleFavourite(isFavourite: Boolean) {
        if (!isFavourite) {
            binding.clapFab.setImageResource(R.drawable.ic_favorite_accent_24dp)
        } else {
            binding.clapFab.setImageResource(R.drawable.ic_favorite_outline_black_24dp)
        }
        viewModel.toggleFavoriteStatus(sessionId)
    }


    private fun shareTextUrl() {
        val DEV_CON_URL="https://demo1.devconmyanmar.org/"
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, resources.getString(org.devconmyanmar.devconyangon.R.string.dev_con_share_text))
        share.putExtra(Intent.EXTRA_TEXT, DEV_CON_URL)

        startActivity(Intent.createChooser(share, "Share link!"))
    }

    private fun feedback(){
        val feedbackEmail = Intent(Intent.ACTION_SEND)
        feedbackEmail.type = "text/email"
        feedbackEmail.putExtra(Intent.EXTRA_EMAIL, arrayOf("toezinthaw@gmail.com"))
        feedbackEmail.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
        startActivity(Intent.createChooser(feedbackEmail, "Send Feedback:"))
    }
}