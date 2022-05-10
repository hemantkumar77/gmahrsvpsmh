package com.app.abasiccommondiary.ui.checklist

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
import com.app.abasiccommondiary.databinding.FragmentChecklistBinding
import java.time.LocalDateTime
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.app.abasiccommondiary.MainActivity
import java.text.DecimalFormat
import java.util.regex.Pattern

class ChecklistFragment : Fragment(), View.OnClickListener {

    private lateinit var checklistViewModel: ChecklistViewModel
    private var _binding: FragmentChecklistBinding? = null
    private lateinit var textViewDate: TextView
    private lateinit var textViewSummary: TextView
    private var textViewChecklist = ArrayList<TextView>()
    private lateinit var buttonSaveTask: Button
    private lateinit var buttonLoadTask: Button
    private lateinit var buttonPrevTask: Button
    private lateinit var textViewMonthDate: TextView
    private lateinit var buttonNextTask: Button
    private lateinit var textHome: TextView
    var darkMode = ""
    var colorString = ""
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checklistViewModel =
            ViewModelProvider(this).get(ChecklistViewModel::class.java)

        _binding = FragmentChecklistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textViewChecklist = arrayListOf<TextView>(
            binding.A00,binding.A01,binding.A02,binding.A03,binding.A04,binding.A05,binding.A06,binding.A07,binding.A08,binding.A09,
            binding.A10,binding.A11,binding.A12,binding.A13,binding.A14,binding.A15,binding.A16,binding.A17,binding.A18,binding.A19,
            binding.A20,binding.A21,binding.A22,binding.A23,binding.A24,binding.A25,binding.A26,binding.A27,binding.A28,binding.A29,
            binding.A30,binding.A31,binding.A32,binding.A33,binding.A34)

        textViewDate = binding.tvDate
        textViewSummary = binding.txtSummary
        buttonSaveTask = binding.savebtn
        buttonLoadTask = binding.loadbtn
        buttonPrevTask = binding.prevbtn
        textViewMonthDate = binding.monthDate
        buttonNextTask = binding.nextbtn
        textHome = binding.textHome

