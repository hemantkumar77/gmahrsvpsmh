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
import android.widget.Spinner;
import android.widget.Toast;

import com.kumar.hemant.travelguide.R;
import com.kumar.hemant.travelguide.CheckList.ChecklistActivity;
import com.kumar.hemant.travelguide.RTO.RTOActivity;
import java.io.File;
import java.util.List;

public class SearchStationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String APP_TAG = "com.hemant.kumar";
    public static final String EXTRA_MESSAGE = "MEEKU";
    Spinner spinnerStation;
    private String[] arraySpinner;
    private TrainController controller1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchstation);
        spinnerStation = (Spinner) findViewById(R.id.spn_station);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.controller1 = new TrainController(this);
        this.populateStations();

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
    private void populateStations()
    {
        int ii=999;
        final List<String> stations1 = this.controller1.getStations();
        this.arraySpinner = new String[stations1.size()];
        arraySpinner=stations1.toArray(arraySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        spinnerStation.setAdapter(adapter);
        Log.d(AddStation.APP_TAG, String.format("%d found tasks ..........", stations1.size()));
    }

    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        File rtoFile= new File(Environment.getExternalStorageDirectory()+"/hemant2.xml");
        Toast.makeText(SearchStationActivity.this, "Into Train Time3", Toast.LENGTH_LONG).show();
        Log.v("GGGGGGGGGGGGGGGGGGGGGG","TTTTTTTTTTTTTTTT...");
        //showDatePicker();
        switch (view.getId()) {
            case R.id.detail:
                Log.v("GGGGGGGGGGGGGGGGGGGGGG","TTTTTTTTTTTTTTTT...");
                Intent intent = new Intent(this, TrainActivity.class);
//                EditText editText = (EditText) findViewById(R.id.editText);

                String message = ""+spinnerStation.getSelectedItem().toString();// "Message from Meeku";
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
        }
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
            Intent i = new Intent(SearchStationActivity.this, SearchStationActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_train) {
            Intent i = new Intent(SearchStationActivity.this, AddTrain.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_station) {
            Intent i = new Intent(SearchStationActivity.this, AddStation.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_train_station) {
            Intent i = new Intent(SearchStationActivity.this, AddTrainStation.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_update_train_time) {
            Intent i = new Intent(SearchStationActivity.this, UpdateTrainTime.class);
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
            Intent i = new Intent(SearchStationActivity.this, TrainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(SearchStationActivity.this, ChecklistActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(SearchStationActivity.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(SearchStationActivity.this, SearchStationActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(SearchStationActivity.this, SearchStationActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_add_train) {
            Intent i = new Intent(SearchStationActivity.this, AddTrain.class);
            startActivity(i);
        } else if (id == R.id.nav_add_station) {
            Intent i = new Intent(SearchStationActivity.this, TrainView.class);
            startActivity(i);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
