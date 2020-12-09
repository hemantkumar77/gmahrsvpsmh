package com.kumar.hemant.traintimetable2009.ui.timer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.kumar.hemant.traintimetable2009.R
import kotlinx.android.synthetic.main.fragment_rto.*
import java.lang.Double.parseDouble
import java.lang.NumberFormatException
import java.util.*
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.*
import kotlinx.android.synthetic.main.fragment_timer.*
import kotlinx.android.synthetic.*

class TimerFragment : Fragment(), View.OnClickListener {
    //https://medium.com/@dairdr/kotlin-playing-audio-file-3eeaca0d3cb1
    private lateinit var timerViewModel: TimerViewModel
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

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        timerViewModel =
            ViewModelProviders.of(this).get(TimerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_timer, container, false)
        tvTitle = root.findViewById(R.id.txtTitle) as TextView
        tvTimer = root.findViewById(R.id.txtTimer) as TextView
        etTimer = root.findViewById(R.id.textInputEditTextTimer)
        //meter = root.findViewById(R.id.c_meter)
        btStartTimer = root.findViewById<View>(R.id.btnStartTimer) as Button
        btStartTimer!!.setOnClickListener(this)
        var isWorking = false
        btResume = root.findViewById<View>(R.id.btnResume) as Button
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
        mediaPlayer?.setOnPreparedListener{
            println("Ready to Go")
        }

        mainHandler = Handler(Looper.getMainLooper())

/*        btnStartTimer.setOnTouchListener{_, event ->
            handleTouch(event)
            true
        }*/




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
                btnStartTimer!!.setText(R.string.stop)
            }
            else if(btnStartTimer.text.contains("Stop")) {
                c_meter.stop()
                offset = SystemClock.elapsedRealtime() - c_meter.base
                btnStartTimer!!.setText(R.string.reset)
            }
            else if(btnStartTimer.text.contains("Reset")) {
                c_meter.base = SystemClock.elapsedRealtime()
                tvTimer!!.text = "0"
                offset = 0
                btnStartTimer!!.setText(R.string.start)
            }
        }
        if (v === btResume) {
            if(btnStartTimer.text.contains("Reset")) {
                btnStartTimer!!.setText(R.string.stop)
            }
        }
    }
}