        val nightModeFlags = context!!.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> darkMode="Yes"
            Configuration.UI_MODE_NIGHT_NO -> darkMode="No"
            Configuration.UI_MODE_NIGHT_UNDEFINED -> darkMode="IDK"
        }

        var colorString2 = Color.GRAY
        if(darkMode.contains("No"))
            colorString2 = Color.BLACK

        val textView: TextView = binding.textHome
        checklistViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = ""

            for (i in 0..34) {
                //textViewChecklist[i].setTextColor(Color.GRAY)
                textViewChecklist[i].setTextColor(colorString2)
            }
        })

        for(i in 0..34){
            textViewChecklist[i]!!.setOnClickListener(this)
        }
        buttonSaveTask!!.setOnClickListener(this)
        buttonLoadTask!!.setOnClickListener(this)
        buttonPrevTask!!.setOnClickListener(this)
        buttonNextTask!!.setOnClickListener(this)

        loadTasks()
        return root
    }

    private fun updateTaskToSheet(){
        //val loading = ProgressDialog.show(requireActivity().applicationContext, "Adding Item", "Please wait")
        //val name = editTextItemName!!.text.toString().trim { it <= ' ' }
        val brand = textViewSummary!!.text.toString().trim { it <= ' ' }
        var dataAlphanumeric = ""
        val currentDate = LocalDateTime.now()
        var monthNumber = currentDate.monthValue.toString()
        var dateNumber = currentDate.dayOfMonth.toString()
        //dateNumber = textViewMonthDate.text.toString()

        val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
        dateNumber = arr[0]// textViewMonthDate.text.toString()

        //                    textViewChecklist[i].setTextColor(Color.CYAN)
        for(i in 0..34){
            if(darkMode.contains("Yes")){
                dataAlphanumeric += if(textViewChecklist[i].currentTextColor==Color.CYAN){
                    "1"
                }
                else {
                    "0"
                }
            }
            else{
                dataAlphanumeric += if(textViewChecklist[i].currentTextColor==Color.GREEN){
                    "1"
                }
                else {
                    "0"
                }
            }
        }

        dataAlphanumeric+="a"

        val stringRequest: StringRequest =
            object : StringRequest(
                Method.POST, "https://script.google.com/macros/s/AKfycbyzLKkNoicthE1x2ACgnJVOyUunDGQDYJpA7i5nkDAGqogV3Z15/exec",
                Response.Listener { response ->
                    //loading.dismiss()
                    //Toast.makeText(this@AddItem, response, Toast.LENGTH_LONG).show()
                    val intent =
                        Intent(requireActivity().applicationContext, MainActivity::class.java)
                    startActivity(intent)
                },
                Response.ErrorListener { }
                //var queue: RequestQueue? = Volley.newRequestQueue(requireActivity().applicationContext)
            ) {
                override fun getParams(): Map<String, String> {
                    textViewSummary!!.text = "222. " + dateNumber + "-"+ monthNumber
                    val parmas: MutableMap<String, String> =
                        HashMap()

                    //here we pass params
                    parmas["action"] = "addItem"
                    parmas["dateNumber"] = dateNumber
                    parmas["monthNumber"] = monthNumber
                    //parmas["dataAlphanumeric"] = "0101001011011000110101110a"
                    parmas["dataAlphanumeric"] = dataAlphanumeric

                    //parmas["itemName"] = name
                    //parmas["brand"] = brand
                    return parmas
                }
            }
        val socketTimeOut = 50000 // u can change this .. here it is 50 seconds
        val retryPolicy: RetryPolicy =
            DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        stringRequest.retryPolicy = retryPolicy
        //val queue = Volley.newRequestQueue(this)
        var queue: RequestQueue? = Volley.newRequestQueue(requireActivity().applicationContext)
        if (queue != null) {
            queue.add(stringRequest)
        }
        textViewSummary!!.text = "444. " + dateNumber + "-"+ monthNumber
    }

    private fun loadPreviousTasks(){
        val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
        var dateNumber = arr[0].toInt()// textViewMonthDate.text.toString()
        var adjustDate = arr[2].toInt()
        if (dateNumber>1)
            textViewMonthDate!!.text="" + (dateNumber-1) + "," + arr[1]+"," + (adjustDate-1)
    }
    private fun loadNextTasks(){
        val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
        var dateNumber = arr[0].toInt()// textViewMonthDate.text.toString()
        var adjustDate = arr[2].toInt()
        if (dateNumber<31)
            textViewMonthDate!!.text="" + (dateNumber+1) + "," + arr[1] + "," + (adjustDate+1)
    }
    private fun loadTasks(){
        //loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
        var strdate04 = "5555"
        textViewSummary!!.text = "Loading Tasks..."
        val currentDate = LocalDateTime.now()
        var monthNumber = currentDate.monthValue.toString()
        var dateNumber = "ABCD"
        var adjustDate = ""

        if(textViewMonthDate.text.contains("Enter the Date",true)){
            dateNumber = currentDate.dayOfMonth.toString() + "," + currentDate.monthValue.toString()+",0"
            textViewMonthDate.setText(""+dateNumber)
            dateNumber = currentDate.dayOfMonth.toString()
        }
        else{
            val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
            dateNumber = arr[0]// textViewMonthDate.text.toString()
            adjustDate = arr[2]
        }

        val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
        var tomorrow = currentDate.plusDays(arr[2].toLong())


        textViewDate!!.text = "strdate04"
        strdate04 = tomorrow.dayOfWeek.toString().substring(0,3) +"-"+ tomorrow.year.toString()+"-"+ tomorrow.monthValue.toString()+"-"+tomorrow.dayOfMonth.toString()
        //strdate04 = currentDate.dayOfWeek.toString().substring(0,3) +"-"+ currentDate.year.toString()+"-"+ currentDate.monthValue.toString()+"-"+currentDate.dayOfMonth.toString()
        textViewDate!!.text = strdate04
        //etDateOfBirth.setText(dateNumber)
        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAA", monthNumber)
        Log.d("BBBBBBBBBBBBBBBBBBBBBBBBBBBB", "https://script.google.com/macros/s/AKfycbxKp0kp8Bgotjv81AgwnkROZ5MI8U19Hmq0r6GLAc36Gb_MNdA/exec?action=getItems&dateNumber="+dateNumber+"&monthNumber="+monthNumber)
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://script.google.com/macros/s/AKfycbyzLKkNoicthE1x2ACgnJVOyUunDGQDYJpA7i5nkDAGqogV3Z15/exec?action=getItems&dateNumber=" + dateNumber + "&monthNumber=" + monthNumber,
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
        textViewSummary!!.text = "666.  $strTask01, $strDailyCount, $strMonthlyCount"
        //textHome!!.text = "665.  $strTask01, $strDailyCount, $strMonthlyCount"

        for(i in 0..34){
            if(strTask01[i].toString()== "1"){
                if(darkMode.contains("Yes"))
                    textViewChecklist[i].setTextColor(Color.CYAN)
                else
                    textViewChecklist[i].setTextColor(Color.GREEN)
            }
            else if(strTask01[i].toString()== "0"){
                if(darkMode.contains("Yes"))
                    textViewChecklist[i].setTextColor(Color.GRAY)
                else
                    textViewChecklist[i].setTextColor(Color.BLACK)
            }
        }
        val df = DecimalFormat("#.##")
        var totalPercentage = df.format ((strMonthlyCount.toFloat() / (dateNumber.toFloat() * 35.0)) * 100.0)
        textViewSummary!!.text="Today: " + strDailyCount +" of 35. || Total: "+ strMonthlyCount + " of " + (dateNumber.toInt() * 35) + " ("+totalPercentage + " %)"
    }

    override fun onClick(v: View) {
        if (v === buttonSaveTask) {
            updateTaskToSheet()

            //Define what to do when button is clicked
        }
        if (v === buttonLoadTask) {
            loadTasks()

            //Define what to do when button is clicked
        }
        if (v === buttonPrevTask) {
            loadPreviousTasks()//https://stackoverflow.com/questions/27225815/android-how-to-show-datepicker-in-fragment
        }
        if (v === buttonNextTask) {
            loadNextTasks()//https://stackoverflow.com/questions/27225815/android-how-to-show-datepicker-in-fragment
        }
        for(i in 0..34) {
            if(v===textViewChecklist[i]){
                if(textViewChecklist[i].currentTextColor==Color.GRAY){
                    textViewChecklist[i].setTextColor(Color.CYAN)}
                else if(textViewChecklist[i].currentTextColor==Color.CYAN){
                    textViewChecklist[i].setTextColor(Color.GRAY)}
                else if(textViewChecklist[i].currentTextColor==Color.BLACK){
                    textViewChecklist[i].setTextColor(Color.GREEN)}
                else if(textViewChecklist[i].currentTextColor==Color.GREEN){
                    textViewChecklist[i].setTextColor(Color.BLACK)}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}