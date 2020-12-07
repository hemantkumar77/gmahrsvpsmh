package com.kumar.hemant.traintimetable2009.ui.gallery

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
import com.kumar.hemant.traintimetable2009.ui.gallery.GalleryViewModel
import kotlinx.android.synthetic.main.fragment_check_list.*
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.json.JSONException
import org.json.JSONObject
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.util.*

class GalleryFragment : Fragment(), View.OnClickListener {

    private lateinit var galleryViewModel: GalleryViewModel
    var buttonSaveTask: Button? = null
    var buttonLoadTask: Button? = null
    var buttonDeleteTask: Button? = null
    var buttonDate: Button? = null
    var textViewSummary: TextView? = null
    var textViewDate: TextView? = null
    var tvTitle : TextView? = null
    var etStateCode : EditText? = null
    var etRTOCode : EditText? = null
    var btDetail : Button? = null
    var tvSummary : TextView? = null
    var selectedDate = "Hemant"
    private val mListener: OnFragmentInteractionListener? = null
    val REQUEST_CODE = 11

    fun GalleryFragment() {
        // Required empty public constructor
    }

    fun newInstance() {
        return GalleryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel = ViewModelProviders.of(this).get(galleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        tvTitle = root.findViewById(R.id.txtSummary) as TextView
        etStateCode = root.findViewById(R.id.etRTOState) as EditText
        etRTOCode = root.findViewById(R.id.etRTOCode) as EditText
        tvSummary = root.findViewById(R.id.tvSummary) as TextView
        btDetail = root.findViewById<View>(R.id.btDetail) as Button
        btDetail!!.setOnClickListener(this)

        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
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

        loadData()
        //populate the dropdowns

        val fm = (activity as AppCompatActivity?)!!.supportFragmentManager
        return root
    }
    private fun loadData(){
        //loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
        textViewSummary!!.text = "555.  Got the RTO Details Yey..."

        var strStateCode = "MH"
        var strRTOCode = "14"

        strRTOCode = etRTOCode?.text.toString()
        strStateCode = etStateCode?.text.toString()

        Log.d("BBBBBBBBBBBBBBBBBBBBBBBBBBBB", "https://script.google.com/macros/s/AKfycbxKp0kp8Bgotjv81AgwnkROZ5MI8U19Hmq0r6GLAc36Gb_MNdA/exec?action=getItems&stateCode="+strStateCode+"&rtoCode="+strRTOCode)
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://script.google.com/macros/s/AKfycbxKp0kp8Bgotjv81AgwnkROZ5MI8U19Hmq0r6GLAc36Gb_MNdA/exec?action=getRTODetails&stateCode=" + strStateCode + "&rtoCode=" + strRTOCode,
            Response.Listener { response -> parseItems(response) },
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

    private fun parseItems(jsonResponse: String) {
        val list =
            ArrayList<HashMap<String, String?>>()
        textViewSummary!!.text = "666.  $jsonResponse"
/*
        try {
            var txt01 = jsonResponse.substring(8)
            val jobj = JSONObject(txt01)
            val jarray = jobj.getJSONArray("items")
            //val monthName = "June"

            var str02 = "001"

            for (i in 0 until jarray.length()) {
                val jo = jarray.getJSONObject(i)
                val itemName = jo.getString("1")
*/
/*
                cbCheckList01.isChecked = !(i==0 && itemName=="0")
                cbCheckList02.isChecked = !(i==1 && itemName=="0")
                cbCheckList03.isChecked = !(i==2 && itemName=="0")
                cbCheckList04.isChecked = !(i==3 && itemName=="0")
                cbCheckList05.isChecked = !(i==4 && itemName=="0")
*//*


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
*/

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

    /*private fun updateTaskToSheet() {
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

    }*/

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri?)
    }

    override fun onClick(v: View) {
        if (v === buttonSaveTask) {
            //updateTaskToSheet()

            //Define what to do when button is clicked
        }
        if (v === buttonLoadTask) {
            loadData()

            //Define what to do when button is clicked
        }
        if (v === buttonDate) {
            //https://stackoverflow.com/questions/27225815/android-how-to-show-datepicker-in-fragment
        }
    }
}