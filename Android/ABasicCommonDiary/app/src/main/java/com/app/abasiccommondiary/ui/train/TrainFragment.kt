package com.app.abasiccommondiary.ui.train

import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.abasiccommondiary.databinding.FragmentTrainBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.util.*
import android.os.Handler
import android.widget.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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

        val stringRequest = StringRequest(
            Request.Method.GET,
            strScriptURL,
            Response.Listener { response -> parseItems(response) },
            Response.ErrorListener {
                //tvSummary!!.text = "777.  Into Error...."
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

        trainAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View) {
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
            //loadData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}