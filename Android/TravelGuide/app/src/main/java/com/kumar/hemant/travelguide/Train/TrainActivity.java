package com.kumar.hemant.travelguide.Train;

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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.kumar.hemant.travelguide.CheckList.ChecklistActivity;
import com.kumar.hemant.travelguide.DividerItemDecoration;
import com.kumar.hemant.travelguide.R;
import com.kumar.hemant.travelguide.RTO.RTOActivity;

import java.util.ArrayList;
import java.util.List;

public class TrainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private List<Train> trainList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TrainsAdapter mAdapter;
    public String stationName;
    private TextView tv_current_train;
    private TrainController controller;
    private ListView lvTrainTimes;
    private ImageButton ibt_up_down;
    private Boolean boolUpFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.controller = new TrainController(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_train);
        mAdapter = new TrainsAdapter(trainList);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager2);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        this.lvTrainTimes = (ListView) this.findViewById(R.id. lvTrainStation);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        stationName = intent.getStringExtra(SearchStationActivity.EXTRA_MESSAGE);
        if(stationName == null)
            stationName = "DEHUROAD";
        tv_current_train = (TextView) findViewById(R.id.tv_current_station);
        tv_current_train.setText(stationName);
        boolUpFlag=true;
        prepareTrainData();

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

        ibt_up_down =(ImageButton)findViewById(R.id.ibt_up_down);
        ibt_up_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolUpFlag=!boolUpFlag;
                prepareTrainData();
            }
        });

        //this.populateTrainTimes();

    }

    private void prepareTrainData()
    {
        ///////////////////////////
        //trainList.add(train);
        final List<String> traintimes = this.controller.getTrainTimes();
        String arrivalTime="";
        if (traintimes.size()>0)
            arrivalTime = ""+traintimes.size()+":" +traintimes.get(0).toString();
        else
            arrivalTime = "Not found: Not Found";
        Log.v("DDDDDDDDATE","...144: "+traintimes.toString());

        String[] strStationTime;
        String temp = "";
        Train train;
        if (stationName.contains("Dehuroad".toUpperCase())) {
            int i=0;
            trainList.clear();
            if(boolUpFlag)
                i=0;
            else
                i=1;
            for(;i<traintimes.size();i=i+2)
            {
                strStationTime = traintimes.get(i).split("-");
                train = new Train(strStationTime[0], strStationTime[1], strStationTime[4],strStationTime[5], strStationTime[7], strStationTime[8], strStationTime[10]);
                trainList.add(train);
            }
        }

        mAdapter.notifyDataSetChanged();
    }

    private void populateTrainTimes()
    {
        final List<String> traintimes = this.controller.getTrainTimes();
        Log.d(AddTrainStation.APP_TAG, String.format("%d found tasks ", traintimes.size()));
        this.lvTrainTimes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, traintimes.toArray(new String[] {})));
        this.lvTrainTimes.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id)
            {
                Log.d(AddTrainStation.APP_TAG, String.format("task id: %d and position: %d", id, position));
                final TextView v = (TextView) view;
                //AddTrain.this.controller.deleteTrainStation(v.getText().toString());
                //Add this.populateTrainStations();
            }
        });
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
            Intent i = new Intent(TrainActivity.this, SearchStationActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_train) {
            Intent i = new Intent(TrainActivity.this, AddTrain.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_station) {
            Intent i = new Intent(TrainActivity.this, AddStation.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_train_station) {
            Intent i = new Intent(TrainActivity.this, AddTrainStation.class);
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
            Intent i = new Intent(TrainActivity.this, TrainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(TrainActivity.this, ChecklistActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(TrainActivity.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(TrainActivity.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(TrainActivity.this, SearchStationActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_add_train) {
            Intent i = new Intent(TrainActivity.this, AddTrain.class);
            startActivity(i);
        } else if (id == R.id.nav_add_station) {
            Intent i = new Intent(TrainActivity.this, TrainView.class);
            startActivity(i);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
