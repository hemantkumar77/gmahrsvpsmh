package com.kumar.hemant.travelguide.Train;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.kumar.hemant.travelguide.CheckList.ChecklistActivity;
import com.kumar.hemant.travelguide.R;
import com.kumar.hemant.travelguide.RTO.RTOActivity;

import java.util.List;
import android.widget.Toast;

public class AddStation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    public static final String APP_TAG = "com.hemant.kumar";
    private ListView lvStation;
    private Button btNewStation;
    private Button btImportStation;
    private EditText etNewStationCode;
    private EditText etNewStationName;
    private TrainController controller;
    @Override
    public void onCreate(final Bundle bundle)
    {
        super.onCreate(bundle);
        this.setContentView(R.layout.add_station);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.controller = new TrainController(this);
        this.lvStation = (ListView) this.findViewById(R.id. lvStation);
        this.btNewStation = (Button) this.findViewById(R.id.btNewStation);
        this.btImportStation = (Button) this.findViewById(R.id.btImportStation);
        this.etNewStationCode = (EditText) this.findViewById(R.id.etStationCode);
        this.etNewStationName = (EditText) this.findViewById(R.id.etStationName);
        this.btNewStation.setOnClickListener(this.handleNewStationEvent);
        this.btImportStation.setOnClickListener(this.handleImportStationEvent);

        this.populateStations();
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
        final List<String> stations = this.controller.getStations();
        Log.d(AddStation.APP_TAG, String.format("%d found tasks ", stations.size()));
        this.lvStation.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stations.toArray(new String[] {})));
        this.lvStation.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id)
            {
                Log.d(AddStation.APP_TAG, String.format("task id: %d and position: %d", id, position));
                final TextView v = (TextView) view;
                AddStation.this.controller.deleteStation(v.getText().toString());
                AddStation.this.populateStations();
            }
        });
    }

    private final View.OnClickListener handleNewStationEvent = new View.OnClickListener()
    {
        @Override
        public void onClick(final View view)
        {
            Log.d(APP_TAG, "New Station button added");

            if(AddStation.this.etNewStationCode.getText().toString().length()==3)
                AddStation.this.controller.addStation(AddStation.this.etNewStationCode.getText().toString(), AddStation.this.etNewStationName.getText().toString());
            AddStation.this.populateStations();

        }
    };
    private final View.OnClickListener handleImportStationEvent = new View.OnClickListener()
    {
        @Override
        public void onClick(final View view)
        {
            Log.d(APP_TAG, "New Station button added");

            String strToastText = "";
            String filePath = "/storage/emulated/0/stations.xml";
            File rtoFile= new File(filePath);

            try{
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(rtoFile);
                Node station_code_node = doc.getElementsByTagName("station_code").item(1);
                Node station_name_node = doc.getElementsByTagName("station_name").item(1);
                strToastText = "";


                Toast.makeText(AddStation.this, "Into Import button click....", Toast.LENGTH_LONG).show();
                for(int i=0;i<17;i++)
                {
                    station_code_node= doc.getElementsByTagName("station_code").item(i);
                    station_name_node = doc.getElementsByTagName("station_name").item(i);
                    strToastText = strToastText + "," + station_code_node.getTextContent()+":" + station_name_node.getTextContent();
                    AddStation.this.controller.addStation(station_code_node.getTextContent(), station_name_node.getTextContent());
                }
                Toast.makeText(AddStation.this, "Out of Import button click....", Toast.LENGTH_LONG).show();
                AddStation.this.populateStations();

                Toast.makeText(AddStation.this, "Station Imported Successfully!!!", Toast.LENGTH_LONG).show();
            }
            catch(Exception e){
                Log.e("Jobs", "Exception parse xml :"+e);
            }
            AddStation.this.populateStations();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.train, menu);
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
            Intent i = new Intent(AddStation.this, SearchStationActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_train) {
            Intent i = new Intent(AddStation.this, AddTrain.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_station) {
            Intent i = new Intent(AddStation.this, AddStation.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_train_station) {
            Intent i = new Intent(AddStation.this, AddTrainStation.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_update_train_time) {
            Intent i = new Intent(AddStation.this, UpdateTrainTime.class);
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
            Intent i = new Intent(AddStation.this, TrainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(AddStation.this, ChecklistActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(AddStation.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(AddStation.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(AddStation.this, SearchStationActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_add_train) {
            Intent i = new Intent(AddStation.this, TrainView.class);
            startActivity(i);
        } else if (id == R.id.nav_add_train) {
            Intent i = new Intent(AddStation.this, AddStation.class);
            startActivity(i);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
