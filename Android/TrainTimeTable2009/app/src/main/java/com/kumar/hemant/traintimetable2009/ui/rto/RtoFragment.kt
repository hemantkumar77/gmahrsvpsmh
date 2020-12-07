package com.kumar.hemant.traintimetable2009.ui.rto

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_rto.*
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
import java.lang.Double.parseDouble
import java.lang.NumberFormatException
import java.util.*

class RtoFragment : Fragment(), View.OnClickListener {

    private lateinit var rtoViewModel: RtoViewModel
    var tvTitle : TextView? = null
    var etStateCode : EditText? = null
    var etRtoCode : EditText? = null
    var etSearchRtoCode : EditText? = null
    var tvSpeak : TextView? = null
    var tvSummary : TextView? = null
    var etSpokenRtoCode : EditText? = null
    var btDetail : Button? = null
    var btRtoList : Button? = null
    var btSpokenRtoCode : Button? = null
    var btReadRtoCode : Button? = null
    var btStopReadRtoCode : Button? = null
    lateinit var mTTS: TextToSpeech

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        rtoViewModel = ViewModelProviders.of(this).get(RtoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_rto, container, false)
        tvTitle = root.findViewById(R.id.txtTitle) as TextView
        etStateCode = root.findViewById(R.id.textInputEditTextStateCode) as EditText
        etRtoCode = root.findViewById(R.id.textInputEditTextRtoCode) as EditText
        etSearchRtoCode = root.findViewById(R.id.textInputEditTextSearchRtoCode) as EditText
        tvSpeak = root.findViewById(R.id.tvSpeak) as TextView
        tvSummary = root.findViewById(R.id.tvSummary) as TextView
        btDetail = root.findViewById<View>(R.id.btDetail) as Button
        btDetail!!.setOnClickListener(this)
        btRtoList = root.findViewById<View>(R.id.btRtoList) as Button
        btRtoList!!.setOnClickListener(this)
        btSpokenRtoCode = root.findViewById<View>(R.id.btSpokenRtoCode) as Button
        btSpokenRtoCode!!.setOnClickListener(this)
        btReadRtoCode = root.findViewById<View>(R.id.btReadRtoCode) as Button
        btReadRtoCode!!.setOnClickListener(this)
        btStopReadRtoCode = root.findViewById<View>(R.id.btReadRtoCode) as Button
        btStopReadRtoCode!!.setOnClickListener(this)
        rtoViewModel.text.observe(viewLifecycleOwner, Observer { tvTitle!!.text = it })

