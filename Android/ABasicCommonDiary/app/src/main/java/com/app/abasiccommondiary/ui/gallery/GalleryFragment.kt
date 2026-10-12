package com.app.abasiccommondiary.ui.gallery

import android.annotation.SuppressLint
import android.content.Context
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
import com.app.abasiccommondiary.databinding.FragmentGalleryBinding
import com.app.abasiccommondiary.R
import java.time.LocalDateTime
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.text.DecimalFormat
import java.util.regex.Pattern
import kotlin.String
import kotlin.toString

class GalleryFragment : Fragment(), View.OnClickListener {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null
    private lateinit var textViewDate: TextView
    private lateinit var textViewSummary: TextView
    private var textViewGallery = ArrayList<TextView>()
    private var textViewGalleryTime = ArrayList<TextView>()
    private lateinit var buttonSaveTask: Button
    private lateinit var buttonLoadTask: Button
    private lateinit var buttonPrevTask: Button
    private lateinit var textViewMonthDate: TextView
    private lateinit var buttonNextTask: Button
    private lateinit var textHome: TextView
    var darkMode = ""
    //val strDaySequence = arrayOf<String>("Yesterday","Today","Tomorrow")
    val strDaySequence = mapOf(-1 to "Yesterday",0 to "Today",1 to "Tomorrow")


    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textViewGallery = arrayListOf<TextView>(
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
        textViewGalleryTime = arrayListOf<TextView>(
            binding.B00,binding.B01,binding.B02,binding.B03,binding.B04,binding.B05,binding.B06,binding.B07,binding.B08,binding.B09,
            binding.B10,binding.B11,binding.B12,binding.B13,binding.B14,binding.B15,binding.B16,binding.B17,binding.B18,binding.B19,
            binding.B20,binding.B21,binding.B22,binding.B23,binding.B24,binding.B25,binding.B26,binding.B27,binding.B28,binding.B29,
            binding.B30,binding.B31,binding.B32,binding.B33,binding.B34)
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

        val textView: TextView = binding.textHome
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = ""

            for (i in 0..34) {
                textViewGallery[i].setTextColor(colorString2) //Set Gray color
            }
        })

        val currentDate1 = LocalDateTime.now()
        var strWeekName1 = currentDate1.dayOfWeek.toString()
        val timeWFO = arrayOf<String>("2300","630", "640", "700", "704", "706", "716", "720", "840", "842", "852", "854", "900", "904", "1300", "1700", "1702", "1704", "1830", "1856", "1858", "2030", "2032", "2130", "2145", "2200", "2230", "2245", "2245", "2246", "2249", "2250", "2252", "2255", "2300")
        val timeWFH = arrayOf<String>("2300","630", "640", "700", "704", "706", "716", "720", "840", "842", "852", "854", "900", "904", "1300", "1700", "1702", "1704", "1830", "1856", "1858", "2030", "2032", "2130", "2145", "2200", "2230", "2245", "2245", "2246", "2249", "2250", "2252", "2255", "2300")
        val timeSAT = arrayOf<String>("2300","630", "640", "700", "704", "706", "716", "720", "840", "842", "852", "854", "900", "904", "1300", "1700", "1702", "1704", "1830", "1856", "1858", "2030", "2032", "2130", "2145", "2200", "2230", "2245", "2245", "2246", "2249", "2250", "2252", "2255", "2300")
        val timeSUN = arrayOf<String>("2300","630", "640", "700", "704", "706", "716", "720", "840", "842", "852", "854", "900", "904", "1300", "1700", "1702", "1704", "1830", "1856", "1858", "2030", "2032", "2130", "2145", "2200", "2230", "2245", "2245", "2246", "2249", "2250", "2252", "2255", "2300")
        val taskIcon: List<String> = resources.getStringArray(R.array.font_icon).toList()

        for(i in 0..34){
            textViewGallery[i].setOnClickListener(this)
            if(strWeekName1.equals("SATURDAY"))
                textViewGalleryTime[i].setText(timeSAT[i])
            else if(strWeekName1.equals("SUNDAY"))
                textViewGalleryTime[i].setText(timeSUN[i])
            else if(strWeekName1.equals("THURSDAY") || strWeekName1.equals("FRIDAY"))
                textViewGalleryTime[i].setText(timeWFH[i])
            else{
                textViewGalleryTime[i].setText(timeWFO[i])
                textViewGallery[i].setText(taskIcon.get(i))
            }
        }

        buttonSaveTask.setOnClickListener(this)
        buttonLoadTask.setOnClickListener(this)
        buttonPrevTask.setOnClickListener(this)
        buttonNextTask.setOnClickListener(this)

        loadTasks()
        return root
    }

    override fun onClick(v: View) {
        //Define what to do when button is clicked
        if (v === buttonSaveTask) {
            saveTasksToFile()
            buttonSaveTask.isEnabled=false
        }
        if (v === buttonLoadTask) {
            loadTasks()
            buttonSaveTask.isEnabled=true
        }
        if (v === buttonPrevTask) {
            loadPreviousTasks()//https://stackoverflow.com/questions/27225815/android-how-to-show-datepicker-in-fragment
        }
        if (v === buttonNextTask) {
            loadNextTasks()//https://stackoverflow.com/questions/27225815/android-how-to-show-datepicker-in-fragment
        }
        for(i in 0..34) {
            if(v===textViewGallery[i]){
                if(textViewGallery[i].currentTextColor==Color.GRAY){
                    textViewGallery[i].setTextColor(Color.CYAN)}
                else if(textViewGallery[i].currentTextColor==Color.CYAN){
                    textViewGallery[i].setTextColor(Color.GRAY)}
                else if(textViewGallery[i].currentTextColor==Color.BLACK){
                    textViewGallery[i].setTextColor(Color.GREEN)}
                else if(textViewGallery[i].currentTextColor==Color.GREEN){
                    textViewGallery[i].setTextColor(Color.BLACK)}
            }
        }
    }

    private fun loadTasks(){
        var strDate04 = "5555"
        val strSummaryText = "Loading Tasks ..."
        textViewSummary.text = strSummaryText
        val currentDate = LocalDateTime.now()
        var monthNumber = currentDate.monthValue
        var currentYear = currentDate.year
        var dateNumber = "ABCD"
        var adjustDate = ""
        var fileNameCSV = ""

        if(textViewMonthDate.text.contains("Enter the Date",true)){
            dateNumber = currentDate.dayOfMonth.toString() + "," + currentDate.monthValue.toString()+",0"
            textViewMonthDate.setText("" + dateNumber)
            dateNumber = currentDate.dayOfMonth.toString()
        }
        else{
            val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
            dateNumber = arr[0]// textViewMonthDate.text.toString()
            adjustDate = arr[2]
        }
        if(currentDate.monthValue<10)
            fileNameCSV = (currentYear*10).toString() + currentDate.monthValue.toString()+".csv"
        else
            fileNameCSV = (currentYear).toString() + currentDate.monthValue.toString()+".csv"

        val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
        var tomorrow = currentDate.plusDays(arr[2].toLong())

        textViewDate.text = strDate04
        strDate04 = tomorrow.dayOfWeek.toString().substring(0,3) +"-"+ tomorrow.year.toString()+"-"+ tomorrow.monthValue.toString()+"-"+tomorrow.dayOfMonth.toString()
        textViewDate.text = strDate04

        //Need to execute this on first of every month

        val targetFile = File(context?.filesDir, fileNameCSV)
        if (!targetFile.exists()) {
            var strCompleteCSVFile = writeInitialCSVFile(context, fileNameCSV, 6)
            Log.d("AAAAAAAAAAAAAAAAAAAA", strCompleteCSVFile.size.toString())
        }

        var csvData2D = readLocalCSV(context,fileNameCSV)
        val mutableList: MutableList<MutableList<String>> = csvData2D
            .map { it.toMutableList() }
            .toMutableList()
        updateUI(mutableList, dateNumber)
    }

    private fun saveTasksToFile(){
        val strSummaryText = "Loading Tasks ..."
        textViewSummary.text = strSummaryText
        val currentDate = LocalDateTime.now()
        var monthNumber = currentDate.monthValue
        var currentYear = currentDate.year
        var dateNumber = ""
        var adjustDate = ""
        var fileNameCSV = ""

        if(textViewMonthDate.text.contains("Enter the Date",true)){
            dateNumber = currentDate.dayOfMonth.toString() + "," + currentDate.monthValue.toString()+",0"
            textViewMonthDate.setText("" + dateNumber)
            dateNumber = currentDate.dayOfMonth.toString()
        }
        else{
            val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
            dateNumber = arr[0]// textViewMonthDate.text.toString()
            adjustDate = arr[2]
        }
        if(currentDate.monthValue<10)
            fileNameCSV = (currentYear*10).toString() + currentDate.monthValue.toString()+".csv"
        else
            fileNameCSV = (currentYear).toString() + currentDate.monthValue.toString()+".csv"


        //fileNameCSV = (currentYear*10).toString() + currentDate.monthValue.toString()+".csv"


        var csvData2D = readLocalCSV(context,fileNameCSV)
        Log.d("AAAA", csvData2D.toString())

        val mutableList: MutableList<MutableList<String>> = csvData2D
            .map { it.toMutableList() }
            .toMutableList()
        Log.d("BBBB", mutableList.get(0).get(0)+"..." + mutableList.get(0).get(30)+"..." + mutableList.get(37).get(0) + "..." + mutableList.get(37).get(30))

        val brand = textViewSummary!!.text.toString().trim { it <= ' ' }
        var dataAlphanumeric = ""
        //dateNumber = textViewMonthDate.text.toString()

        val arr = Pattern.compile(",").split(textViewMonthDate.text.toString())
        dateNumber = arr[0]// textViewMonthDate.text.toString()

        var monthTotal = 0
        for(i in 1..35) {
            if (textViewGallery[i-1].currentTextColor == Color.CYAN) {
                mutableList.get(i).set(dateNumber.toInt() - 1, "1")
                monthTotal++
            } else {
                mutableList.get(i).set(dateNumber.toInt() - 1, "0")
            }
        }

        // Calculate First Day's Month Total
        mutableList.get(37).set(0,mutableList.get(36).get(0))
        // Calculate Day Total
        mutableList.get(36).set(dateNumber.toInt() - 1, monthTotal.toString())
        Log.d("CCCC", "First Day and Current Day total"  + mutableList.get(37).get(0) + " ... " + mutableList.get(36).set(dateNumber.toInt() - 1, monthTotal.toString()))

        for(i in 1..30) {
            var tempSum = mutableList.get(37).get(i-1).toInt() + mutableList.get(36).get(i).toInt()
            mutableList.get(37).set(i,tempSum.toString())
        }

        println("Stored CSV List structure1:")
        mutableList.forEach { row ->
            println(row)
        }

        // Write updated data back to CSV file
        var stringABC = writeCheckListDataCSVFile(context, fileNameCSV, mutableList,6)
        Log.d("DDDD", stringABC)

        var listListTask2 = readCompleteCSV(context,fileNameCSV)
        Log.d("EEEE", listListTask2.toString())

        updateUI(mutableList, dateNumber)
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

    @SuppressLint("SetTextI18n")
    private fun updateUI(listMonthTasks: MutableList<MutableList<String>>, dateNumber: String) {
        var strDailyCount = listMonthTasks.get(36).get(dateNumber.toInt()-1)
        var strMonthlyCount = listMonthTasks.get(37).get(dateNumber.toInt()-1)
        textViewSummary!!.text = "Task Count: $strDailyCount, $strMonthlyCount"
        Log.d("FFFF: Monthly: ", strMonthlyCount + " Date: " + dateNumber +" Daily: " + strDailyCount)

        for(i in 1..35){
            if(listMonthTasks.get(i).get(dateNumber.toInt()-1).equals("1")) {
                textViewGallery[i-1].setTextColor(Color.CYAN)
            }
            else{
                textViewGallery[i-1].setTextColor(Color.GRAY)
            }
        }
        val df = DecimalFormat("#.##")
        var totalPercentage = df.format ((strMonthlyCount.toFloat() / (dateNumber.toFloat() * 35.0)) * 100.0)

        val strCurrentDay = textViewMonthDate!!.text.split(",")
        var strWhichDay = ""
        strWhichDay = strDaySequence[strCurrentDay.get(2).toInt()].toString()
        Log.d("1@@@@@@@@@@@", "...:" + strWhichDay)
        //if(strCurrentDay.get(2).toInt()==dateNumber.toInt())
        //strDaySequence)

        textViewSummary!!.text= strWhichDay + ": $strDailyCount of 35 | Total: $strMonthlyCount of " + (dateNumber.toInt() * 35) + " ($totalPercentage % )"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun readSingleMonthCSVFile(context: Context?, fileName: String, monthNumber: Int): MutableList<String> {
        var abcString: String? = ""
        val listMonthTasks : MutableList<String> = mutableListOf()
        try {
            val inputStream = context?.assets?.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            //val readHeader = reader.readLine() // Skip the header line
            var line: String? = reader.readLine()
            while (line != null) {
                val tokens = line.split(",")
                if(tokens[monthNumber-1]!="\n"){
                    listMonthTasks.add(tokens[monthNumber-1].toString())
                    abcString = abcString.toString() + ","+tokens[monthNumber-1].toString()
                }
                line = reader.readLine()
            }
            reader.close()

        }
        catch (e: Exception){
            e.printStackTrace()
        }
        Log.d("GGGG", "20260620..." + listMonthTasks.size +"..."+ listMonthTasks.toString())
        return listMonthTasks
    }

    private fun readWriteCsvFile(context: Context?, fileName: String, monthNumber: Int): String {
        val data = listOf(
            listOf("Name", "Age", "City"),
            listOf("Alice", "30", "New York"),
            listOf("Bob", "25", "London")
        )
        return try {
            val file = File(context?.filesDir, fileName)
            file.bufferedWriter().use { out ->
                data.forEach { row ->
                    out.write(row.joinToString("H,"))
                    out.newLine()
                    Log.d("HHHH", "ABCD")
                }
            }
            Log.d("IIII", "..." + context?.filesDir + "..." + file.absoluteFile+"..."+file.absolutePath+"..."+file.name)
            "File written successfully..."

        } catch (e: Exception) {
            e.printStackTrace()
            "Error writing file: ${e.message}"
        }
    }

    fun writeInitialCSVFile(context: Context?, fileName: String, monthNumber: Int): List<String> {
        var abcString = ""
        // Read Asset file
        Log.d("ZZZZZZZZZZZZZZZZZZZZZZZZ", "...:" + fileName )

        val inputStream = context?.assets?.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        //val readHeader = reader.readLine() // Skip the header line
        var lines: List<String> = reader.readLines()
        abcString = abcString + lines.size

        Log.d("JJJJ", "...:" + abcString +" ... " + lines.get(0)+" ... " + lines.get(37))

        // Copy Asset file to local folder
        val targetFile = File(context?.filesDir, fileName)

        // Skip copying if the file already exists in internal storage
        if (!targetFile.exists()) {
            Log.d("JJJJ", "File not exist")

            try {
                context?.assets?.open(fileName).use { inputStream ->
                    targetFile.outputStream().use { outputStream ->
                        inputStream?.copyTo(outputStream)
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        //Write to local copy
        try {
            val file = File(context?.filesDir, fileName)
            file.writeText("") // Make file blank
            for(i in 0..37) {
                file.appendText(lines.get(i)) // Overwrites or creates the file
                file.appendText("\n")
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


        //var lines2 = readCompleteLocalCSV(context,fileNameCSV)

        //Read from local copy
        var lines2 = listOf<String>()
        try {
            val file = File(context?.filesDir, fileName)
            if (file.exists())
                lines2 = file.readLines()
            else
                abcString = "File does not exist"
        }
        catch (e: Exception) {
            e.printStackTrace()
        }

        Log.d("KKKK", lines2.get(0) + " ... " + lines2.get(37) +"...")
        return lines2
    }

    fun readCompleteCSV(context: Context?, fileName: String): List<List<String>> {
        var abcString = "Reading the file..." + fileName +" ... "
        var listComleteFile = mutableListOf<MutableList<String>>()



        val file = File(context?.filesDir, fileName)


        // 1. Read all lines from the file
        val lines: List<String> = file.readLines()

        // 2. Parse lines into a List of Lists (Rows and Columns)
        // Optional: Use .drop(1) if you want to skip a header row
        val csvData: List<List<String>> = lines.map { line ->
            line.split(",").map { it.trim() }
        }

        // 3. Print the resulting list to verify
        println("Stored CSV List structure:")
        csvData.forEach { row ->
            println(row)
        }
        return csvData
    }

    fun writeCheckListDataCSVFile(context: Context?, fileName: String, listMonthTasks: MutableList<MutableList<String>>, monthNumber: Int): String {
        var abcString = "Reading the file..." + fileName + " ... "
        // Read Asset file

        val inputStream = context?.assets?.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        //val readHeader = reader.readLine() // Skip the header line
        var line: String? = reader.readLine()
        abcString = abcString + line

        // Copy Asset file to local folder
        val targetFile = File(context?.filesDir, fileName)

        // Skip copying if the file already exists in internal storage
        if (!targetFile.exists()) {
            try {
                context?.assets?.open(fileName).use { inputStream ->
                    targetFile.outputStream().use { outputStream ->
                        inputStream?.copyTo(outputStream)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        //Write to local copy
        try {
            val file = File(context?.filesDir, fileName)
            file.writeText("")
            for (i in 0..37) {
                for(j in 0..30){
                    file.appendText(listMonthTasks[i][j])
                    if(j<30){
                        file.appendText(",")
                    }
                    else
                        file.appendText("\n")
                }
            }
            //file.writeText("After copying the file") // Overwrites or creates the file
            //file.appendText("Appending ")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.d("MMMM", listMonthTasks.toString())

        return "ABC"
    }

    fun readLocalCSV(context: Context?, fileName: String): List<List<String>> {
        val file = File(context?.filesDir, fileName)
        Log.d("IIII", "..." + context?.filesDir + "..." + file.absoluteFile+"..."+file.absolutePath+"..."+file.name)

        val csvData2D = mutableListOf<List<String>>()

        // Read the file line-by-line efficiently without loading the whole file into memory
        file.forEachLine { line ->
            // Split each row by the comma (or provided delimiter) and add to the 2D list
            val row = line.split(",")
            csvData2D.add(row)
        }

        Log.d("OOOO", csvData2D.get(0).get(0) + "..." + csvData2D.get(0).size.toString())
        Log.d("OOOO", csvData2D.get(1).get(0) + "..." + csvData2D.get(0).size.toString())
//        Log.d("OOOO", csvData2D.get(0).get(0) + "..." + csvData2D.get(1).get(30) + "..." + csvData2D.get(37).get(0)+"..." +csvData2D.get(37).get(30)+"...")

        // 3. Print the resulting list to verify
        println("Stored CSV List structure:")
        csvData2D.forEach { row ->
            println(row)
        }

        return csvData2D
    }

    fun readAssetsCSV(context: Context?, fileName: String): List<List<String>> {
        //Read from Assets folder copy
        val csvData2D = mutableListOf<List<String>>()

        try {
            // Open the file from the assets folder using the provided context
            context?.assets?.open(fileName).use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
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

        Log.d("PPPP", csvData2D.get(0).get(0)+"..." + csvData2D.get(0).get(30)+"..." + csvData2D.get(37).get(0) + "..." + csvData2D.get(37).get(30))
        return csvData2D
    }
}
