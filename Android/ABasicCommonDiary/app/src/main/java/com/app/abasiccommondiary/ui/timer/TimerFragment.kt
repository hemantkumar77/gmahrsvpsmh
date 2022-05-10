package com.app.abasiccommondiary.ui.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.speech.tts.TextToSpeech
import android.view.MotionEvent
import java.util.*
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.*
import kotlinx.android.synthetic.main.fragment_timer.*
import com.app.abasiccommondiary.databinding.FragmentTimerBinding
import com.app.abasiccommondiary.R
import kotlinx.coroutines.NonCancellable.start

class TimerFragment : Fragment(), View.OnClickListener {

    private lateinit var timerViewModel: TimerViewModel
    private var _binding: FragmentTimerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var tvTitle : TextView? = null
    var tvTimer : TextView? = null
    var etTimer : EditText? = null
    var etStateCode : EditText? = null
    var etRtoCode : EditText? = null
    var etSearchRtoCode : EditText? = null
    var tvSpeak : TextView? = null
    var tvSummary : TextView? = null
    var etSpokenRtoCode : EditText? = null
    var btStartTimer : Button? = null
    var btResume : Button? = null
    var btSpokenRtoCode : Button? = null
    var btReadRtoCode : Button? = null
    var btStopReadRtoCode : Button? = null
    lateinit var mTTS: TextToSpeech
    private var mediaPlayer: MediaPlayer? = null
    var c_seconds:Long = 0
    var offset:Long = 0
    var int_a:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        timerViewModel =
            ViewModelProvider(this).get(TimerViewModel::class.java)

        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        val root: View = binding.root
        tvTitle = binding.txtTitle
        tvTimer = binding.txtTimer
        etTimer = binding.textInputEditTextTimer
        btStartTimer = binding.btnStartTimer
        btResume = binding.btnResume

        btStartTimer!!.setOnClickListener(this)
        btResume!!.setOnClickListener(this)

        timerViewModel.text.observe(viewLifecycleOwner, Observer {
            tvTitle!!.text = it
        })

        mTTS = TextToSpeech(requireActivity().applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR){
                //if there is no error then set language
                mTTS.language = Locale.ENGLISH
            }
        })
        mediaPlayer = MediaPlayer.create(requireActivity().applicationContext, R.raw.slack_drop)
        //mediaPlayer = MediaPlayer.create(requireActivity().applicationContext, binding.)
        mediaPlayer?.setOnPreparedListener {
            println("Ready to Go")
        }
        mainHandler = Handler(Looper.getMainLooper())

        return root
    }
    lateinit var mainHandler: Handler

    private val updateTextTask = object : Runnable {
        override fun run() {
            //minusOneSecond()
            mainHandler.postDelayed(this, 1000)

            if(btnStartTimer.text.contains("Stop")){
                var intTimer1: Int = ("" + tvTimer!!.text.toString()).toInt()
                int_a = ("" + etTimer!!.text.toString()).toInt()
                //var intTimer1: Int = 0
                intTimer1++
                tvTimer!!.text = "" + intTimer1.toString()
                //c_seconds = SystemClock.elapsedRealtime() - c_meter.base
                //int_a = (c_seconds/5000).toInt()

                if(intTimer1 % int_a == 0 )
                {
                    mediaPlayer?.start()
                    return
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
        mainHandler.post(updateTextTask)
    }
    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updateTextTask)
    }
    private fun handleTouch(event:MotionEvent){
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                mediaPlayer?.start()
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                mediaPlayer?.pause()
                mediaPlayer?.seekTo(0)
            }
        }
    }

    override fun onClick(v: View) {
        if (v === btStartTimer) {
            println("aaaaaaaaaaaaaaaaaaaaaaa")
            mediaPlayer?.start()

            if(btnStartTimer.text.contains("Start")) {
                c_meter.base = SystemClock.elapsedRealtime() - offset
                //cmeter?.base ?: SystemClock.elapsedRealtime() - offset

                c_meter.start()
                btnStartTimer!!.text = "Stop"

            }
            else if(btnStartTimer.text.contains("Stop")) {
                c_meter.stop()
                offset = SystemClock.elapsedRealtime() - c_meter.base
                btnStartTimer!!.text = "Reset"
            }
            else if(btnStartTimer.text.contains("Reset")) {
                c_meter.base = SystemClock.elapsedRealtime()
                tvTimer!!.text = "0"
                offset = 0
                btnStartTimer!!.text = "Start"
            }
        }
        if (v === btResume) {
            if(btnStartTimer.text.contains("Reset")) {
                btnStartTimer!!.text = "Stop"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}