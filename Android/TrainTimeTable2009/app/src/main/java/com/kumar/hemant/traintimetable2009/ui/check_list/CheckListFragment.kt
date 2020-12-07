package com.kumar.hemant.traintimetable2009.ui.home

//import android.support.annotation.Nullable
//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.datepicker.MaterialDatePicker
import com.kumar.hemant.traintimetable2009.MainActivity
import com.kumar.hemant.traintimetable2009.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONException
import org.json.JSONObject
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.util.*

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel
    var buttonSaveTask: Button? = null
    var buttonLoadTask: Button? = null
    var buttonDeleteTask: Button? = null
    var buttonDate: Button? = null
    var textViewSummary: TextView? = null
    var textViewDate: TextView? = null
    private lateinit var cbCheckList00 : CheckBox
    private lateinit var cbCheckList01 : CheckBox
    private lateinit var cbCheckList02 : CheckBox
    private lateinit var cbCheckList03 : CheckBox
    private lateinit var cbCheckList04 : CheckBox
    private lateinit var cbCheckList05 : CheckBox
    private lateinit var cbCheckList06 : CheckBox
    private lateinit var cbCheckList07 : CheckBox
    private lateinit var cbCheckList08 : CheckBox
    private lateinit var cbCheckList09 : CheckBox
    private lateinit var cbCheckList10 : CheckBox
    private lateinit var cbCheckList11 : CheckBox
    private lateinit var cbCheckList12 : CheckBox
    private lateinit var cbCheckList13 : CheckBox
    private lateinit var cbCheckList14 : CheckBox
    private lateinit var cbCheckList15 : CheckBox
    private lateinit var cbCheckList16 : CheckBox
    private lateinit var cbCheckList17 : CheckBox
    private lateinit var cbCheckList18 : CheckBox
    private lateinit var cbCheckList19 : CheckBox
    private lateinit var cbCheckList20 : CheckBox
    private lateinit var cbCheckList21 : CheckBox
    private lateinit var cbCheckList22 : CheckBox
    private lateinit var cbCheckList23 : CheckBox
    private lateinit var cbCheckList24 : CheckBox
    private lateinit var etCurrentDate : EditText
    var selectedDate = "Hemant"
    private val mListener: OnFragmentInteractionListener? = null
    val REQUEST_CODE = 11

    fun HomeFragment() {
        // Required empty public constructor
    }

    fun newInstance() {
        return HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        textViewSummary = root.findViewById(R.id.txtSummary) as TextView
        textViewDate = root.findViewById<View>(R.id.tv_date) as TextView
        buttonSaveTask = root.findViewById<View>(R.id.savebtn) as Button
        buttonSaveTask!!.setOnClickListener(this)
        buttonLoadTask = root.findViewById<View>(R.id.loadbtn) as Button
        buttonLoadTask!!.setOnClickListener(this)
        buttonDate = root.findViewById<View>(R.id.datebtn) as Button
        etCurrentDate = root.findViewById(R.id.currentDate)
        //buttonDate!!.setOnClickListener(this)
        cbCheckList00 = root.findViewById<View>(R.id.A00) as CheckBox
        cbCheckList01 = root.findViewById<View>(R.id.A01) as CheckBox
        cbCheckList02 = root.findViewById<View>(R.id.A02) as CheckBox
        cbCheckList03 = root.findViewById<View>(R.id.A03) as CheckBox
        cbCheckList04 = root.findViewById<View>(R.id.A04) as CheckBox
        cbCheckList05 = root.findViewById<View>(R.id.A05) as CheckBox
        cbCheckList06 = root.findViewById<View>(R.id.A06) as CheckBox
        cbCheckList07 = root.findViewById<View>(R.id.A07) as CheckBox
        cbCheckList08 = root.findViewById<View>(R.id.A08) as CheckBox
        cbCheckList09 = root.findViewById<View>(R.id.A09) as CheckBox
        cbCheckList10 = root.findViewById<View>(R.id.A10) as CheckBox
        cbCheckList11 = root.findViewById<View>(R.id.A11) as CheckBox
        cbCheckList12 = root.findViewById<View>(R.id.A12) as CheckBox
        cbCheckList13 = root.findViewById<View>(R.id.A13) as CheckBox
        cbCheckList14 = root.findViewById<View>(R.id.A14) as CheckBox
        cbCheckList15 = root.findViewById<View>(R.id.A15) as CheckBox
        cbCheckList16 = root.findViewById<View>(R.id.A16) as CheckBox
        cbCheckList17 = root.findViewById<View>(R.id.A17) as CheckBox
        cbCheckList18 = root.findViewById<View>(R.id.A18) as CheckBox
        cbCheckList19 = root.findViewById<View>(R.id.A19) as CheckBox
        cbCheckList20 = root.findViewById<View>(R.id.A20) as CheckBox
        cbCheckList21 = root.findViewById<View>(R.id.A21) as CheckBox
        cbCheckList22 = root.findViewById<View>(R.id.A22) as CheckBox
        cbCheckList23 = root.findViewById<View>(R.id.A23) as CheckBox
        cbCheckList24 = root.findViewById<View>(R.id.A24) as CheckBox
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //textViewSummary!!.text = "1111. Hemant Kumar"//it
        })

        val builder : MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Select Date")
        val picker:MaterialDatePicker<*> = builder.build()

