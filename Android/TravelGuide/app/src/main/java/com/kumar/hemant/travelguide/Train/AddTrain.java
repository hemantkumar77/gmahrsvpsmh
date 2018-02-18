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

public class AddTrain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    public static final String APP_TAG = "com.hemant.kumar";
    private ListView lvTrain;
    private Button btNewTrain;
    private Button btImportTrain;
    private EditText etNewTrainNo;
    private EditText etNewTrainName;
    private Spinner spnNewSource;
    private Spinner spnNewDestination;
    private EditText etNewDaysOfWeek;
    private TrainController controller;
    @Override
    public void onCreate(final Bundle bundle)
    {
        super.onCreate(bundle);
        this.setContentView(R.layout.add_train);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.controller = new TrainController(this);
        this.lvTrain = (ListView) this.findViewById(R.id.lvTask);
        this.btNewTrain = (Button) this.findViewById(R.id.bt_new_train);
        this.btImportTrain = (Button) this.findViewById(R.id.bt_import_train);
        this.etNewTrainNo = (EditText) this.findViewById(R.id.et_train_no);
        this.etNewTrainName = (EditText) this.findViewById(R.id.et_train_name);
        this.spnNewSource = (Spinner) this.findViewById(R.id.spn_source);
        this.spnNewDestination = (Spinner) this.findViewById(R.id.spn_destination);
        this.etNewDaysOfWeek = (EditText) this.findViewById(R.id.et_days_of_week);
        this.btNewTrain.setOnClickListener(this.handleNewTaskEvent);
        this.btImportTrain.setOnClickListener(this.handleImportStationEvent);
        this.populateTrains();
        this.populateSourceSpinner();
        this.populateDestinationSpinner();

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
        final List<String> trains = this.controller.getTrains();
        Log.d(AddTrain.APP_TAG, String.format("%d found tasks ", trains.size()));
        this.lvTrain.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, trains.toArray(new String[] {})));
        this.lvTrain.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id)
            {
                Log.d(AddTrain.APP_TAG, String.format("train id: %d and position: %d", id, position));
                final TextView v = (TextView) view;
                AddTrain.this.controller.deleteTrain (v.getText().toString());
                AddTrain.this.populateTrains();
            }
        });
    }
    private void populateSourceSpinner()
    {
        final List<String> sourceList = this.controller.getStationList();
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, sourceList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNewSource.setAdapter(dataAdapter2);
    }
    private void populateDestinationSpinner()
    {
        final List<String> destinationList = this.controller.getStationList();
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, destinationList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNewDestination.setAdapter(dataAdapter2);
    }
    private final View.OnClickListener handleNewTaskEvent = new View.OnClickListener()
    {
        @Override
        public void onClick(final View view)
        {
            Log.d(APP_TAG, "New Task button added");
            if(AddTrain.this.etNewTrainNo.getText().toString().length()==5)
                AddTrain.this.controller.addTrain(AddTrain.this.etNewTrainNo.getText().toString(), AddTrain.this.etNewTrainName.getText().toString(), AddTrain.this.spnNewSource.getSelectedItem().toString().substring(0,3), AddTrain.this.spnNewDestination.getSelectedItem().toString().substring(0,3), AddTrain.this.etNewDaysOfWeek.getText().toString());
            AddTrain.this.populateTrains();
        }
    };
    private final View.OnClickListener handleImportStationEvent = new View.OnClickListener()
    {
        @Override
        public void onClick(final View view)
        {
            Log.d(APP_TAG, "New Train button added");

            String strToastText = "";
            String filePath = "/storage/emulated/0/trains.xml";
            File rtoFile= new File(filePath);

            try{
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(rtoFile);
                Node train_no_node = doc.getElementsByTagName("train_no").item(1);
                Node train_name_node = doc.getElementsByTagName("train_name").item(1);
                Node source_node = doc.getElementsByTagName("source").item(1);
                Node destination_node = doc.getElementsByTagName("destination").item(1);
                Node days_of_week_node = doc.getElementsByTagName("days_of_week").item(1);
                strToastText = "";


                Toast.makeText(AddTrain.this, "Into Import button click....", Toast.LENGTH_LONG).show();
                for(int i=0;i<42;i++)
                {
                    train_no_node= doc.getElementsByTagName("train_no").item(i);
                    train_name_node = doc.getElementsByTagName("train_name").item(i);
                    source_node= doc.getElementsByTagName("source").item(i);
                    destination_node = doc.getElementsByTagName("destination").item(i);
                    days_of_week_node= doc.getElementsByTagName("days_of_week").item(i);
                    strToastText = strToastText + "," + train_name_node.getTextContent()+":" + train_no_node.getTextContent();
                    AddTrain.this.controller.addTrain(train_no_node.getTextContent(), train_name_node.getTextContent(), source_node.getTextContent(), destination_node.getTextContent(), days_of_week_node.getTextContent());
                }
                Toast.makeText(AddTrain.this, "Out of Import button click....", Toast.LENGTH_LONG).show();
                AddTrain.this.populateTrains();

                Toast.makeText(AddTrain.this, "Station Imported Successfully!!!", Toast.LENGTH_LONG).show();
            }
            catch(Exception e){
                Log.e("Jobs", "Exception parse xml :"+e);
            }
            AddTrain.this.populateTrains();
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
            Intent i = new Intent(AddTrain.this, SearchStationActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_train) {
            Intent i = new Intent(AddTrain.this, AddTrain.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_station) {
            Intent i = new Intent(AddTrain.this, AddStation.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_train_station) {
            Intent i = new Intent(AddTrain.this, AddTrainStation.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_update_train_time) {
            Intent i = new Intent(AddTrain.this, UpdateTrainTime.class);
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
            Intent i = new Intent(AddTrain.this, TrainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(AddTrain.this, ChecklistActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(AddTrain.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(AddTrain.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(AddTrain.this, SearchStationActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_add_train) {
            Intent i = new Intent(AddTrain.this, TrainView.class);
            startActivity(i);
        } else if (id == R.id.nav_add_train) {
            Intent i = new Intent(AddTrain.this, AddStation.class);
            startActivity(i);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
