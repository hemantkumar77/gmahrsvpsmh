package com.example.verificientqa1.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import java.util.Calendar;
import java.io.File;
import android.view.View.OnClickListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import android.widget.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import android.widget.DatePicker;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Environment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActionsDataSource datasource;
    TextView tv2, tvSummary;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private Button dateBtn;
    private CheckBox[] cb1 = new CheckBox [25];
    static final int DATE_DIALOG_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datasource = new ActionsDataSource(this);
        datasource.open();

        tv2 = (TextView) findViewById(R.id.textView1);
        tv2.setText(datasource.getStringDate());
        findViewById(R.id.datebtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        tvSummary=(TextView)findViewById(R.id.txtSummary);
        tvSummary.setText(""+tvSummary.getText()+"... Total");
        String[] perms = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"};
        int permsRequestCode = 200;

        requestPermissions(perms, permsRequestCode);

        loadTasks();
        saveData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        Action action = null;
        //showDatePicker();
        switch (view.getId()) {
            case R.id.save:
                CheckBox[] cb1= new CheckBox [25];
                cb1[0]=(CheckBox) findViewById(R.id.A01);
                cb1[1]=(CheckBox) findViewById(R.id.A02);
                cb1[2]=(CheckBox) findViewById(R.id.A03);
                cb1[3]=(CheckBox) findViewById(R.id.A04);
                cb1[4]=(CheckBox) findViewById(R.id.A05);
                cb1[5]=(CheckBox) findViewById(R.id.A06);
                cb1[6]=(CheckBox) findViewById(R.id.A07);
                cb1[7]=(CheckBox) findViewById(R.id.A08);
                cb1[8]=(CheckBox) findViewById(R.id.A09);
                cb1[9]=(CheckBox) findViewById(R.id.A10);
                cb1[10]=(CheckBox) findViewById(R.id.A11);
                cb1[11]=(CheckBox) findViewById(R.id.A12);
                cb1[12]=(CheckBox) findViewById(R.id.A13);
                cb1[13]=(CheckBox) findViewById(R.id.A14);
                cb1[14]=(CheckBox) findViewById(R.id.A15);
                cb1[15]=(CheckBox) findViewById(R.id.A16);
                cb1[16]=(CheckBox) findViewById(R.id.A17);
                cb1[17]=(CheckBox) findViewById(R.id.A18);
                cb1[18]=(CheckBox) findViewById(R.id.A19);
                cb1[19]=(CheckBox) findViewById(R.id.A20);
                cb1[20]=(CheckBox) findViewById(R.id.A21);
                cb1[21]=(CheckBox) findViewById(R.id.A22);
                cb1[22]=(CheckBox) findViewById(R.id.A23);
                cb1[23]=(CheckBox) findViewById(R.id.A24);
                cb1[24]=(CheckBox) findViewById(R.id.A25);

                File rtoFile= new File(Environment.getExternalStorageDirectory()+"/"+datasource.getIntDate()/100+".xml");
				File f = new File("/mnt/sdcard/t01.png");
				ImageView mImgView1 = (ImageView)findViewById(R.id.imageView);
				Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
				mImgView1.setImageBitmap(bmp);
				
				
				
				
                tvSummary.setText("Good");
                try{
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse(rtoFile);
                    String dateStr= "" + tv2.getText().subSequence(8,10);
                    int date1=(2*(Integer.parseInt(""+dateStr)%100))-1;
                    //int date1=(2*(datasource.getIntDate()%100))-1;
                    int count=0;
                    int countDays=0;
                    Element docEle = doc.getDocumentElement();
                    NodeList nl = docEle.getChildNodes();
                    Element el = (Element) nl.item(date1);
                    String output1="";
                    for(int i=0;i<25;i++){
                        if(cb1[i].isChecked()){
                            el.getElementsByTagName("task"+i).item(0).setTextContent("1");
                            count++;
                        }
                        else{
                            el.getElementsByTagName("task"+i).item(0).setTextContent("0");

                        }
                        countDays++;
                    }
                    Log.v("BBBBBBBBBBBBBBB","...15: ");
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                    // initialize StreamResult with File object to save to file
                    StreamResult result = new StreamResult(rtoFile);
                    DOMSource source = new DOMSource(doc);
                    transformer.transform(source, result);

                    //Month's Calculation
                    int date2=datasource.getIntDate()%100;
                    int totalCount=0, totalCountDays=0;
                    NodeList monthNode = docEle.getChildNodes();
                    if (monthNode != null && monthNode.getLength() > 0) {
                        Log.v("BBBBBBBBBBBBBBB","...Date: "+date2);
                        for (int i = 0; i < date1+1; i++) {
                            if (monthNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
                                Element dayEle = (Element) monthNode.item(i);
                                if (dayEle.getNodeName().contains("day")) {
                                    for(int k=0;k<25;k++){
                                        String task = dayEle.getElementsByTagName("task"+k).item(0).getTextContent();
                                        if(task.contains("1")){
                                            totalCount++;
                                        }
                                        totalCountDays++;
                                    }
                                }
                            }
                        }
                    }

                    Log.v("BBBBBBBBBBBBBBB","...13: ");
                    TextView tvSummary=(TextView)findViewById(R.id.txtSummary);
                    int totalCountPercentage=totalCount*100/totalCountDays;
                    tvSummary.setText("Total: "+totalCount+"/"+totalCountDays+"("+totalCountPercentage+"%), Today: "+count+"/"+countDays);
                }
                catch(Exception e){
                    Log.e("Jobs", "Exception parse xml :"+e);
                }
                break;
            case R.id.load:
                loadTasks();
                saveData();
                break;
            case R.id.delete:
                Intent i = new Intent(MainActivity.this, LocalActivity.class);
                startActivity(i);
                break;
            case R.id.datebtn:
                Log.v("DDDDDDDDDDDDDDDDDDD","...14: ");
                showDialog(DATE_DIALOG_ID);
                //			datasource.deleteAllAction();
                break;
        }
    }
    private String round(float countAction) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(MainActivity.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(MainActivity.this, LocalActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_settings) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);



        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void daatepopup()
    {
        Calendar c = Calendar.getInstance();
        String completeDate = ""+tv2.getText();
        int mYear=Integer.parseInt(""+tv2.getText().subSequence(0,4));
        int mMonth=Integer.parseInt(""+tv2.getText().subSequence(5,7));
        int mDay=Integer.parseInt(""+tv2.getText().subSequence(8,10));
        //int mYear = c.get(Calendar.YEAR);
        //int mMonth = c.get(Calendar.MONTH);
        //int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener mDateSetListener = null;
        //updateDisplay();
        DatePickerDialog d = new DatePickerDialog(this, mDateSetListener,mYear,  mMonth, mDay);
        d.show();
    }
    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        //Set Up Current Date Into dialog
        Calendar calender = Calendar.getInstance();
        int mYear=Integer.parseInt(""+tv2.getText().subSequence(0,4));
        int mMonth=Integer.parseInt(""+tv2.getText().subSequence(5,7));
        int mDay=Integer.parseInt(""+tv2.getText().subSequence(8,10));

        Bundle args = new Bundle();
        args.putInt("year", mYear);
        args.putInt("month", mMonth-1);
        args.putInt("day", mDay);
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getSupportFragmentManager(), "Date Picker");
    }

    OnDateSetListener ondate = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            int month1=monthOfYear+1;
            String month2="";
            if(month1<10)
                month2="0"+month1;
            else
                month2=""+month1;

            int day1=dayOfMonth;
            String day2="";
            if(day1<10)
                day2="0"+day1;
            else
                day2=""+day1;

            tv2.setText(String.valueOf(year) + " " + month2 + " " + day2);

            Toast.makeText(
                    MainActivity.this,
                    String.valueOf(year) + "-" + String.valueOf(monthOfYear)
                            + "-" + String.valueOf(dayOfMonth),
                    Toast.LENGTH_LONG).show();
        }
    };

    private void loadTasks(){
        cb1[0]=(CheckBox) findViewById(R.id.A01);
        cb1[1]=(CheckBox) findViewById(R.id.A02);
        cb1[2]=(CheckBox) findViewById(R.id.A03);
        cb1[3]=(CheckBox) findViewById(R.id.A04);
        cb1[4]=(CheckBox) findViewById(R.id.A05);
        cb1[5]=(CheckBox) findViewById(R.id.A06);
        cb1[6]=(CheckBox) findViewById(R.id.A07);
        cb1[7]=(CheckBox) findViewById(R.id.A08);
        cb1[8]=(CheckBox) findViewById(R.id.A09);
        cb1[9]=(CheckBox) findViewById(R.id.A10);
        cb1[10]=(CheckBox) findViewById(R.id.A11);
        cb1[11]=(CheckBox) findViewById(R.id.A12);
        cb1[12]=(CheckBox) findViewById(R.id.A13);
        cb1[13]=(CheckBox) findViewById(R.id.A14);
        cb1[14]=(CheckBox) findViewById(R.id.A15);
        cb1[15]=(CheckBox) findViewById(R.id.A16);
        cb1[16]=(CheckBox) findViewById(R.id.A17);
        cb1[17]=(CheckBox) findViewById(R.id.A18);
        cb1[18]=(CheckBox) findViewById(R.id.A19);
        cb1[19]=(CheckBox) findViewById(R.id.A20);
        cb1[20]=(CheckBox) findViewById(R.id.A21);
        cb1[21]=(CheckBox) findViewById(R.id.A22);
        cb1[22]=(CheckBox) findViewById(R.id.A23);
        cb1[23]=(CheckBox) findViewById(R.id.A24);
        cb1[24]=(CheckBox) findViewById(R.id.A25);
        int intChk[] = datasource.loadActions();

        String filePath = "/storage/emulated/0/"+(datasource.getIntDate(""+tv2.getText()))/100+".xml";
        tvSummary.setText(filePath);
        File rtoFile= new File(filePath);
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(rtoFile);
            Node task1Node = doc.getElementsByTagName("task1").item(0);

            int date1=datasource.getIntDate(""+tv2.getText())%100;
            int date2=(2*(date1))-1;
            tvSummary.setText(""+date1+",...."+date2);
            Element docEle2 = doc.getDocumentElement();
            NodeList nl2 = docEle2.getChildNodes();
            Element el2 = (Element) nl2.item(date2);
            Log.v("DDDDDDDDATE","...144: "+date2);
            String output1="";
            for(int i=0;i<25;i++){
                String abc=""+el2.getElementsByTagName("task"+i).item(0).getTextContent();
                if((""+el2.getElementsByTagName("task"+i).item(0).getTextContent()).contains("1")){
                    Log.v("DDDDDDDDATE","...16: ");
                    cb1[i].setChecked(true);}
                else
                    cb1[i].setChecked(false);
            }
            rtoFile=null;
            doc=null;
        }
        catch(Exception e){
            Log.e("Jobs", "Exception parse xml :"+e);
        }
    }
    private void saveData(){
        String filePath = "/storage/emulated/0/"+(datasource.getIntDate(""+tv2.getText()))/100+".xml";
        File rtoFile= new File(filePath);
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(rtoFile);
            //Save and calculate
            String dateStr= "" + tv2.getText().subSequence(8,10);
            int date1=(2*(Integer.parseInt(""+dateStr)%100))-1;
            //int date1=(2*(datasource.getIntDate()%100))-1;
            int count=0;
            int countDays=0;
            Element docEle = doc.getDocumentElement();
            NodeList nl = docEle.getChildNodes();
            Element el = (Element) nl.item(date1);
            String output1="";
            for(int i=0;i<25;i++){
                if(cb1[i].isChecked()){
                    el.getElementsByTagName("task"+i).item(0).setTextContent("1");
                    count++;
                    Log.v("BBBBBBBBBBBBBBB","...12: ");
                }
                else{
                    el.getElementsByTagName("task"+i).item(0).setTextContent("0");

                }
                countDays++;
            }
            Log.v("BBBBBBBBBBBBBBB","...15: ");
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // initialize StreamResult with File object to save to file
            StreamResult result = new StreamResult(rtoFile);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);

            //Month's Calculation
            int date2=datasource.getIntDate()%100;
            int totalCount=0, totalCountDays=0;
            NodeList monthNode = docEle.getChildNodes();
            if (monthNode != null && monthNode.getLength() > 0) {
                //for (int i = 0; i < monthNode.getLength(); i++) {
                Log.v("BBBBBBBBBBBBBBB","...Date: "+date2);
                for (int i = 0; i < date1+1; i++) {
                    if (monthNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element dayEle = (Element) monthNode.item(i);
                        if (dayEle.getNodeName().contains("day")) {
                            for(int k=0;k<25;k++){
                                String task = dayEle.getElementsByTagName("task"+k).item(0).getTextContent();
                                if(task.contains("1")){
                                    totalCount++;
                                }
                                totalCountDays++;
                            }
                        }
                    }
                }
            }

            Log.v("BBBBBBBBBBBBBBB","...13: ");
            TextView tvSummary=(TextView)findViewById(R.id.txtSummary);
            int totalCountPercentage=totalCount*100/totalCountDays;
            tvSummary.setText("Total: "+totalCount+"/"+totalCountDays+"("+totalCountPercentage+"%), Today: "+count+"/"+countDays);
        }
        catch(Exception e){
            Log.e("Jobs", "Exception parse xml :"+e);
        }
    }
}
