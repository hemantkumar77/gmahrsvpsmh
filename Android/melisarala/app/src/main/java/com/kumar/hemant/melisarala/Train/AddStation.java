package com.kumar.hemant.melisarala.Train;

import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.kumar.hemant.melisarala.Checklist.ChecklistActivity;
import com.kumar.hemant.melisarala.Checklist.checktask;
import com.kumar.hemant.melisarala.R;
import com.kumar.hemant.melisarala.RTO.RTOActivity;
import java.util.List;
public class AddStation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    public static final String APP_TAG = "com.hemant.kumar";
    private ListView lvStation;
    private Button btNewStation;
    private EditText etNewStationCode;
    private EditText etNewStationName;
    private MVCController controller;
    @Override
    public void onCreate(final Bundle bundle)
    {
        super.onCreate(bundle);
        this.setContentView(R.layout.add_station);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.controller = new MVCController(this);
        this.lvStation = (ListView) this.findViewById(R.id. lvStation);
        this.btNewStation = (Button) this.findViewById(R.id.btNewStation);
        this.etNewStationCode = (EditText) this.findViewById(R.id.etStationCode);
        this.etNewStationName = (EditText) this.findViewById(R.id.etStationName);
        this.btNewStation.setOnClickListener(this.handleNewTaskEvent);
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

    private final View.OnClickListener handleNewTaskEvent = new View.OnClickListener()
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
            Intent i = new Intent(AddStation.this, checktask.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(AddStation.this, SearchStationActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_add_train) {
            Intent i = new Intent(AddStation.this, MVCView.class);
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
