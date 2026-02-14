package com.app.abasiccommondiary.ui.pot

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.*
import com.app.abasiccommondiary.databinding.FragmentPotBinding
import java.time.LocalDateTime
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.app.abasiccommondiary.MainActivity
import com.app.abasiccommondiary.ui.pot.PotAdapter
import com.app.abasiccommondiary.ui.pot.PotModel
import org.json.JSONObject
import java.text.DecimalFormat
import java.util.regex.Pattern

class PotFragment : Fragment(), View.OnClickListener {

    private lateinit var potViewModel: PotViewModel
    private var _binding: FragmentPotBinding? = null
    private lateinit var textViewDate: TextView
    private lateinit var textViewSummary: TextView
    private var textViewPotPrev = ArrayList<TextView>()
    private var textViewPot = ArrayList<TextView>()
    private lateinit var textViewPotSplit: TextView
    private lateinit var buttonShowTask: Button
    private lateinit var buttonLoadTask: Button
    private lateinit var buttonPrevTask: Button
    private lateinit var textViewMonthDate: TextView
    private lateinit var buttonNextTask: Button
    private lateinit var textPotTotal: TextView
    private lateinit var textPotTotalDiff: TextView
    private val potList = ArrayList<PotModel>()
    var arr01 = arrayListOf<String>()
    var arr02 = arrayListOf<String>()
    private lateinit var potAdapter: PotAdapter
    var darkMode = ""
    var colorString = ""
    private lateinit var textViewSavPot: TextView
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        potViewModel =
            ViewModelProvider(this).get(PotViewModel::class.java)

        _binding = FragmentPotBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textViewPotPrev = arrayListOf<TextView>(binding.SAVDIFF,binding.SIPDIFF,binding.FNODIFF,binding.CRPDIFF,binding.POLDIFF)
        textViewPot = arrayListOf<TextView>(binding.SAV,binding.SIP,binding.FNO,binding.CRP,binding.POL)

        textViewDate = binding.tvDate
        textViewSummary = binding.txtSummary
        buttonShowTask = binding.showbtn!!
        buttonLoadTask = binding.loadbtn
        buttonPrevTask = binding.prevbtn
        textViewMonthDate = binding.monthDate
        buttonNextTask = binding.nextbtn
        textViewPotSplit = binding.potSplit!!
        textPotTotal = binding.textPotTotal!!
        textPotTotalDiff = binding.textPotTotalDIFF!!