        mTTS = TextToSpeech(requireActivity().applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR){
                //if there is no error then set language
                mTTS.language = Locale.ENGLISH
            }
        })

        etStateCode!!.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if(etStateCode!!.text.length==2){
                    etRtoCode!!.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        //loadData()
        //showAllRto()
        startSpeechToText()
        return root
    }

    private fun loadData(){
        //loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
        tvSummary!!.text = "555.  Got the Rto Details Yey..."


        var strRtoCode = etRtoCode?.text.toString()
        var strStateCode = etStateCode?.text.toString()
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://script.google.com/macros/s/AKfycbxKp0kp8Bgotjv81AgwnkROZ5MI8U19Hmq0r6GLAc36Gb_MNdA/exec?action=getRtoDetails&stateCode=" + strStateCode + "&rtoCode=" + strRtoCode,
            Response.Listener { response -> parseItems(response) },
            Response.ErrorListener {
                tvSummary!!.text = "777.  Into Error...."
            }
        )

        val socketTimeOut = 50000
        val policy: RetryPolicy =
            DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        stringRequest.retryPolicy = policy
        //val queue = Volley.newRequestQueue(this)
        val queue: RequestQueue = Volley.newRequestQueue(requireActivity().applicationContext)
        queue.add(stringRequest)
    }

    private fun showRtoList(){
        //loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
        tvSummary!!.text = "555.  Got the Rto Details Yey..."

        var strRtoText = etSearchRtoCode?.text.toString()
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://script.google.com/macros/s/AKfycbxKp0kp8Bgotjv81AgwnkROZ5MI8U19Hmq0r6GLAc36Gb_MNdA/exec?action=getRtoList&rtoText=" + strRtoText,
            Response.Listener { response -> parseItems(response) },
            Response.ErrorListener {
                tvSummary!!.text = "555.  Into Error...."
            }
        )

        val socketTimeOut = 50000
        val policy: RetryPolicy =
            DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        stringRequest.retryPolicy = policy
        //val queue = Volley.newRequestQueue(this)
        val queue: RequestQueue = Volley.newRequestQueue(requireActivity().applicationContext)
        queue.add(stringRequest)
    }

    private fun showSpokenRto(){
        //loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
        tvSummary!!.text = "555.  Got the Rto Details Yey..."

        var strSpeakText = tvSpeak?.text.toString()
        var strRtoCode = ""
        var strStateCode = ""
        if(strSpeakText.length == 5) {
            tvSummary!!.text = "999...  The text is ... " + strSpeakText.substring(3..4)
            var numeric = true
            try {
                val num1 = parseDouble( strSpeakText.substring(3..4))
            }
            catch (e: NumberFormatException){
                numeric = false
            }
            if(numeric){
                if (strSpeakText.substring(3..4).toInt() < 100) {
                    strRtoCode = tvSpeak?.text.toString().substring(3..4)
                    strStateCode = tvSpeak?.text.toString().substring(0..1)
                }
            }
        }
        else if(strSpeakText.length == 4) {
            tvSummary!!.text = "999...  The text is ... " + strSpeakText.substring(2..3)
            var numeric = true
            try {
                val num1 = parseDouble( strSpeakText.substring(2..3))
            }
            catch (e: NumberFormatException){
                numeric = false
            }
            if(numeric){
                if (strSpeakText.substring(2..3).toInt() < 100) {
                    strRtoCode = tvSpeak?.text.toString().substring(2..3)
                    strStateCode = tvSpeak?.text.toString().substring(0..1)
                }
            }
        }
/*
        else if(strSpeakText.length == 3) {
            var numeric = true
            try {
                val num1 = parseDouble( strSpeakText.substring(2..2))
            }
            catch (e: NumberFormatException){
                numeric = false
            }
            if(numeric){
                if (strSpeakText.substring(2..2).toInt() < 100) {
                    strRtoCode = tvSpeak?.text.toString().substring(2..2)
                    strStateCode = tvSpeak?.text.toString().substring(0..1)
                }
            }
        }
*/
        else{
            tvSummary!!.text = "888...  Into Error... " + strSpeakText.length.toString()
            return
        }
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://script.google.com/macros/s/AKfycbxKp0kp8Bgotjv81AgwnkROZ5MI8U19Hmq0r6GLAc36Gb_MNdA/exec?action=getRtoDetails&stateCode=" + strStateCode + "&rtoCode=" + strRtoCode,
            Response.Listener { response -> parseItems(response) },
            Response.ErrorListener {
                tvSummary!!.text = "777.  Into Error...."
            }
        )

        val socketTimeOut = 50000
        val policy: RetryPolicy =
            DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        stringRequest.retryPolicy = policy
        //val queue = Volley.newRequestQueue(this)
        val queue: RequestQueue = Volley.newRequestQueue(requireActivity().applicationContext)
        queue.add(stringRequest)
    }

    private fun parseItems(jsonResponse: String) {
        val list =
            ArrayList<HashMap<String, String?>>()
        val rtoList =  "$jsonResponse"
        var rtoLength = "ABCD"
        val fontSize1 = 15f

        if(rtoList.length<50){
            rtoLength = "111"
            var fontSize1 = 50f
            tvSummary?.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize1)
        }
        if(rtoList.length>50 && rtoList.length<150){
            rtoLength = "22"
            var fontSize1 = 30f
            tvSummary?.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize1)
        }
        if(rtoList.length>150){
            rtoLength = "3333"
            tvSummary?.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize1)
        }

        tvSummary?.setMovementMethod(ScrollingMovementMethod())
        tvSummary!!.text = "" + rtoList
        readRto()

        //loading!!.dismiss()
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun startSpeechToText(){
        //val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireActivity().applicationContext)
        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        var message01:String
        message01 = "ABCD"
        speechRecognizer.setRecognitionListener(object : RecognitionListener{
            override fun onReadyForSpeech(bundle: Bundle) {tvSummary!!.text = "Ready..."}
            override fun onBeginningOfSpeech() {tvSummary!!.text = "Begin..."}
            override fun onRmsChanged(v: Float) {tvSummary!!.text = "changed..." + v}
            override fun onBufferReceived(buffer: ByteArray) {tvSummary!!.text = "Received..."}
            override fun onEndOfSpeech() {tvSummary!!.text = "End..."}
            //override fun onError(error: Int) {tvSummary!!.text = "Error..." + error}
            override fun onError(error: Int) {
                speechRecognizer.cancel()
                message01 = getErrorText(error)
                //!!.text = "Error..." + error

            }
            override fun onResults(bundle: Bundle) {
                val matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if(matches!=null)
                    tvSpeak!!.text = "" + matches[0]
                    showSpokenRto()
            }

            override fun onPartialResults(partialResults: Bundle) {tvSummary!!.text = "Partial Results..."}
            override fun onEvent(i: Int, bundle: Bundle) {tvSummary!!.text = "Event..." + i}
        })
        btSpokenRtoCode!!.setOnTouchListener(View.OnTouchListener{view,motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_UP -> {
                    speechRecognizer.stopListening()
                    tvSpeak!!.text = "Hint2"//getString(R.string.text_hint)
                }
                MotionEvent.ACTION_DOWN -> {
                    speechRecognizer.startListening(speechRecognizerIntent)
                    tvSpeak!!.text = "Listening..."

                }
            }
            false
        })


    }
    private fun getErrorText(errorCode: Int): String {
        val errorMessage:String
        when(errorCode){
            SpeechRecognizer.ERROR_AUDIO -> errorMessage = "Audio recording error"
            SpeechRecognizer.ERROR_CLIENT -> errorMessage = "Audio recording error"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> errorMessage = "Audio recording error"
            SpeechRecognizer.ERROR_NETWORK -> errorMessage = "Audio recording error"
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> errorMessage = "Audio recording error"
            SpeechRecognizer.ERROR_NO_MATCH -> errorMessage = "Audio recording error"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> errorMessage = "Audio recording error"
            SpeechRecognizer.ERROR_SERVER -> errorMessage = "Audio recording error"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> errorMessage = "Speech time Out"
            else -> errorMessage = "Didn't understand. Please try again."
        }
        return errorMessage
    }

    private fun readRto(){
        val toSpeak = tvSummary!!.text.toString()
        mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
    }

    private fun stopReadRto(){
        if(mTTS.isSpeaking)
            mTTS.stop()
    }



    override fun onClick(v: View) {
        if (v === btDetail) {
            //updateTaskToSheet()
            loadData()
        }
        if (v === btRtoList) {
            showRtoList()
        }
        if (v === btSpokenRtoCode) {
            showSpokenRto()
        }
        if (v === btReadRtoCode) {
            readRto()
        }
        if (v === btStopReadRtoCode) {
            stopReadRto()
        }
    }

}