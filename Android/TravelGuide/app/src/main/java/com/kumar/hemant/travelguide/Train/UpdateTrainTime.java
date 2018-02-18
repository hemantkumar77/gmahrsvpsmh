package com.kumar.hemant.travelguide.Train;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kumar.hemant.travelguide.R;
import com.kumar.hemant.travelguide.CheckList.ChecklistActivity;
import com.kumar.hemant.travelguide.RTO.RTOActivity;
import java.io.File;
import java.util.List;

public class UpdateTrainTime extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String APP_TAG = "com.hemant.kumar";
    public static final String EXTRA_MESSAGE = "MEEKU";
    Spinner spinnerTrain;
    EditText et_train_time;
    private String[] arraySpinner;
    private TrainController controller2, controller3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_train_time);
        spinnerTrain = (Spinner) findViewById(R.id.spn_train_no);
        et_train_time = (EditText) findViewById(R.id.et_train_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.controller2 = new TrainController(this);
        this.controller3 = new TrainController(this);
        this.populateTrains();

        this.arraySpinner = new String[] {
                "1", "2", "3", "4", "5"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
//        spinnerStation.setAdapter(adapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    private void populateTrains()
    {
        int ii=999;
        final List<String> trains1 = this.controller2.getTrains();
        this.arraySpinner = new String[trains1.size()];
        arraySpinner=trains1.toArray(arraySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        spinnerTrain.setAdapter(adapter);
        Log.d(AddStation.APP_TAG, String.format("%d found tasks ..........", trains1.size()));
    }

    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        File rtoFile= new File(Environment.getExternalStorageDirectory()+"/hemant2.xml");
        Log.v("GGGGGGGGGGGGGGGGGGGGGG","TTTTTTTTTTTTTTTT...");
        //showDatePicker();
        switch (view.getId()) {
            case R.id.bt_update_train_time1:
                Log.v("GGGGGGGGGGGGGGGGGGGGGG","TTTTTTTTTTTTTTTT...");
                String strStations[] = {"lon", "mal", "kam", "kan", "wad", "tal", "gho", "beg", "deh", "aku", "chi", "pim", "kas", "dap", "kha", "shi", "pun"};
                String str_train_no = ""+et_train_time.getText();
                str_train_no = str_train_no + "..." + UpdateTrainTime.this.spinnerTrain.getSelectedItem().toString().substring(0,5);
                str_train_no = "11013";

                final List<String> trainstations = this.controller3.getTrainStations(str_train_no);
                str_train_no="";
                for(int i=0;i<trainstations.size();i++){
                    //str_train_no = str_train_no + ";..." +trainstations.get(i).substring(15,19)+":"+ addSeconds(trainstations.get(i).substring(15,19), 1);
                    str_train_no = str_train_no + ";..." +trainstations.get(i).substring(6,9);
                    UpdateTrainTime.this.controller3.updateTrainTime(UpdateTrainTime.this.spinnerTrain.getSelectedItem().toString().substring(0,5), trainstations.get(i).substring(6,9), addSeconds(trainstations.get(i).substring(15,19), 1), addSeconds(trainstations.get(i).substring(15,19), 1));
                }
                str_train_no = str_train_no + "..";

                //str_train_no = ""+ trainstations.get(1)+".."+ trainstations.get(2)+".."+ trainstations.get(3)+".."+ trainstations.get(4)+"..";
                //lon, mal, kam, kan, wad, tal, gho, beg, deh, aku, chi, pim, kas, dap, kha, shi, pun,
                //AddTrainStation.this.controller.addTrainStation(AddTrainStation.this.spnNewTrainNo.getSelectedItem().toString().substring(0,5), AddTrainStation.this.spnNewStationCode.getSelectedItem().toString().substring(0,3),  AddTrainStation.this.etNewArrivalTime.getText().toString(),  AddTrainStation.this.etNewDepartureTime.getText().toString());

                //UpdateTrainTime.this.controller2.addTrainStation(UpdateTrainTime.this.spinnerTrain.getSelectedItem().toString().substring(0,5), AddTrainStation.this.spnNewStationCode.getSelectedItem().toString().substring(0,3),  AddTrainStation.this.etNewArrivalTime.getText().toString(),  AddTrainStation.this.etNewDepartureTime.getText().toString());
                Toast.makeText(UpdateTrainTime.this, "Into Train Time..."+str_train_no, Toast.LENGTH_LONG).show();
                Log.v("AAAAAAAAAAAAAAAA","...156: "+str_train_no);
                //Intent intent = new Intent(this, TrainActivity.class);
//                EditText editText = (EditText) findViewById(R.id.editText);

                String message = ""+spinnerTrain.getSelectedItem().toString();// "Message from Meeku";
                //intent.putExtra(EXTRA_MESSAGE, message);
                //startActivity(intent);
        }
    }
    private String addSeconds(String time1, int minutes){
        if(time1!="XXXX"){
            int hr1 = Integer.parseInt(time1.substring(0,2));
            int min1 = Integer.parseInt(time1.substring(2,4));
            min1=min1+minutes;
            hr1=hr1+min1/60;
            min1=min1%60;
            hr1=hr1%24;
            hr1=10000+(hr1*100)+min1;
            time1=Integer.toString(hr1).substring(1,5);
            return time1;
        }
        else
            return "XXXX";
    }

    private String round(float countAction) {
        // TODO Auto-generated method stub
        return null;
    }




    @Override
    protected void onResume() {
//		datasource.open();
        super.onResume();
    }
    @Override
    protected void onPause() {
//		datasource.close();
        super.onPause();
    }
    //@Override

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
    {
    }
    public void onNothingSelected(AdapterView<?> parent) {
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
        getMenuInflater().inflate(R.menu.checklist, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_select_station) {
            Intent i = new Intent(UpdateTrainTime.this, SearchStationActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_train) {
            Intent i = new Intent(UpdateTrainTime.this, AddTrain.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_station) {
            Intent i = new Intent(UpdateTrainTime.this, AddStation.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_train_station) {
            Intent i = new Intent(UpdateTrainTime.this, AddTrainStation.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_update_train_time) {
            Intent i = new Intent(UpdateTrainTime.this, UpdateTrainTime.class);
            startActivity(i);
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
            Intent i = new Intent(UpdateTrainTime.this, TrainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(UpdateTrainTime.this, ChecklistActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(UpdateTrainTime.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(UpdateTrainTime.this, UpdateTrainTime.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(UpdateTrainTime.this, UpdateTrainTime.class);
            startActivity(i);
        } else if (id == R.id.nav_add_train) {
            Intent i = new Intent(UpdateTrainTime.this, AddTrain.class);
            startActivity(i);
        } else if (id == R.id.nav_add_station) {
            Intent i = new Intent(UpdateTrainTime.this, TrainView.class);
            startActivity(i);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