        val nightModeFlags = requireContext().resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> darkMode="Yes"
            Configuration.UI_MODE_NIGHT_NO -> darkMode="No"
            Configuration.UI_MODE_NIGHT_UNDEFINED -> darkMode="IDK"
        }

        var colorString2 = Color.GRAY
        if(darkMode.contains("No"))
            colorString2 = Color.BLACK

        val textView: TextView? = binding.textHome
        potViewModel.text.observe(viewLifecycleOwner, Observer {
            textView?.text = ""

            for (i in 0..4) {
                //textViewPot[i].setTextColor(Color.GRAY)
                textViewPot[i].setTextColor(colorString2)
            }
        })

        for(i in 0..4){
            textViewPot[i]!!.setOnClickListener(this)
        }
        buttonShowTask!!.setOnClickListener(this)
        buttonLoadTask!!.setOnClickListener(this)
        buttonPrevTask!!.setOnClickListener(this)
        buttonNextTask!!.setOnClickListener(this)

        loadTasks()
        return root
    }

    private fun showPotDetails() {
        if (textViewPotSplit.visibility == View.VISIBLE) {
            textViewPotSplit.visibility = View.GONE
            buttonShowTask.text = "SHOW"
        }
        else{
            textViewPotSplit.visibility = View.VISIBLE
            buttonShowTask.text = "HIDE"
        }
    }

    private fun loadPreviousTasks(){
        val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
        var yearNumber = arr[0].toInt()// textViewMonthDate.text.toString()
        var monthNumber = arr[1].toInt()// textViewMonthDate.text.toString()
        var adjustDate = arr[2].toInt()
        if (monthNumber>1)
            textViewMonthDate!!.text="" + (yearNumber) + "," + (monthNumber-1) +"," + (adjustDate-1)
    }
    private fun loadNextTasks(){
        val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
        var yearNumber = arr[0].toInt()// textViewMonthDate.text.toString()
        var monthNumber = arr[1].toInt()// textViewMonthDate.text.toString()
        var adjustDate = arr[2].toInt()
        if (monthNumber<12)
            textViewMonthDate!!.text="" + (yearNumber+1) + "," + (monthNumber+1) + "," + (adjustDate+1)
    }
    private fun loadTasks(){
        //loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
        var strdate04 = "5555"
        textViewSummary!!.text = "Loading Tasks..."
        var currentDate = LocalDateTime.now()
        currentDate = currentDate.plusMonths(-1)
        var monthNumber = currentDate.monthValue.toString()
        var dateNumber = "ABCD"
        var adjustDate = ""

        if(textViewMonthDate.text.contains("Enter the Date",true)){
            //dateNumber = currentDate.dayOfMonth.toString() + "," + currentDate.monthValue.toString()+",0"
            dateNumber = currentDate.year.toString() + "," + currentDate.monthValue.toString()+",0"
            textViewMonthDate.setText(""+dateNumber)
            dateNumber = currentDate.dayOfMonth.toString()
        }
        else{
            val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
            dateNumber = arr[0]// textViewMonthDate.text.toString()
            monthNumber = arr[1]
            adjustDate = arr[2]
        }

        val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
        //var tomorrow = currentDate.plusDays(arr[2].toLong())
        var nextMonth = currentDate.plusMonths(arr[2].toLong())

        textViewDate!!.text = "strdate04"
        strdate04 = nextMonth.dayOfWeek.toString().substring(0,3) +"-"+ nextMonth.year.toString()+"-"+ nextMonth.monthValue.toString()+"-"+nextMonth.dayOfMonth.toString()
        //strdate04 = currentDate.dayOfWeek.toString().substring(0,3) +"-"+ currentDate.year.toString()+"-"+ currentDate.monthValue.toString()+"-"+currentDate.dayOfMonth.toString()
        textViewDate!!.text = strdate04
        //etDateOfBirth.setText(dateNumber)
        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAA", monthNumber)
        Log.d("BBBBBBBBBBBBBBBBBBBBBBBBBBBB", "https://script.google.com/macros/s/AKfycbxKp0kp8Bgotjv81AgwnkROZ5MI8U19Hmq0r6GLAc36Gb_MNdA/exec?action=getItems&dateNumber="+dateNumber+"&monthNumber="+monthNumber)
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://script.google.com/macros/s/AKfycbwimeldsdsiEyI1eRk9_yncK4_PS070CW5gu2F_97_seWxJKxzsFbE8IE1VCEUory9WUA/exec?action=getPotTotal&pot=pot&type=SAV&month="+monthNumber,
            Response.Listener { response -> parseItems(response, dateNumber) },
            Response.ErrorListener {
                textViewSummary!!.text = "555.  Into Error...."
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

    @SuppressLint("SetTextI18n")
    private fun parseItems(jsonResponse: String, dateNumber: String) {
        val list = ArrayList<HashMap<String, String?>>()
        val parts01 = jsonResponse.split(",")
        val strTask01 = parts01[0]
        val strDailyCount = parts01[1]
        val strMonthlyCount = parts01[2]
        //textViewSummary!!.text = "666.  $strTask01, $strDailyCount, $strMonthlyCount"
        textViewSummary!!.text = "666.  Data Loaded"

        preparePotData(jsonResponse)
        textViewPot[0].setTextColor(Color.YELLOW)
        textViewPot[1].setTextColor(Color.YELLOW)
        textViewPot[2].setTextColor(Color.YELLOW)
        textViewPot[3].setTextColor(Color.YELLOW)
        textViewPot[4].setTextColor(Color.YELLOW)
//        textViewPotPrev[0].setTextColor(Color.GREEN)
//        textViewPotPrev[1].setTextColor(Color.GREEN)
//        textViewPotPrev[2].setTextColor(Color.GREEN)
//        textViewPotPrev[3].setTextColor(Color.GREEN)
//        textViewPotPrev[4].setTextColor(Color.GREEN)
    }

    private fun preparePotData(str01: String) {
        //https://www.youtube.com/watch?v=10IE3oNbck8
        //Header ^^
        var pot = PotModel("Name", "Type", "PrevAmount", "Amount")
        //movieList.add(movie)

        val jsonList = "" + str01
        val jsonObject = JSONObject(str01)
        val jsonArray = jsonObject.getJSONArray("PotData")
        var strSAVPotAmountPrev = 0
        var strSIPPotAmountPrev = 0
        var strFNOPotAmountPrev = 0
        var strCRPPotAmountPrev = 0
        var strPOLPotAmountPrev = 0
        var strSAVPotAmount = 0
        var strSIPPotAmount = 0
        var strFNOPotAmount = 0
        var strCRPPotAmount = 0
        var strPOLPotAmount = 0
        var strPOTSplitAmount = ""
        for(i in 0..jsonArray.length()-1){
            var jsonobj01 = jsonArray.getJSONObject(i)
            pot = PotModel("" + jsonobj01.getString("potname"), "" + jsonobj01.getString("pottype"), "" + jsonobj01.getString("potamountprev"), "" + jsonobj01.getString("potamount"))
            potList.add(pot)
            textViewPot[0]!!.text = "Guntumukku"
            if(pot.getPottype()=="SAV")
            {
                strSAVPotAmount = strSAVPotAmount + (pot.getPotamount()?.toInt() ?: 0)
                var int1 = pot.getPotamountprev()?.toInt()!!
                var int2 = pot.getPotamount()?.toInt()!!
                int1 = int2 -int1
/*                if(int1<0)
                    textViewPotPrev[0].setTextColor(Color.RED)*/
                strSAVPotAmountPrev = strSAVPotAmountPrev + (int1 ?: 0)
            }
            if(pot.getPottype()=="SIP")
            {
                strSIPPotAmount = strSIPPotAmount + (pot.getPotamount()?.toInt() ?: 0)
                var int1 = pot.getPotamountprev()?.toInt()!!
                var int2 = pot.getPotamount()?.toInt()!!
                int1 = int2 -int1
/*                if(int1<0)
                    textViewPotPrev[1].setTextColor(Color.RED)*/
                strSIPPotAmountPrev = strSIPPotAmountPrev + (int1 ?: 0)
            }
            if(pot.getPottype()=="FNO")
            {
                strFNOPotAmount = strFNOPotAmount + (pot.getPotamount()?.toInt() ?: 0)
                var int1 = pot.getPotamountprev()?.toInt()!!
                var int2 = pot.getPotamount()?.toInt()!!
                int1 = int2 -int1
/*                if(int1<0)
                    textViewPotPrev[2].setTextColor(Color.RED)*/
                strFNOPotAmountPrev = strFNOPotAmountPrev + (int1 ?: 0)
            }
            if(pot.getPottype()=="CRP")
            {
                strCRPPotAmount = strCRPPotAmount + (pot.getPotamount()?.toInt() ?: 0)
                var int1 = pot.getPotamountprev()?.toInt()!!
                var int2 = pot.getPotamount()?.toInt()!!
                int1 = int2 -int1
/*                if(int1<0)
                    textViewPotPrev[3].setTextColor(Color.RED)*/
                strCRPPotAmountPrev = strCRPPotAmountPrev + (int1 ?: 0)
            }
            if(pot.getPottype()=="POL")
            {
                strPOLPotAmount = strPOLPotAmount + (pot.getPotamount()?.toInt() ?: 0)
                var int1 = pot.getPotamountprev()?.toInt()!!
                var int2 = pot.getPotamount()?.toInt()!!
                int1 = int2 -int1
/*                if(int1<0)
                    textViewPotPrev[4].setTextColor(Color.RED)*/
                strPOLPotAmountPrev = strPOLPotAmountPrev + (int1 ?: 0)
            }
            strPOTSplitAmount = strPOTSplitAmount + pot.getPotname()+" | "+ (pot.getPotamount()?.toInt() ?: 0)+" | "
        }
        strPOTSplitAmount = strPOTSplitAmount + " | "
        textViewPotPrev[0]!!.text = strSAVPotAmountPrev.toString()
        textViewPotPrev[1]!!.text = strSIPPotAmountPrev.toString()
        textViewPotPrev[2]!!.text = strFNOPotAmountPrev.toString()
        textViewPotPrev[3]!!.text = strCRPPotAmountPrev.toString()
        textViewPotPrev[4]!!.text = strPOLPotAmountPrev.toString()
        textViewPot[0]!!.text = strSAVPotAmount.toString()
        textViewPot[1]!!.text = strSIPPotAmount.toString()
        textViewPot[2]!!.text = strFNOPotAmount.toString()
        textViewPot[3]!!.text = strCRPPotAmount.toString()
        textViewPot[4]!!.text = strPOLPotAmount.toString()
        var intPotTotalDiff = strCRPPotAmountPrev+strFNOPotAmountPrev+strPOLPotAmountPrev+strSAVPotAmountPrev+strSIPPotAmountPrev
        var intPotTotal = strCRPPotAmount+strFNOPotAmount+strPOLPotAmount+strSAVPotAmount+strSIPPotAmount
        textPotTotal!!.text = intPotTotal.toString()
        textPotTotalDiff!!.text = intPotTotalDiff.toString()

        if(strSAVPotAmountPrev.toInt()<0)
            textViewPotPrev[0].setTextColor(Color.RED)
        if(strSIPPotAmountPrev.toInt()<0)
            textViewPotPrev[1].setTextColor(Color.RED)
        if(strFNOPotAmountPrev.toInt()<0)
            textViewPotPrev[2].setTextColor(Color.RED)
        if(strCRPPotAmountPrev.toInt()<0)
            textViewPotPrev[3].setTextColor(Color.RED)
        if(strPOLPotAmountPrev.toInt()<0)
            textViewPotPrev[4].setTextColor(Color.RED)

        if(intPotTotalDiff<0)
            textPotTotalDiff.setTextColor(Color.RED)
        //showPotDetails()
        textViewPotSplit.text = strPOTSplitAmount
    }

    override fun onClick(v: View) {
        if (v === buttonShowTask) {
            showPotDetails()
        }
        if (v === buttonLoadTask) {
            loadTasks()
        }
        if (v === buttonPrevTask) {
            loadPreviousTasks()
        }
        if (v === buttonNextTask) {
            loadNextTasks()
        }
        for(i in 0..4) {
            if(v===textViewPot[i]){
                if(textViewPot[i].currentTextColor==Color.GRAY){
                    textViewPot[i].setTextColor(Color.CYAN)}
                else if(textViewPot[i].currentTextColor==Color.CYAN){
                    textViewPot[i].setTextColor(Color.GRAY)}
                else if(textViewPot[i].currentTextColor==Color.BLACK){
                    textViewPot[i].setTextColor(Color.GREEN)}
                else if(textViewPot[i].currentTextColor==Color.GREEN){
                    textViewPot[i].setTextColor(Color.BLACK)}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}