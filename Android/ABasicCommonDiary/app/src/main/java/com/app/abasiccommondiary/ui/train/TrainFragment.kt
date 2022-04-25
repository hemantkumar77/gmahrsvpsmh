package com.app.abasiccommondiary.ui.train

import android.R.attr
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.abasiccommondiary.databinding.FragmentTrainBinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModel
import android.speech.tts.TextToSpeech
import java.util.*
import android.media.MediaPlayer
import android.os.Handler
import android.os.SystemClock
import android.text.method.ScrollingMovementMethod
import android.util.TypedValue
import android.widget.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_timer.*
import kotlinx.android.synthetic.main.fragment_train.*
import kotlin.collections.ArrayList
import org.json.JSONObject
import android.widget.AdapterView

class TrainFragment : Fragment(), View.OnClickListener {
    //https://www.tutorialspoint.com/working-with-recyclerview-in-an-android-app-using-kotlin
    private lateinit var trainViewModel: TrainViewModel
    private var _binding: FragmentTrainBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private val trainList = ArrayList<TrainModel>()
    private lateinit var trainAdapter: TrainAdapter
    private lateinit var tvUp : TextView
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
    var strTrainTime01 = "1111"
    var arr01 = arrayListOf<String>()
    var arr02 = arrayListOf<String>()
    var strStationName = ""
    var tvTrainDetails : TextView? = null
    var spnStationName : Spinner? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        trainViewModel =
            ViewModelProvider(this).get(TrainViewModel::class.java)

        _binding = FragmentTrainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTrain
        tvUp = binding.trainUp
        tvTrainDetails = binding.trainDetails
        spnStationName = binding.spnStationName
        spnStationName!!.setSelection(8,true)
        spnStationName!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                strStationName = "" + p0?.getItemAtPosition(p2).toString()
                //tvTrainDetails!!.text = p0?.getItemIdAtPosition(p2).toString()
                loadData()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        tvUp!!.setOnClickListener(this)
        val recyclerView: RecyclerView = binding.recyclerView
        trainAdapter = TrainAdapter(trainList)
        val layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = trainAdapter
        loadData()

        trainViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    private fun loadData(){
        trainList.clear()
        //loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
        if(strStationName.isEmpty()){
            strStationName = "DEHUROAD"
        }
        var strUpOrDown = tvUp.text
        var strScriptURL = "https://script.google.com/macros/s/AKfycbyzLKkNoicthE1x2ACgnJVOyUunDGQDYJpA7i5nkDAGqogV3Z15/exec?action=getTrainStationTime&trainStation=" + strStationName + "&upOrDown=" + strUpOrDown
        //var strScriptURL =   "https://script.google.com/macros/s/AKfycbyzLKkNoicthE1x2ACgnJVOyUunDGQDYJpA7i5nkDAGqogV3Z15/exec?action=getTrainStationTime&trainStation=AKURDI&upOrDown=UP"

        val stringRequest = StringRequest(
            Request.Method.GET,
            strScriptURL,
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
            java.util.ArrayList<HashMap<String, String?>>()
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
        //tvSummary!!.text = "" + rtoList
        strTrainTime01 = "" + rtoList
        prepareMovieData(strTrainTime01)
    }


    lateinit var mainHandler: Handler

    private fun prepareMovieData(str01: String) {
        //https://www.youtube.com/watch?v=10IE3oNbck8
        //Header ^^
        var train = TrainModel("Train Time", "Train No.", "Train Name", "Train Source", "Train Via", "Train Destination")
        //movieList.add(movie)

        var str02 = "{'TrainTime':[{'Time':'DEHUROAD','TrainNo':'10045','TrainName':'Pune Lonavala Local98','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'0:16','TrainNo':'10024','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'5:59','TrainNo':'10025','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'7:13','TrainNo':'10026','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'8:01','TrainNo':'10027','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'8:08','TrainNo':'10028','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'8:59','TrainNo':'10029','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'10:08','TrainNo':'10030','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'10:49','TrainNo':'10031','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'12:09','TrainNo':'10032','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'14:39','TrainNo':'10033','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'15:34','TrainNo':'10034','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'16:21','TrainNo':'10035','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'16:51','TrainNo':'10036','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'18:04','TrainNo':'10037','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'18:59','TrainNo':'10038','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'19:39','TrainNo':'10039','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'20:15','TrainNo':'10040','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'21:19','TrainNo':'10041','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'22:14','TrainNo':'10042','TrainName':'Pune Lonavala Local','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'22:44','TrainNo':'10043','TrainName':'Pune Lonavala Local96','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'23:14','TrainNo':'10044','TrainName':'Pune Lonavala Local97','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'0:18','TrainNo':'10045','TrainName':'Pune Lonavala Local98','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'17:00','TrainNo':'10045','TrainName':'Pune Lonavala Local98','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'},{'Time':'00:00','TrainNo':'10045','TrainName':'Pune Lonavala Local98','Source':'Pune','Via':'Kasarwadi','Destination':'Loanvala'}]}"
        val jsonList = "" + str01
        val jsonObject = JSONObject(str01)
        val jsonArray = jsonObject.getJSONArray("TrainTime")
        for(i in 0..jsonArray.length()-1){
            var jsonobj01 = jsonArray.getJSONObject(i)
/*
            arr01.add(jsonobj01.getString("Time"))
            arr02.add(jsonobj01.getString("TrainNo"))
            arr02.add(jsonobj01.getString("TrainName"))
*/
            train = TrainModel("" + jsonobj01.getString("Time"), "" + jsonobj01.getString("TrainNo"), "" + jsonobj01.getString("TrainName"), "" + jsonobj01.getString("Source"), "" + jsonobj01.getString("Via"), "" + jsonobj01.getString("Destination"))
            trainList.add(train)
        }

        var strarr01 = "" + arr01
        var strarr02 = "" + arr02
        //val mapper = jacksonObjectMapper()

        //var parts02 = str01.split(",")
        //var parts03 = parts02.toTypedArray()
        //var parts = parts03.drop(1)
        //var movie = MovieModel(parts[0], parts[1], parts[2])

/*
        var movie = MovieModel("Guntumukku", "Hemant", "Kumar")
        movieList.add(movie)
        movieList.clear()
*/
//        movieList.add(movie)


/*
        for(i in parts){
            movie = MovieModel(arr02, parts[1], i)
            movieList.add(movie)
        }
*/




/*
        movie = MovieModel("Star Wars: Episode VII - The Force Awakens", "Action", "2015")
        movieList.add(movie)
        movie = MovieModel("Shaun the Sheep", "Animation", "2015")
        movieList.add(movie)
        movie = MovieModel("The Martian", "Science Fiction & Fantasy", "2015")
        movieList.add(movie)
        movie = MovieModel("Mission: Impossible Rogue Nation", "Action", "2015")
        movieList.add(movie)
        movie = MovieModel("Up", "Animation", "2009")
        movieList.add(movie)
        movie = MovieModel("Star Trek", "Science Fiction", "2009")
        movieList.add(movie)
        movie = MovieModel("The LEGO MovieModel", "Animation", "2014")
        movieList.add(movie)
        movie = MovieModel("Iron Man", "Action & Adventure", "2008")
        movieList.add(movie)
        movie = MovieModel("Aliens", "Science Fiction", "1986")
        movieList.add(movie)
        movie = MovieModel("Chicken Run", "Animation", "2000")
        movieList.add(movie)
        movie = MovieModel("Back to the Future", "Science Fiction", "1985")
        movieList.add(movie)
        movie = MovieModel("Raiders of the Lost Ark", "Action & Adventure", "1981")
        movieList.add(movie)
        movie = MovieModel("Goldfinger", "Action & Adventure", "1965")
        movieList.add(movie)
        movie = MovieModel("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014")
        movieList.add(movie)
*/
        trainAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        if (v === btStartTimer) {
            println("aaaaaaaaaaaaaaaaaaaaaaa")
            mediaPlayer?.start()

            if(btnStartTimer.text.contains("Start")) {
                c_meter.base = SystemClock.elapsedRealtime() - offset
                //cmeter?.base ?: SystemClock.elapsedRealtime() - offset

                c_meter.start()
                btnStartTimer!!.setText("Stop")
            }
            else if(btnStartTimer.text.contains("Stop")) {
                c_meter.stop()
                offset = SystemClock.elapsedRealtime() - c_meter.base
                btnStartTimer!!.setText("Reset")
            }
            else if(btnStartTimer.text.contains("Reset")) {
                c_meter.base = SystemClock.elapsedRealtime()
                tvTimer!!.text = "0"
                offset = 0
                btnStartTimer!!.setText("Start")
            }
        }
        if (v === btResume) {
            if(btnStartTimer.text.contains("Reset")) {
                btnStartTimer!!.setText("Stop")
            }
        }
        if (v === tvUp){
            if (tvUp.text.contains("UP")){
                tvUp!!.text = "DOWN"
            }
            else if (tvUp.text.contains("DOWN")){
                tvUp!!.text = "UP"
            }
            loadData()
        }

        if (v == spnStationName){
            //if(spnStationName.chang)
            loadData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}