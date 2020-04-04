package com.kumar.hemant.traintimetable2002.check_list

import android.content.pm.PackageManager
import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log

/*
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
*/
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.kumar.hemant.traintimetable2002.R
import com.kumar.hemant.traintimetable2002.RTO.RTO
/*
import com.kumar.hemant.traintimetable2002.check_list1.RTO.RTOActivity
import com.kumar.hemant.traintimetable2002.check_list1.RTO.RTOActivity
import com.kumar.hemant.traintimetable2002.check_list1.Train.MVCView
import com.kumar.hemant.traintimetable2002.check_list1.Train.SearchStationActivity
import com.kumar.hemant.traintimetable2002.check_list1.Train.Train
import com.kumar.hemant.traintimetable2002.check_list1.Train.TrainActivity
import com.kumar.hemant.traintimetable2002.check_list1.Train.TrainActivity
import com.kumar.hemant.traintimetable2002.check_list1.Train.TrainsAdapter
*/
import java.io.File
import java.lang.Exception
import java.text.*
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import kotlin.collections.ArrayList
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList

class check_list : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    /* Documentation
    https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html#foreground.type=clipart&foreground.clipart=beach_access&foreground.space.trim=1&foreground.space.pad=0.25&foreColor=rgb(63%2C%2081%2C%20181)&backColor=rgb(244%2C%2067%2C%2054)&crop=0&backgroundShape=square&effects=shadow&name=ic_launcher
    https://turreta.com/2017/07/07/how-to-write-xml-in-kotin-using-dom/
    https://turreta.com/2017/07/07/how-to-read-xml-in-kotlin-using-dom-parser/
    */
    private lateinit var btnDate : Button
    private lateinit var btnLoad : Button
    private lateinit var btnSave : Button
    private val cal = Calendar.getInstance()
    private var mYear : Int = 2020
    private var mMonth: Int = 0
    private var mDay : Int = 0
    private var mHour : Int = 0
    private var mMinute : Int = 0
    private lateinit var tvSummary : TextView
    private lateinit var tvDate : TextView
    private lateinit var tvInfo : TextView
    private var strInfo : String = ""
    private var dateString : String = ""
    private var dataSource = ActionsDataSource(this)
    private lateinit var fileXML: File
    private lateinit var cbChecklist01 : CheckBox
    private lateinit var cbChecklist02 : CheckBox
    private lateinit var cbChecklist03 : CheckBox
    private lateinit var cbChecklist04 : CheckBox
    private lateinit var cbChecklist05 : CheckBox
    private lateinit var cbChecklist06 : CheckBox
    private lateinit var cbChecklist07 : CheckBox
    private lateinit var cbChecklist08 : CheckBox
    private lateinit var cbChecklist09 : CheckBox
    private lateinit var cbChecklist10 : CheckBox
    private lateinit var cbChecklist11 : CheckBox
    private lateinit var cbChecklist12 : CheckBox
    private lateinit var cbChecklist13 : CheckBox
    private lateinit var cbChecklist14 : CheckBox
    private lateinit var cbChecklist15 : CheckBox
    private lateinit var cbChecklist16 : CheckBox
    private lateinit var cbChecklist17 : CheckBox
    private lateinit var cbChecklist18 : CheckBox
    private lateinit var cbChecklist19 : CheckBox
    private lateinit var cbChecklist20 : CheckBox
    private lateinit var cbChecklist21 : CheckBox
    private lateinit var cbChecklist22 : CheckBox
    private lateinit var cbChecklist23 : CheckBox
    private lateinit var cbChecklist24 : CheckBox
    private lateinit var cbChecklist25 : CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_list)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        dataSource.open()
        tvInfo = findViewById(R.id.tv_info)
        tvDate = findViewById(R.id.tv_date)
        dateString = dataSource.stringDate

        //strInfo += dateString.toString()
        tvDate.text = dateString
        val dateSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }
        btnDate = findViewById(R.id.datebtn) as Button
        btnDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                DatePickerDialog(this@check_list,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        btnLoad = findViewById(R.id.loadbtn) as Button
        btnLoad!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                updateDateInView()
                loadTasks()
            }
        })

        btnSave = findViewById(R.id.savebtn) as Button
        btnSave!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                updateDateInView()
                saveTasks()
            }
        })


        if(checkAndRequestPermissions())
            Log.e("Permission Granted....","...Date: "+"11111111")
        loadTasks()


        updateDateInView()
        tvSummary = findViewById(R.id.txtSummary)

    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                val i = Intent(this, check_list::class.java).apply {  }
                startActivity(i)
            }
            R.id.nav_gallery -> {
                val i = Intent(this, RTO::class.java).apply {  }
                startActivity(i)
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun updateDateInView() {
        val myFormat = "EEE-yyyyMMdd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat)
        tvDate.text = sdf.format(cal.getTime())
        strInfo += sdf.format(cal.getTime()).toString()
    }

    private fun loadTasks(){
        cbChecklist01 = findViewById(R.id.A01)
        cbChecklist02 = findViewById(R.id.A02)
        cbChecklist03 = findViewById(R.id.A03)
        cbChecklist04 = findViewById(R.id.A04)
        cbChecklist05 = findViewById(R.id.A05)
        cbChecklist06 = findViewById(R.id.A06)
        cbChecklist07 = findViewById(R.id.A07)
        cbChecklist08 = findViewById(R.id.A08)
        cbChecklist09 = findViewById(R.id.A09)
        cbChecklist10 = findViewById(R.id.A10)
        cbChecklist11 = findViewById(R.id.A11)
        cbChecklist12 = findViewById(R.id.A12)
        cbChecklist13 = findViewById(R.id.A13)
        cbChecklist14 = findViewById(R.id.A14)
        cbChecklist15 = findViewById(R.id.A15)
        cbChecklist16 = findViewById(R.id.A16)
        cbChecklist17 = findViewById(R.id.A17)
        cbChecklist18 = findViewById(R.id.A18)
        cbChecklist19 = findViewById(R.id.A19)
        cbChecklist20 = findViewById(R.id.A20)
        cbChecklist21 = findViewById(R.id.A21)
        cbChecklist22 = findViewById(R.id.A22)
        cbChecklist23 = findViewById(R.id.A23)
        cbChecklist24 = findViewById(R.id.A24)
        cbChecklist25 = findViewById(R.id.A25)


        fileXML = File("/storage/emulated/0/TrainTimeTable/" + tvDate.text.substring(4,10) + ".xml")
        val xmlDoc : Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileXML)
        xmlDoc.documentElement.normalize()
        //strInfo += "Root Node: " +xmlDoc.documentElement.nodeName+"... "
        //tvInfo.text = strInfo
        val dayList : NodeList = xmlDoc.getElementsByTagName("day")
        //var intDate : Int  = dataSource.getIntDate(tvDate.text.toString())%100
        var intDate : Int  = 1 //dataSource.getIntDate(tvDate.text.toString())%100
        intDate = tvDate.text.substring(10,12).toInt()


        val dayNode : Node = dayList.item(intDate-1)
        if(dayNode.nodeType===Node.ELEMENT_NODE)
        {
            val dayElement = dayNode as Element
            val mMap = mutableMapOf<String, String>()
            for(j in 0..dayElement.attributes.length-20)
            {
                mMap.putIfAbsent(dayElement.attributes.item(j).nodeName,dayElement.attributes.item(j).nodeValue)
            }
            //strInfo += "Current value is : ${dayNode.nodeName} - $mMap"
            //strInfo += "Task1: ${dayElement.getElementsByTagName("task1").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task1").item(0).textContent}".equals("1"))
                cbChecklist01.isChecked = true
            else
                cbChecklist01.isChecked = false

            //strInfo += "Task1: ${dayElement.getElementsByTagName("task2").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task2").item(0).textContent}".equals("1"))
                cbChecklist02.isChecked = true
            else
                cbChecklist02.isChecked = false

            //strInfo += "Task1: ${dayElement.getElementsByTagName("task3").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task3").item(0).textContent}".equals("1"))
                cbChecklist03.isChecked = true
            else
                cbChecklist03.isChecked = false

            //strInfo += "Task1: ${dayElement.getElementsByTagName("task4").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task4").item(0).textContent}".equals("1"))
                cbChecklist04.isChecked = true
            else
                cbChecklist04.isChecked = false

            //strInfo += "Task1: ${dayElement.getElementsByTagName("task5").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task5").item(0).textContent}".equals("1"))
                cbChecklist05.isChecked = true
            else
                cbChecklist05.isChecked = false

            //strInfo += "Task1: ${dayElement.getElementsByTagName("task6").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task6").item(0).textContent}".equals("1"))
                cbChecklist06.isChecked = true
            else
                cbChecklist06.isChecked = false

            //strInfo += "Task1: ${dayElement.getElementsByTagName("task7").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task7").item(0).textContent}".equals("1"))
                cbChecklist07.isChecked = true
            else
                cbChecklist07.isChecked = false

            //strInfo += "Task1: ${dayElement.getElementsByTagName("task8").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task8").item(0).textContent}".equals("1"))
                cbChecklist08.isChecked = true
            else
                cbChecklist08.isChecked = false

            //strInfo += "Task1: ${dayElement.getElementsByTagName("task9").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task9").item(0).textContent}".equals("1"))
                cbChecklist09.isChecked = true
            else
                cbChecklist09.isChecked = false

            //strInfo += "Task1: ${dayElement.getElementsByTagName("task10").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task10").item(0).textContent}".equals("1"))
                cbChecklist10.isChecked = true
            else
                cbChecklist10.isChecked = false

            //strInfo += "Task11: ${dayElement.getElementsByTagName("task11").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task11").item(0).textContent}".equals("1"))
                cbChecklist11.isChecked = true
            else
                cbChecklist11.isChecked = false

            //strInfo += "Task12: ${dayElement.getElementsByTagName("task12").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task12").item(0).textContent}".equals("1"))
                cbChecklist12.isChecked = true
            else
                cbChecklist12.isChecked = false

            //strInfo += "Task13: ${dayElement.getElementsByTagName("task13").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task13").item(0).textContent}".equals("1"))
                cbChecklist13.isChecked = true
            else
                cbChecklist13.isChecked = false

            //strInfo += "Task14: ${dayElement.getElementsByTagName("task14").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task14").item(0).textContent}".equals("1"))
                cbChecklist14.isChecked = true
            else
                cbChecklist14.isChecked = false

            //strInfo += "Task15: ${dayElement.getElementsByTagName("task15").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task15").item(0).textContent}".equals("1"))
                cbChecklist15.isChecked = true
            else
                cbChecklist15.isChecked = false

            //strInfo += "Task16: ${dayElement.getElementsByTagName("task16").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task16").item(0).textContent}".equals("1"))
                cbChecklist16.isChecked = true
            else
                cbChecklist16.isChecked = false

            //strInfo += "Task17: ${dayElement.getElementsByTagName("task17").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task17").item(0).textContent}".equals("1"))
                cbChecklist17.isChecked = true
            else
                cbChecklist17.isChecked = false

            //strInfo += "Task18: ${dayElement.getElementsByTagName("task18").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task18").item(0).textContent}".equals("1"))
                cbChecklist18.isChecked = true
            else
                cbChecklist18.isChecked = false

            //strInfo += "Task19: ${dayElement.getElementsByTagName("task19").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task19").item(0).textContent}".equals("1"))
                cbChecklist19.isChecked = true
            else
                cbChecklist19.isChecked = false

            //strInfo += "Task20: ${dayElement.getElementsByTagName("task20").item(0).textContent}"
            if("${dayElement.getElementsByTagName("task20").item(0).textContent}".equals("1"))
                cbChecklist20.isChecked = true
            else
                cbChecklist20.isChecked = false
            if("${dayElement.getElementsByTagName("task21").item(0).textContent}".equals("1"))
                cbChecklist21.isChecked = true
            else
                cbChecklist21.isChecked = false
            if("${dayElement.getElementsByTagName("task22").item(0).textContent}".equals("1"))
                cbChecklist22.isChecked = true
            else
                cbChecklist22.isChecked = false
            if("${dayElement.getElementsByTagName("task23").item(0).textContent}".equals("1"))
                cbChecklist23.isChecked = true
            else
                cbChecklist23.isChecked = false
            if("${dayElement.getElementsByTagName("task24").item(0).textContent}".equals("1"))
                cbChecklist24.isChecked = true
            else
                cbChecklist24.isChecked = false
            if("${dayElement.getElementsByTagName("task25").item(0).textContent}".equals("1"))
                cbChecklist25.isChecked = true
            else
                cbChecklist25.isChecked = false
        }
        tvInfo.setText(strInfo)
    }

    private fun saveTasks(){
        cbChecklist01 = findViewById(R.id.A01)
        cbChecklist02 = findViewById(R.id.A02)
        cbChecklist03 = findViewById(R.id.A03)
        cbChecklist04 = findViewById(R.id.A04)
        cbChecklist05 = findViewById(R.id.A05)
        cbChecklist06 = findViewById(R.id.A06)
        cbChecklist07 = findViewById(R.id.A07)
        cbChecklist08 = findViewById(R.id.A08)
        cbChecklist09 = findViewById(R.id.A09)
        cbChecklist10 = findViewById(R.id.A10)
        cbChecklist11 = findViewById(R.id.A11)
        cbChecklist12 = findViewById(R.id.A12)
        cbChecklist13 = findViewById(R.id.A13)
        cbChecklist14 = findViewById(R.id.A14)
        cbChecklist15 = findViewById(R.id.A15)
        cbChecklist16 = findViewById(R.id.A16)
        cbChecklist17 = findViewById(R.id.A17)
        cbChecklist18 = findViewById(R.id.A18)
        cbChecklist19 = findViewById(R.id.A19)
        cbChecklist20 = findViewById(R.id.A20)
        cbChecklist21 = findViewById(R.id.A21)
        cbChecklist22 = findViewById(R.id.A22)
        cbChecklist23 = findViewById(R.id.A23)
        cbChecklist24 = findViewById(R.id.A24)
        cbChecklist25 = findViewById(R.id.A25)

        fileXML = File("/storage/emulated/0/TrainTimeTable/" + tvDate.text.substring(4,10) + ".xml")
        strInfo += "..From save function.."
        try {
            val xmlDoc: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileXML)
            xmlDoc.documentElement.normalize()
            val docElement : Element = xmlDoc.documentElement
            val dayList: NodeList = xmlDoc.getElementsByTagName("day")
            var intCount : Int = 0
            var intCountDays : Int = 0
            val intDate = tvDate.text.substring(10,12).toInt()
            strInfo += "...Date..." + intDate.toString() + "...Integer..."
            val dayNode : Node = dayList.item(intDate-1)
            if(dayNode.getNodeType() === Node.ELEMENT_NODE) {
                strInfo += "...inside IF..."
                val dayElement = dayNode as Element
                val mMap = mutableMapOf<String, String>()
                for (j in 0..dayElement.attributes.length - 20) {
                    mMap.putIfAbsent(dayElement.attributes.item(j).nodeName, dayElement.attributes.item(j).nodeValue)
                }
                if (cbChecklist01.isChecked){
                    dayElement.getElementsByTagName("task1").item(0).textContent = "1"
                    intCount++
                }
                else
                    dayElement.getElementsByTagName("task1").item(0).textContent="0"
                intCountDays++
                if(cbChecklist02.isChecked){
                    dayElement.getElementsByTagName("task2").item(0).textContent="1"
                    intCount++
                }
                else
                    dayElement.getElementsByTagName("task2").item(0).textContent="0"
                intCountDays++

                if(cbChecklist03.isChecked){
                    dayElement.getElementsByTagName("task3").item(0).textContent="1"
                    intCount++
                }
                else
                    dayElement.getElementsByTagName("task3").item(0).textContent="0"
                intCountDays++

                if(cbChecklist04.isChecked){
                    dayElement.getElementsByTagName("task4").item(0).textContent="1"
                    intCount++
                }
                else
                    dayElement.getElementsByTagName("task4").item(0).textContent="0"
                intCountDays++

                if(cbChecklist05.isChecked){
                    dayElement.getElementsByTagName("task5").item(0).textContent="1"
                    intCount++
                }
                else
                    dayElement.getElementsByTagName("task5").item(0).textContent="0"
                intCountDays++

                if(cbChecklist06.isChecked){
                    dayElement.getElementsByTagName("task6").item(0).textContent="1"
                    intCount++
                }
                else
                    dayElement.getElementsByTagName("task6").item(0).textContent="0"
                intCountDays++

                if(cbChecklist07.isChecked){
                    dayElement.getElementsByTagName("task7").item(0).textContent="1"
                    intCount++
                }
                else
                    dayElement.getElementsByTagName("task7").item(0).textContent="0"
                intCountDays++

                if(cbChecklist08.isChecked){
                    dayElement.getElementsByTagName("task8").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task8").item(0).textContent="0"
                intCountDays++

                if(cbChecklist09.isChecked){
                    dayElement.getElementsByTagName("task9").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task9").item(0).textContent="0"
                intCountDays++

                if(cbChecklist10.isChecked){
                    dayElement.getElementsByTagName("task10").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task10").item(0).textContent="0"
                intCountDays++

                if(cbChecklist11.isChecked){
                    dayElement.getElementsByTagName("task11").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task11").item(0).textContent="0"
                intCountDays++

                if(cbChecklist12.isChecked){
                    dayElement.getElementsByTagName("task12").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task12").item(0).textContent="0"
                intCountDays++

                if(cbChecklist13.isChecked){
                    dayElement.getElementsByTagName("task13").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task13").item(0).textContent="0"
                intCountDays++

                if(cbChecklist14.isChecked){
                    dayElement.getElementsByTagName("task14").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task14").item(0).textContent="0"
                intCountDays++

                if(cbChecklist15.isChecked){
                    dayElement.getElementsByTagName("task15").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task15").item(0).textContent="0"
                intCountDays++

                if(cbChecklist16.isChecked){
                    dayElement.getElementsByTagName("task16").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task16").item(0).textContent="0"
                intCountDays++

                if(cbChecklist17.isChecked){
                    dayElement.getElementsByTagName("task17").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task17").item(0).textContent="0"
                intCountDays++

                if(cbChecklist18.isChecked){
                    dayElement.getElementsByTagName("task18").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task18").item(0).textContent="0"
                intCountDays++

                if(cbChecklist19.isChecked){
                    dayElement.getElementsByTagName("task19").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task19").item(0).textContent="0"
                intCountDays++

                if(cbChecklist20.isChecked){
                    dayElement.getElementsByTagName("task20").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task20").item(0).textContent="0"
                intCountDays++

                if(cbChecklist21.isChecked){
                    dayElement.getElementsByTagName("task21").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task21").item(0).textContent="0"
                intCountDays++

                if(cbChecklist22.isChecked){
                    dayElement.getElementsByTagName("task22").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task22").item(0).textContent="0"
                intCountDays++

                if(cbChecklist23.isChecked){
                    dayElement.getElementsByTagName("task23").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task23").item(0).textContent="0"
                intCountDays++

                if(cbChecklist24.isChecked){
                    dayElement.getElementsByTagName("task24").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task24").item(0).textContent="0"
                intCountDays++

                if(cbChecklist25.isChecked){
                    dayElement.getElementsByTagName("task25").item(0).textContent="1"
                    intCount++}
                else
                    dayElement.getElementsByTagName("task25").item(0).textContent="0"
                intCountDays++
            }
            val transformer1 : Transformer = TransformerFactory.newInstance().newTransformer()
            transformer1.transform(DOMSource(xmlDoc),StreamResult(fileXML))

            //Calculations:

            val dayList2 : NodeList = xmlDoc.getElementsByTagName("day")
            var intDate2 : Int
            var intTotalCount : Int = 0
            var intTotalCountDays : Int = 0
            intDate2 = tvDate.text.substring(10,12).toInt()

            for(m in 0..intDate2 -1){
                var dayNode2 : Node = dayList2.item(m)
                if(dayNode2.nodeType===Node.ELEMENT_NODE)
                {
                    var dayElement2 = dayNode2 as Element
                    if("${dayElement2.getElementsByTagName("task1").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task2").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task3").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task4").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task5").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task6").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task7").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task8").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task9").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task10").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task11").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task12").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task13").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task14").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task15").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task16").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task17").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task18").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task19").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task20").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task21").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task22").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task23").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task24").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                    if("${dayElement2.getElementsByTagName("task25").item(0).textContent}".equals("1")){
                        intTotalCount++
                    }
                    intTotalCountDays++
                }
            }
            strInfo += "\n... ..  Count25..." + intCount.toString() + "...Integer..."
            strInfo += "\n.....CountDay25..." + intCountDays.toString() + "...Integer..."
            strInfo += "\n...TotalCount25..." + intTotalCount.toString() + "...Integer..."
            strInfo += "\nTotalCountDay25..." + intTotalCountDays.toString() + "...Integer..."
            var intTotalCountPercentage : Int
            intTotalCountPercentage = intTotalCount*100/intTotalCountDays

            var strTemp1 : String
            strTemp1 = "Total: "+intTotalCount+"/"+intTotalCountDays+"("+intTotalCountPercentage+"%), Today: "+intCount+"/"+intCountDays
            tvSummary.text = strTemp1
            tvInfo.text = strTemp1
        }
        catch(e:Exception){}
        //strInfo += "....OutSide Try...."
        tvInfo.setText(strInfo)
    }
    /*    fun showDatePicker(){
            val date = DatePickerFragment()
            val calender = Calendar.getInstance()
            var mYear1 : Int = tvDate.text.substring(0,4).toInt()
            var mMonth1 : Int = tvDate.text.substring(5,7).toInt()
            var mDay1 : Int = tvDate.text.substring(8,10).toInt()
            var args = Bundle()
            args.putInt("year", mYear1)
            args.putInt("month", mMonth1)
            args.putInt("day", mDay1)
            date.arguments = args
            date.setCallBack(onDate)
            date.show((supportFragmentManager), "Date Picker")
        }


        private val onDate = OnDateSetListener() {
            fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                var dateString1: String = "" + year.toString() + "" + monthOfYear + "" + dayOfMonth
                var formatter = DateTimeFormatter.ofPattern("yyyy mm dd", Locale.ENGpychLISH)
                //var date1 = LocalDate.parse(dateString1, formatter)
                var dayOfWeek: String = ""

                tvInfo.text = dateString1

            }
        }

        fun onDateSet(view: DatePicker, year: Int,monthOfYear: Int,dayOfMonth: Int){
            var simpleDateFormat = SimpleDateFormat("EEEE")
            //var date1 = Date(mYear,month)
            tvInfo.text = "Into ONDATESET function..."//dateString1
        }*/

    private fun checkAndRequestPermissions(): Boolean {
        val camerapermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val readpermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val writepermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val readcalendarpermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR)
        val writecalendarpermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
        val permissionLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionRecordAudio = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)


        val listPermissionsNeeded = ArrayList<String>()

        if (camerapermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (readpermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (writepermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (readcalendarpermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CALENDAR)
        }
        if (writecalendarpermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_CALENDAR)
        }
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (permissionRecordAudio != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO)
        }
        /*
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), REQUEST_ID_MULTIPLE_PERMISSIONS)
                return false
            }
        */
        return true
        //val perms = arrayOf("android.permission.READ_EXTERNAL_STORAGE",
        // "android.permission.WRITE_EXTERNAL_STORAGE",
        // "android.permission.WRITE_EXTERNAL_STORAGE",
        // "android.permission.READ_CALENDAR",
        // "android.permission.WRITE_CALENDAR")

    }

}
