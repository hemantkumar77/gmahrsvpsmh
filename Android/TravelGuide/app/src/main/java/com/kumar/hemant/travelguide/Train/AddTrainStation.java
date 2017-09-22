package com.kumar.hemant.travelguide.Train;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.kumar.hemant.travelguide.CheckList.ChecklistActivity;
import com.kumar.hemant.travelguide.R;
import com.kumar.hemant.travelguide.RTO.RTOActivity;

import java.util.List;

public class AddTrainStation extends Activity implements NavigationView.OnNavigationItemSelectedListener
{
    public static final String APP_TAG = "com.hemant.kumar";
    private ListView lvTrainStation;
    private Button btNewTrainStation;
    private Spinner spnNewTrainNo;
    private Spinner spnNewStationCode;
    private EditText etNewArrivalTime;
    private EditText etNewDepartureTime;
    private TrainController controller;
    private String[] arraySpinner;
    @Override
    public void onCreate(final Bundle bundle)
    {
        super.onCreate(bundle);
        this.setContentView(R.layout.add_train_station);
        this.controller = new TrainController(this);
        this.lvTrainStation = (ListView) this.findViewById(R.id. lvTrainStation);
        this.btNewTrainStation = (Button) this.findViewById(R.id.btNewTrainStation);
        this.spnNewStationCode = (Spinner) this.findViewById(R.id.spn_station_code);
        this.spnNewTrainNo = (Spinner) this.findViewById(R.id.spn_train_no);
        this.etNewArrivalTime = (EditText) this.findViewById(R.id.etNewArrivalTime);
        this.etNewDepartureTime = (EditText) this.findViewById(R.id.etNewDepartureTime);
        this.btNewTrainStation.setOnClickListener(this.handleNewTrainStationEvent);
        this.populateTrainStations();
        this.populateStationSpinner();
        this.populateTrainSpinner();
    }

    private void populateTrainStations()
    {
        final List<String> trainstations = this.controller.getTrainStations();
        Log.d(AddTrainStation.APP_TAG, String.format("%d found tasks ", trainstations.size()));
        this.lvTrainStation.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, trainstations.toArray(new String[] {})));
        this.lvTrainStation.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id)
            {
                Log.d(AddTrainStation.APP_TAG, String.format("task id: %d and position: %d", id, position));
                final TextView v = (TextView) view;
                AddTrainStation.this.controller.deleteTrainStation(v.getText().toString());
                AddTrainStation.this.populateTrainStations();
            }
        });
    }
    private void populateTrainSpinner()
    {
        final List<String> trainList = this.controller.getTrainList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, trainList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNewTrainNo.setAdapter(dataAdapter);
    }
    private void populateStationSpinner()
    {
        final List<String> stationList = this.controller.getStationList();
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, stationList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNewStationCode.setAdapter(dataAdapter2);
    }
    private final View.OnClickListener handleNewTrainStationEvent = new View.OnClickListener()
    {
        @Override
        public void onClick(final View view)
        {
            Log.d(APP_TAG, "New Station button added");
/*
            if(AddTrainStation.this.spnNewStationCode.getSelectedItem().toString().length()==3)
                if(AddTrainStation.this.spnNewTrainNo.getSelectedItem().toString().length()==5)
                    if(AddTrainStation.this.etNewArrivalTime.getText().toString().length()==4)
                        if(AddTrainStation.this.etNewDepartureTime.getText().toString().length()==4)
*/
                            AddTrainStation.this.controller.addTrainStation(AddTrainStation.this.spnNewTrainNo.getSelectedItem().toString().substring(0,5), AddTrainStation.this.spnNewStationCode.getSelectedItem().toString().substring(0,3),  AddTrainStation.this.etNewArrivalTime.getText().toString(),  AddTrainStation.this.etNewDepartureTime.getText().toString());
            AddTrainStation.this.populateTrainStations();
        }
    };
    @Override
    protected void onStart()
    {
        super.onStart();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i = new Intent(AddTrainStation.this, TrainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(AddTrainStation.this, ChecklistActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(AddTrainStation.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(AddTrainStation.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(AddTrainStation.this, SearchStationActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_add_train) {
            Intent i = new Intent(AddTrainStation.this, TrainView.class);
            startActivity(i);
        } else if (id == R.id.nav_add_train) {
            Intent i = new Intent(AddTrainStation.this, AddTrainStation.class);
            startActivity(i);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
