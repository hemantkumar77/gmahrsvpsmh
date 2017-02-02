package com.kumar.hemant.melisarala;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TrainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private List<Train> trainList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TrainsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_train);
        mAdapter = new TrainsAdapter(trainList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

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
    }

    private void prepareTrainData() {
        String LONAWALADown[] = {" XX:XX", " 05:20",  "06:30", " 7:3", "  XX:XX", "08:20",  " XX:XX",  "9:35",  "11:3",  "14:00",  "14:55",  "15:45",  " XX:XX",  "17:25", "18:2",  "19:35",  "20:4",  "21:15",  "22:05", " 22:35",  "23:45",  "16:10"};
        String MALWALIDown[] = {" XX:XX", " 5:29",  "6:29", " 7:29", "  XX:XX", " 8:29",  " XX:XX",  "9:44",  "11:29",  "14:09",  "15:04",  "15:54",  " XX:XX",  "17:34", "18:29",  "19:44",  "20:49",  "21:24",  "22:14", " 22:44",  "23:54", "16:19"};
        String KAMSHETDown[] = {" XX:XX", " 5:37",  "6:37", " 7:37", "  XX:XX", " 8:37",  " XX:XX",  "9:52",  "11:37",  "14:17",  "15:12",  "16:02",  " XX:XX",  "17:42", "18:37",  "19:52",  "20:57",  "21:32",  "22:22", " 22:52",  "0:02",  "16:27"};
        String KANHEDown[] = {" XX:XX", " 5:41",  "6:41", " 7:41", "  XX:XX", " 8:41",  " XX:XX",  "9:56",  "11:41",  "14:21",  "15:16",  "16:06",  " XX:XX",  "17:46", "18:41",  "19:56",  "21:01",  "21:36",  "22:26", " 22:56",  "0:06",  "16:32"};
        String WADGAONDown[] = {" XX:XX", " 5:46",  "6:46", " 7:46", "  XX:XX", " 8:46",  " XX:XX",  "10:01",  "11:46",  "14:26",  "15:22",  "16:11",  " XX:XX",  "17:51", "18:46",  "20:01",  "21:06",  "21:41",  "22:31", " 23:01",  "0:11",  "16:39"};
        String TALEGAONDown[] = {"0:05", " 5:52",  "6:52", " 7:52", " 7:5", " 8:52",  "9:57",  "10:07",  "11:52",  "14:32",  "15:28",  "16:17",  "16:4",  "17:57", "18:52",  "20:07",  "21:11",  "21:47",  "22:37", " 23:07",  "0:17",  "16:48"};
        String GORAWADIDown[] = {"0:09", " 5:56",  "6:56", " 7:56", " 7:54", " 8:56",  "10:01",  "10:11",  "11:56",  "14:36",  "15:32",  "16:21",  "16:44",  "18:01", "18:56",  "20:11",  "21:16",  "21:51",  "22:41", " 23:11",  "0:21",  "16:54"};
        String BEGDEWADIDown[] = {"0:13", " 6",  "7", " 8", " 7:58", " 9",  "10:08",  "10:15",  "12:00",  "14:40",  "15:36",  "16:25",  "16:48",  "18:05", "19",  "20:15",  "21:2",  "21:55",  "22:45", " 23:15",  "0:25",  "XX:XX"};
        String DEHUROADDown[] = {"0:17", " 6:04",  "7:04", " 8:04", " 8:02", " 9:04",  "10:12",  "10:19",  "12:04",  "14:44",  "15:4",  "16:29",  "16:52",  "18:09", "19:04",  "20:19",  "21:24",  "21:59",  "22:49", " 23:19",  "0:29",  "17:00"};
        String AKURDIDown[] = {"0:21", " 6:08",  "7:08", " 8:08", " 8:06", " 9:08",  "10:16",  "10:23",  "12:08",  "14:48",  "15:44",  "16:33",  "16:56",  "18:13", "19:08",  "20:23",  "21:28",  "22:03",  "22:53", " 23:23",  "0:33",  "17:07"};
        String CHINCHWADDown[] = {"0:26", " 6:13",  "7:13", " 8:13", " 8:11", " 9:13",  "10:21",  "10:28",  "12:13",  "14:53",  "15:49",  "16:38",  "17:01",  "18:18", "19:13",  "20:28",  "21:33",  "22:08",  "22:58", " 23:28",  "0:38",  "17:17"};
        String PIMPRIDown[] = {"0:3", " 6:17",  "7:17", " 8:17", " 8:15", " 9:17",  "10:25",  "10:32",  "12:17",  "14:56",  "15:53",  "16:42",  "17:05",  "18:22", "19:17",  "20:32",  "21:37",  "22:12",  "22:02", " 23:32",  "0:42",  "17:21"};
        String KASARWADIDown[] = {"0:33", " 6:2",  "7:2", " 8:2", " 8:18", " 9:2",  "10:28",  "10:35",  "12:20",  "15:00",  "15:57",  "16:45",  "17:08",  "18:27", "19:2",  "20:35",  "21:4",  "22:15",  "22:05", " 23:35",  "0:45",  "17:24"};
        String DAPODIDown[] = {"0:37", " 6:24",  "7:24", " 8:24", " 8:22", " 9:24",  "10:32",  "10:39",  "12:24",  "15:04",  "16:02",  "16:49",  "17:12",  "18:29", "19:24",  "20:39",  "21:44",  "22:19",  "22:09", " 23:39",  "0:49",  "17:28"};
        String KHADKIDown[] = {"0:41", " 6:28",  "7:28", " 8:28", " 8:26", " 9:28",  "10:36",  "10:43",  "12:28",  "15:08",  "16:06",  "16:53",  "17:16",  "18:33", "19:28",  "20:43",  "21:48",  "22:23",  "22:13", " 23:43",  "0:53",  "17:33"};
        String SHIVAJINAGARDown[] = {"0:46", " 6:33",  "7:33", " 8:33", " 8:31", " 9:33",  "10:41",  "10:48",  "12:33",  "15:13",  "16:09",  "16:58",  "17:21",  "18:38", "19:33",  "20:48",  "21:54",  "22:28",  "22:18", " 23:48",  "0:58",  "17:53"};
        String PUNESTATIONDown[] = {"0:58", " 6:45",  "7:45", " 8:45", " 8:43", " 9:45",  "",  "11",  "12:45",  "15:25",  "16:21",  "17:1",  "17:33",  "18:5", "19:45",  "21",  "22:06",  "22:4",  "22:3", " 24",  "1:1",  "18:15"};

        String PUNEUp[] = {"0:1", " 4:45", " 5:45", " 6:3", " 6:5", " 8:05", " 8:57", " 9:55", "  XX:XX", "  11:55", " 13:00", " 15", " 15:4", " 16:25", " 17:1", " 18:02", " 19:05", "  XX:XX", " 20", " 21:05", " 23", " 11:15"} ;
        String SHIVAJINAGARUp[] = {"0:16", " 4:51", " 5:51", " 6:36", " 6:56", " 8:11", " 9:03", " 10:01", " 10:5", " 12:01", " 13:06", " 15:06", " 15:46", " 16:31", " 17:16", " 18:08", " 19:11", " 19:35", " 20:06", " 21:11", " 23:06", " 11:2"} ;
        String KHADKIUp[] = {"0:21", " 4:56", " 5:56", " 6:41", " 7:01", " 8:16", " 9:08", " 10:06", " 10:56", " 12:06", " 13:11", " 15:11", " 15:51", " 16:36", " 17:21", " 18:13", " 19:16", " 19:39", " 20:11", " 21:16", " 23:11", " 11:25"} ;
        String DAPODIUp[] = {"0:26", " 5:01", " 6:01", " 6:46", " 7:06", " 8:21", " 9:13", " 10:11", " 11:01", " 12:11", " 13:16", " 15:16", " 15:56", " 16:41", " 17:26", " 18:18", " 19:21", " 19:44", " 20:16", " 21:21", " 23:16", " 11:3"} ;
        String KASARWADIUp[] = {"0:3", " 5:05", " 6:05", " 6:5", " 7:1", " 8:25", " 9:17", " 10:15", " 11:05", " 12:15", " 13:2", " 15:2", " 16", " 16:45", " 18:3", " 18:22", " 19:25", " 19:48", " 20:2", " 21:25", " 23:2", " 11:35"} ;
        String PIMPRIUp[] = {"0:33", " 5:08", " 6:08", " 6:53", " 7:13", " 8:28", " 9:2", " 10:18", " 11:08", " 12:18", " 13:23", " 15:23", " 16:03", " 16:47", " 18:33", " 18:25", " 19:28", " 19:51", " 20:23", " 21:27", " 23:23", " 11:4"} ;
        String CHINCHWADUp[] = {"0:37", " 5:12", " 6:12", " 6:57", " 7:17", " 8:32", " 9:24", " 10:22", " 11:12", " 12:22", " 13:27", " 15:27", " 16:07", " 16:52", " 18:37", " 18:29", " 19:32", " 19:55", " 20:27", " 21:32", " 23:27", " 11:45"} ;
        String AKURDIUp[] = {"0:42", " 5:17", " 6:17", " 7:02", " 7:22", " 8:37", " 9:29", " 10:27", " 11:17", " 12:27", " 13:32", " 15:32", " 16:12", " 16:57", " 18:42", " 18:34", " 19:37", " 20", " 20:32", " 21:37", " 23:32", " 11:5"} ;
        String DEHUROADUp[] = {"0:47", " 5:22", " 6:22", " 7:07", " 7:27", " 8:42", " 9:34", " 10:32", " 11:22", " 12:32", " 13:37", " 15:37", " 16:17", " 17:02", " 18:47", " 18:39", " 19:42", " 20:05", " 20:37", " 21:42", " 23:37", " 11:55"} ;
        String BEGDEWADIUp[] = {"0:51", " 5:26", " 6:26", " 7:11", " 7:31", " 8:47", " 9:38", " 10:36", " 11:26", " 12:36", " 13:41", " 15:41", " 16:21", " 17:06", " 18:51", " 18:43", " 19:46", " 20:1", " 20:41", " 21:46", " 23:41", " 12"} ;
        String GORAWADIUp[] = {"0:54", " 5:29", " 6:29", " 7:14", " 7:34", " 8:49", " 9:41", " 10:39", " 11:29", " 12:39", " 13:44", " 15:44", " 16:24", " 17:09", " 18:54", " 18:46", " 19:49", " 20:13", " 20:44", " 21:49", " 23:44", " 12:1"} ;
        String TALEGAONUp[] = {"0:59", " 5:34", " 6:34", " 7:19", " 7:39", " 8:54", " 9:46", " 10:45", " 11:35", " 12:44", " 13:49", " 15:49", " 16:29", " 17:14", " 18:59", " 18:51", " 19:54", " 20:18", " 20:49", " 21:54", " 23:49", " 12:25"} ;
        String WADGAONUp[] = {"1:04", " 5:39", " 6:39", " 7:23", "  XX:XX", " 8:59", "  XX:XX", " 10:5", " 11:40", " 12:49", " 13:54", " 15:54", "  XX:XX", " 17:19", "  XX:XX", " 18:56", " 19:59", " 20:23", " 20:54", " 21:59", "  XX:XX", " 12:3"} ;
        String KANHEUp[] = {"1:09", " 5:44", " 6:44", " 7:29", "  XX:XX", " 9:04", "  XX:XX", " 10:54", " 11:44", " 12:54", " 13:59", " 16:59", "  XX:XX", " 17:24", "  XX:XX", " 19:01", " 20:04", " 20:28", " 20:59", " 22:04", "  XX:XX", " 12:35"} ;
        String KAMSHETUp[] = {"1:13", " 5:48", " 6:48", " 7:33", "  XX:XX", " 9:08", "  XX:XX", " 10:59", " 11:49", " 13:06", " 14:03", " 16:03", "  XX:XX", " 17:28", "  XX:XX", " 19:05", " 20:08", " 20:32", " 21:03", " 22:08", "  XX:XX", " 12:4"} ;
        String MALWALIUp[] = {"1:21", " 5:56", " 6:56", " 7:41", "  XX:XX", " 9:16", "  XX:XX", " 11:07", " 11:57", " 13:20", " 14:12", " 16:12", "  XX:XX", " 17:36", "  XX:XX", " 19:14", " 20:16", " 20:4", " 21:11", " 22:16", "  XX:XX", " 12:48"} ;
        String LONAWALAUp[] = {"1:21", " 5:56", " 6:56", " 7:41", "  XX:XX", " 9:16", "  XX:XX", " 11:07", " 11:57", " 13:20", " 14:12", " 16:12", "  XX:XX", " 17:36", "  XX:XX", " 19:14", " 20:16", " 20:4", " 21:11", " 22:16", "  XX:XX", " 12:48"} ;


        Train train;
        for(int i=0;i<22;i++) {
            train = new Train(LONAWALADown[i], DEHUROADDown[i], PUNESTATIONDown[i],PUNEUp[i], DEHUROADUp[i], LONAWALAUp[i]);
            trainList.add(train);
        }

        mAdapter.notifyDataSetChanged();
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
            Intent i = new Intent(TrainActivity.this, checktask.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(TrainActivity.this, TrainActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(TrainActivity.this, BufferActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