/*        buttonDate!!.setOnClickListener{
            picker.show(fragmentManager?, picker.toString())
        }*/

        picker.addOnPositiveButtonClickListener {
            textViewDate!!.text = "Selected Date:" + picker.headerText
        }

        loadTasks()

        val fm = (activity as AppCompatActivity?)!!.supportFragmentManager


        return root
    }
    private fun loadTasks(){
        //loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
        var strdate04 = "5555"
        textViewSummary!!.text = "555.  "
        val currentDate = LocalDateTime.now()
        var monthNumber = currentDate.monthValue.toString()
        var dateNumber = "ABCD"

        if(etCurrentDate.text.contains("Enter the Date",true)){
            dateNumber = currentDate.dayOfMonth.toString()
            etCurrentDate.setText(""+dateNumber)
        }
        else{
            dateNumber = etCurrentDate.text.toString()
            //dateNumber = "19"
        }

        textViewDate!!.text = "strdate04"
        strdate04 = currentDate.dayOfWeek.toString().substring(0,3) +"-"+ currentDate.year.toString()+"-"+ currentDate.monthValue.toString()+"-"+currentDate.dayOfMonth.toString()
        textViewDate!!.text = strdate04
        //etDateOfBirth.setText(dateNumber)
        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAA", monthNumber)
        Log.d("BBBBBBBBBBBBBBBBBBBBBBBBBBBB", "https://script.google.com/macros/s/AKfycbxKp0kp8Bgotjv81AgwnkROZ5MI8U19Hmq0r6GLAc36Gb_MNdA/exec?action=getItems&dateNumber="+dateNumber+"&monthNumber="+monthNumber)
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://script.google.com/macros/s/AKfycbxKp0kp8Bgotjv81AgwnkROZ5MI8U19Hmq0r6GLAc36Gb_MNdA/exec?action=getItems&dateNumber=" + dateNumber + "&monthNumber=" + monthNumber,
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

    private fun parseItems(jsonResponse: String, dateNumber: String) {
        val list =
            ArrayList<HashMap<String, String?>>()
        textViewSummary!!.text = "666.  $jsonResponse"
        try {
            var txt01 = jsonResponse.substring(8)
            val jobj = JSONObject(txt01)
            val jarray = jobj.getJSONArray("items")
            //val monthName = "June"

            var str02 = "001"

            for (i in 0 until jarray.length()) {
                val jo = jarray.getJSONObject(i)
                val itemName = jo.getString(dateNumber)
/*
                cbCheckList01.isChecked = !(i==0 && itemName=="0")
                cbCheckList02.isChecked = !(i==1 && itemName=="0")
                cbCheckList03.isChecked = !(i==2 && itemName=="0")
                cbCheckList04.isChecked = !(i==3 && itemName=="0")
                cbCheckList05.isChecked = !(i==4 && itemName=="0")
*/

                if(i==0){
                    cbCheckList00.isChecked = itemName=="1"                }
                if(i==1){
                    cbCheckList01.isChecked = itemName=="1"                }
                if(i==2){
                    cbCheckList02.isChecked = itemName=="1"                }
                if(i==3){
                    cbCheckList03.isChecked = itemName=="1"                }
                if(i==4){
                    cbCheckList04.isChecked = itemName=="1"                }
                if(i==5){
                    cbCheckList05.isChecked = itemName=="1"                }
                if(i==6){
                    cbCheckList06.isChecked = itemName=="1"                }
                if(i==7){
                    cbCheckList07.isChecked = itemName=="1"                }
                if(i==8){
                    cbCheckList08.isChecked = itemName=="1"                }
                if(i==9){
                    cbCheckList09.isChecked = itemName=="1"                }
                if(i==10){
                    cbCheckList10.isChecked = itemName=="1"                }
                if(i==11){
                    cbCheckList11.isChecked = itemName=="1"                }
                if(i==12){
                    cbCheckList12.isChecked = itemName=="1"                }
                if(i==13){
                    cbCheckList13.isChecked = itemName=="1"                }
                if(i==14){
                    cbCheckList14.isChecked = itemName=="1"                }
                if(i==15){
                    cbCheckList15.isChecked = itemName=="1"                }
                if(i==16){
                    cbCheckList16.isChecked = itemName=="1"                }
                if(i==17){
                    cbCheckList17.isChecked = itemName=="1"                }
                if(i==18){
                    cbCheckList18.isChecked = itemName=="1"                }
                if(i==19){
                    cbCheckList19.isChecked = itemName=="1"                }
                if(i==20){
                    cbCheckList20.isChecked = itemName=="1"                }
                if(i==21){
                    cbCheckList21.isChecked = itemName=="1"                }
                if(i==22){
                    cbCheckList22.isChecked = itemName=="1"                }
                if(i==23){
                    cbCheckList23.isChecked = itemName=="1"                }
                if(i==24){
                    cbCheckList24.isChecked = itemName=="1"                }
                //val brand = jo.getString("brand")
                //val price = jo.getString("price")
                var str01 = jsonResponse.subSequence(5,8)
                var str02 = str01.toString()
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING
                // println(df.format(num))
                var totalPercentage = df.format ((str02.toFloat() / (dateNumber.toFloat() * 25.0)) * 100.0)
                textViewSummary!!.text="Today: " + jsonResponse.substring(2,4) +" of 25. \nTotal: "+jsonResponse.subSequence(5,8) + " of " + (dateNumber.toInt() * 25) + " ("+totalPercentage + " %)"

                val item =
                    HashMap<String, String?>()
                //item["itemName"] = itemName
                item["7"] = itemName
                //item["brand"] = brand
                //item["price"] = price
                list.add(item)
            }
            //editTextItemName!!.text = "abcd"
            //textViewSummary!!.text="abcd"
        } catch (e: JSONException) {
            e.printStackTrace()
        }

/*
        adapter = SimpleAdapter(
            this,
            list,
            R.layout.list_item_row,
            arrayOf("itemName"),
            intArrayOf(R.id.tv_item_name)
        )
        listView!!.adapter = adapter
*/
        //loading!!.dismiss()
    }

    private fun updateTaskToSheet() {
        //val loading = ProgressDialog.show(requireActivity().applicationContext, "Adding Item", "Please wait")


        //val name = editTextItemName!!.text.toString().trim { it <= ' ' }
        val brand = textViewSummary!!.text.toString().trim { it <= ' ' }
        var dataAlphanumeric = "00"
        val currentDate = LocalDateTime.now()
        var monthNumber = currentDate.monthValue.toString()
        var dateNumber = currentDate.dayOfMonth.toString()
        dateNumber = etCurrentDate.text.toString()

        dataAlphanumeric = if(cbCheckList00.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList01.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList02.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList03.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList04.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList05.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList06.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList07.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList08.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList09.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList10.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList11.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList12.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList13.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList14.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList15.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList16.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList17.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList18.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList19.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList20.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList21.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList22.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList23.isChecked) {
            "1"
        } else {
            "0"
        }
        dataAlphanumeric += if(cbCheckList24.isChecked) {
            "1"
        } else {
            "0"
        }

        dataAlphanumeric+="a"

        val stringRequest: StringRequest =
            object : StringRequest(
                Method.POST, "https://script.google.com/macros/s/AKfycbxKp0kp8Bgotjv81AgwnkROZ5MI8U19Hmq0r6GLAc36Gb_MNdA/exec",
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

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri?)
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
        if (v === buttonDate) {
                //https://stackoverflow.com/questions/27225815/android-how-to-show-datepicker-in-fragment
        }
    }
}