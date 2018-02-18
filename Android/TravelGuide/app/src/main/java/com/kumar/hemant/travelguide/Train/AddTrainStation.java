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
import android.widget.Toast;

import com.kumar.hemant.travelguide.CheckList.ChecklistActivity;
import com.kumar.hemant.travelguide.R;
import com.kumar.hemant.travelguide.RTO.RTOActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class AddTrainStation extends Activity implements NavigationView.OnNavigationItemSelectedListener
{
    public static final String APP_TAG = "com.hemant.kumar";
    private ListView lvTrainStation;
    private Button btNewTrainStation;
    private Button btImportTrainStation;
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
        this.btImportTrainStation = (Button) this.findViewById(R.id.btImportTrainStation);
        this.spnNewStationCode = (Spinner) this.findViewById(R.id.spn_station_code);
        this.spnNewTrainNo = (Spinner) this.findViewById(R.id.spn_train_no);
        this.etNewArrivalTime = (EditText) this.findViewById(R.id.etNewArrivalTime);
        this.etNewDepartureTime = (EditText) this.findViewById(R.id.etNewDepartureTime);
        this.btNewTrainStation.setOnClickListener(this.handleNewTrainStationEvent);
        this.btImportTrainStation.setOnClickListener(this.handleImportTrainStationEvent);
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
            AddTrainStation.this.controller.addTrainStation(AddTrainStation.this.spnNewTrainNo.getSelectedItem().toString().substring(0,5), AddTrainStation.this.spnNewStationCode.getSelectedItem().toString().substring(0,3),  AddTrainStation.this.etNewArrivalTime.getText().toString(),  AddTrainStation.this.etNewDepartureTime.getText().toString());
            AddTrainStation.this.populateTrainStations();
        }
    };
    private final View.OnClickListener handleImportTrainStationEvent = new View.OnClickListener()
    {
        @Override
        public void onClick(final View view)
        {
            Log.d(APP_TAG, "New Station button added");
            String strToastText = "";
            String filePath = "/storage/emulated/0/train_station.xml";
            File trainstationFile= new File(filePath);
            Toast.makeText(AddTrainStation.this, "Train Station Time Imported Successfully1!!!", Toast.LENGTH_LONG).show();
            try{
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(trainstationFile);
                Node station_code_node = doc.getElementsByTagName("station_code").item(0);
                Node train_no_node = doc.getElementsByTagName("train_no").item(0);
                Node time_arrival_node = doc.getElementsByTagName("time_arrival").item(0);
                Node time_departure_node = doc.getElementsByTagName("time_departure").item(0);
                strToastText = ""+station_code_node.getTextContent();
                for(int i=0;i<1002;i++)
                {
                    station_code_node= doc.getElementsByTagName("station_code").item(i);
                    train_no_node = doc.getElementsByTagName("train_no").item(i);
                    time_arrival_node = doc.getElementsByTagName("time_arrival").item(i);
                    time_departure_node = doc.getElementsByTagName("time_departure").item(i);
                    strToastText = strToastText + "," + station_code_node.getTextContent()+":" + station_code_node.getTextContent();
                    AddTrainStation.this.controller.addTrainStation( train_no_node.getTextContent(), station_code_node.getTextContent(), time_arrival_node.getTextContent(), time_departure_node.getTextContent());
                }
                AddTrainStation.this.populateTrainStations();
                Toast.makeText(AddTrainStation.this, "Train Station Time Imported Successfully!!!", Toast.LENGTH_LONG).show();
            }
            catch(Exception e){
                Log.e("Jobs", "Exception parse xml :"+e);
            }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_select_station) {
            Intent i = new Intent(AddTrainStation.this, SearchStationActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_train) {
            Intent i = new Intent(AddTrainStation.this, AddTrain.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_station) {
            Intent i = new Intent(AddTrainStation.this, AddStation.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_train_station) {
            Intent i = new Intent(AddTrainStation.this, AddTrainStation.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_update_train_time) {
            Intent i = new Intent(AddTrainStation.this, UpdateTrainTime.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
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
