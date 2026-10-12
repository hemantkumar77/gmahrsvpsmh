package com.app.abasiccommondiary.ui.rto

import androidx.lifecycle.ViewModelProvider
import com.app.abasiccommondiary.databinding.FragmentRtoBinding
import java.util.*
import android.annotation.SuppressLint
import android.content.Context
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
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Double.parseDouble
import java.lang.NumberFormatException

class RtoFragment : Fragment(), View.OnClickListener {

    private lateinit var rtoViewModel: RtoViewModel
    private var _binding: FragmentRtoBinding? = null
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
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rtoViewModel =
            ViewModelProvider(this).get(RtoViewModel::class.java)

        _binding = FragmentRtoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        tvTitle = binding.txtTitle
        etStateCode = binding.textInputEditTextStateCode
        etRtoCode = binding.textInputEditTextRtoCode
        etSearchRtoCode = binding.textInputEditTextSearchRtoCode
        tvSpeak = binding.tvSpeak
        tvSummary = binding.tvSummary
        btDetail = binding.btDetail
        btDetail!!.setOnClickListener(this)
        btRtoList = binding.btRtoList
        btRtoList!!.setOnClickListener(this)
        btSpokenRtoCode = binding.btSpokenRtoCode
        btSpokenRtoCode!!.setOnClickListener(this)
        btReadRtoCode = binding.btReadRtoCode
        btReadRtoCode!!.setOnClickListener(this)
        btStopReadRtoCode = binding.btStopReadRtoCode
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
                if(etStateCode!!.text.length==4){
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadData(){
        //loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
        tvSummary!!.text = "555.  Got the Rto Details Yey..."


        var strRtoCode = etRtoCode!!.text.toString()
        var strStateCode = etStateCode!!.text.toString()
        tvSummary!!.text = "1https://script.google.com/macros/s/AKfycbyzLKkNoicthE1x2ACgnJVOyUunDGQDYJpA7i5nkDAGqogV3Z15/exec?action=getRTODetails&stateCode=" + strStateCode + "&rtoCode=" + strRtoCode

        var listListStateRTO = readAssetsCSV(context, "state_rto.csv")
        var strStateRTO = "ABC"

        for (rtoDetail in listListStateRTO) {
            if(rtoDetail.get(0).equals(strStateCode,ignoreCase = true)){

                strStateRTO = rtoDetail.get(1) +"; " + rtoDetail.get(2)
                break
            }
        }
        tvSummary!!.text = strStateRTO

        parseItems(strStateRTO)
    }

    private fun showRtoList(){
        //loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
        tvSummary!!.text = "555.  Got the Rto Details Yey..."

        var strAllRTO = ""

        var listListStateRTO = readAssetsCSV(context, "state_rto.csv")

        for(rtoDetail in listListStateRTO){
            Log.d("EEEE", rtoDetail.get(1) + "---" + etSearchRtoCode?.text.toString())
            if(rtoDetail.get(1).contains( etSearchRtoCode?.text.toString(),ignoreCase = true)){
                strAllRTO = strAllRTO + rtoDetail.get(1) +". "
            }
        }

        tvSummary!!.text = strAllRTO
        parseItems(strAllRTO)
    }

    private fun showSpokenRto(){
        //loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
        //tvSummary!!.text = "555.  Got the Rto Details Yey..."
        var strSpeakText = tvSummary!!.text.toString()
        parseItems(strSpeakText)
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
        //tvSummary!!.text = "" + "Guntumukku Sarala Hemant Kumar"
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
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
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

    fun readAssetsCSV(context: Context?, fileName: String): List<List<String>> {
        //Read from Assets folder copy
        val csvData2D = mutableListOf<List<String>>()

        try {
            // Open the file from the assets folder using the provided context
            context?.assets?.open(fileName).use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    var headerline: String? = reader.readLine()
                    var line: String? = reader.readLine()
                    while (line != null) {
                        // Split the line by comma and add as an inner list
                        // Use .split(",") for exact comma matching
                        val row = line.split(",").map { it.trim() }
                        csvData2D.add(row)
                        line = reader.readLine()
                    }
                }
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        Log.d("CCCC", csvData2D.get(0).get(0)+ "..." + csvData2D.get(0).get(2)+"..." + csvData2D.get(10).get(0) + "..." + csvData2D.get(10).get(2))
        return csvData2D
    }
}