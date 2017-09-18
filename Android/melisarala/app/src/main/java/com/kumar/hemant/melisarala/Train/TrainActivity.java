package com.kumar.hemant.melisarala.Train;

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
import android.widget.TextView;

import com.kumar.hemant.melisarala.Checklist.ChecklistActivity;
import com.kumar.hemant.melisarala.Checklist.checktask;
import com.kumar.hemant.melisarala.DividerItemDecoration;
import com.kumar.hemant.melisarala.R;
import com.kumar.hemant.melisarala.RTO.RTOActivity;

import java.util.ArrayList;
import java.util.List;

public class TrainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private List<Train> trainList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TrainsAdapter mAdapter;
    public String stationName;
    private TextView tvUpMiddleHeading, tvDownMiddleHeading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_train);
        mAdapter = new TrainsAdapter(trainList);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager2);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        stationName = intent.getStringExtra(SearchStationActivity.EXTRA_MESSAGE);
        if(stationName == null)
            stationName = "Dehuroad";
        tvDownMiddleHeading = (TextView) findViewById(R.id.downMiddleHeading);
        tvDownMiddleHeading.setText(stationName);
        tvUpMiddleHeading = (TextView) findViewById(R.id.upMiddleHeading);
        tvUpMiddleHeading.setText(stationName);
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
        String StationTitleDown[] = {" 0:05T "," 5:20 "," 6:35 "," 7:50T "," 7:25 "," 8:20 "," 9:57 "," 10:10 "," 11:30 "," 14:00 "," 14:55 "," 15:40 "," 16:40T "," 17:25 "," 18:20 "," 19:00 "," 19:35 "," 20:40 "," 21:35 "," 22:05 "," 22:35 "," 23:40 "," 16:10 "};
        String LONAWALADown[] =     {" 0:05T "," 5:20 "," 6:35 "," 7:50T "," 7:25 "," 8:20 "," 9:57 "," 10:10 "," 11:30 "," 14:00 "," 14:55 "," 15:40 "," 16:40T "," 17:25 "," 18:20 "," 19:00 "," 19:35 "," 20:40 "," 21:35 "," 22:05 "," 22:35 "," 23:40 "," 16:10 "};
        String MALWALIDown[] =      {" 0:05T "," 5:28 "," 6:41 "," 7:50T "," 7:33 "," 8:28 "," 9:57 "," 10:18 "," 11:38 "," 14:08 "," 15:03 "," 15:48 "," 16:40T "," 17:33 "," 18:28 "," 19:00L "," 19:44 "," 20:48 "," 21:43 "," 22:13 "," 22:43 "," 23:47 "," 16:19 "};
        String KAMSHETDown[] =      {" 0:05T "," 5:35 "," 6:48 "," 7:50T "," 7:39 "," 8:35 "," 9:57 "," 10:25 "," 11:45 "," 14:15 "," 15:10 "," 15:55 "," 16:40T "," 17:40 "," 18:35 "," 19:00L "," 19:51 "," 20:55 "," 21:50 "," 22:20 "," 22:50 "," 23:54 "," 16:27 "};
        String KANHEDown[] =        {" 0:05T "," 5:39 "," 6:52 "," 7:50T "," 7:43 "," 8:39 "," 9:57 "," 10:29 "," 11:49 "," 14:19 "," 15:14 "," 15:59 "," 16:40T "," 17:44 "," 18:39 "," 19:00L "," 19:55 "," 20:59 "," 21:54 "," 22:24 "," 22:54 "," 23:58 "," 16:32 "};
        String WADGAONDown[] =      {" 0:05T "," 5:43 "," 6:57 "," 7:50T "," 7:49 "," 8:43 "," 9:57T "," 10:33 "," 11:53 "," 14:23 "," 15:18 "," 16:04 "," 16:40T "," 17:48 "," 18:43 "," 19:00L "," 19:59 "," 21:03 "," 21:58 "," 22:28 "," 22:58 "," 0:02 "," 16:39 "};
        String TALEGAONDown[] =     {" 0:05 "," 5:48 "," 7:01 "," 7:50 "," 7:54 "," 8:48 "," 9:57 "," 10:38 "," 11:58 "," 14:28 "," 15:23 "," 16:10 "," 16:40 "," 17:53 "," 18:48 "," 19:28 "," 20:04 "," 21:08 "," 22:03 "," 22:33 "," 23:00 "," 0:07 "," 16:48 "};
        String GORAWADIDown[] =     {" 0:09 "," 5:52 "," 7:06 "," 7:54 "," 7:59 "," 8:52 "," 10:01 "," 10:42 "," 12:02 "," 14:32 "," 15:27 "," 16:13 "," 16:44 "," 17:57 "," 18:52 "," 19:32 "," 20:08 "," 21:12 "," 22:07 "," 22:37 "," 23:07 "," 0:11 "," 16:54 "};
        String BEGDEWADIDown[] =    {" 0:12 "," 5:55 "," 7:09 "," 7:57 "," 8:04 "," 8:55 "," 10:04 "," 10:45 "," 12:05 "," 14:35 "," 15:30 "," 16:17 "," 16:47 "," 18:00 "," 18:55 "," 19:35 "," 20:11 "," 21:15 "," 22:10 "," 22:40 "," 23:10 "," 0:14 "," 16:10L "};
        String DEHUROADDown[] =     {" 0:16 "," 5:59 "," 7:13 "," 8:01 "," 8:08 "," 8:59 "," 10:08 "," 10:49 "," 12:09 "," 14:39 "," 15:34 "," 16:21 "," 16:51 "," 18:04 "," 18:59 "," 19:39 "," 20:15 "," 21:19 "," 22:14 "," 22:44 "," 23:14 "," 0:18 "," 17:00 "};
        String AKURDIDown[]       = {" 0:21 "," 6:04 "," 7:19 "," 8:06 "," 8:13 "," 9:04 "," 10:13 "," 10:54 "," 12:14 "," 14:44 "," 15:39 "," 16:24 "," 16:56 "," 18:09 "," 19:04 "," 19:44 "," 20:20 "," 21:24 "," 22:19 "," 22:49 "," 23:19 "," 0:23 "," 17:07 "};
        String CHINCHWADDown[]    = {" 0:25 "," 6:08 "," 7:23 "," 8:10 "," 8:17 "," 9:08 "," 10:17 "," 10:58 "," 12:18 "," 14:48 "," 15:43 "," 16:30 "," 17:00 "," 18:13 "," 19:08 "," 19:48 "," 20:24 "," 21:28 "," 22:23 "," 22:53 "," 23:24 "," 0:27 "," 17:17 "};
        String PIMPRIDown[]       = {" 0:30 "," 6:12 "," 7:28 "," 8:14 "," 8:22 "," 9:12 "," 10:21 "," 11:02 "," 12:22 "," 14:52 "," 15:47 "," 16:34 "," 17:04 "," 18:17 "," 19:12 "," 19:52 "," 20:28 "," 21:32 "," 22:27 "," 22:57 "," 23:28 "," 0:32 "," 17:21 "};
        String KASARWADIDown[]    = {" 0:33 "," 6:15 "," 7:30 "," 8:17 "," 8:25 "," 9:15 "," 10:24 "," 11:05 "," 12:25 "," 14:55 "," 15:50 "," 16:38 "," 17:07 "," 18:20 "," 19:15 "," 19:55 "," 20:31 "," 21:35 "," 22:30 "," 23:00 "," 23:31 "," 0:37 "," 17:24 "};
        String DAPODIDown[]       = {" 0:37 "," 6:19 "," 7:34 "," 8:21 "," 8:29 "," 9:19 "," 10:28 "," 11:09 "," 12:29 "," 14:59 "," 15:54 "," 16:43 "," 17:11 "," 18:24 "," 19:19 "," 19:59 "," 20:35 "," 21:39 "," 22:34 "," 23:04 "," 23:35 "," 0:41 "," 17:28 "};
        String KHADKIDown[]       = {" 0:42 "," 6:23 "," 7:38 "," 8:25 "," 8:33 "," 9:23 "," 10:32 "," 11:13 "," 12:33 "," 15:03 "," 15:58 "," 16:48 "," 17:15 "," 18:28 "," 19:23 "," 20:03 "," 20:39 "," 21:43 "," 22:38 "," 23:08 "," 23:39 "," 0:46 "," 17:33 "};
        String SHIVAJINAGARDown[] = {" 0:47 "," 6:28 "," 7:43 "," 8:30 "," 8:38 "," 9:28 "," 10:40 "," 11:40 "," 12:38 "," 15:08 "," 16:03 "," 16:53 "," 17:20 "," 18:35 "," 19:28 "," 20:25 "," 20:40 "," 21:48 "," 22:43 "," 23:13 "," 23:44 "," 0:51 "," 17:53 "};
        String PUNESTATIONDown[]  = {" 1:05 "," 6:40 "," 7:55 "," 8:40 "," 8:50 "," 9:40 "," 10:40S "," 11:55 "," 12:50 "," 15:20 "," 16:15 "," 17:05 "," 17:30 "," 18:45 "," 19:40 "," 20:25S "," 20:52 "," 22:00 "," 22:55 "," 23:25 "," 0:20 "," 1:35 "," 18:15 "};

        String PUNEUp[] =         {" 0:10 "," 4:45 "," 5:45 "," 6:30 "," 6:50 "," 8:05 "," 8:57 "," 9:55 "," 11:20S "," 12:15 "," 13:00 "," 15:00 "," 15:40 "," 16:25 "," 17:15 "," 18:02 "," 19:05 "," 20:05 "," 20:35S "," 21:02 "," 22:10 "," 23:00 "," 11:15 "};
        String SHIVAJINAGARUp[] = {" 0:16 "," 4:51 "," 5:51 "," 6:30 "," 6:56 "," 8:11 "," 9:03 "," 10:01 "," 11:20 "," 12:21 "," 13:06 "," 15:06 "," 15:46 "," 16:31 "," 17:23 "," 18:10 "," 19:18 "," 20:11 "," 20:35 "," 21:08 "," 22:16 "," 23:06 "," 11:20 "};
        String KHADKIUp[] =       {" 0:21 "," 4:56 "," 5:56 "," 6:41 "," 7:01 "," 8:16 "," 9:08 "," 10:06 "," 11:25 "," 12:24 "," 13:11 "," 15:11 "," 15:51 "," 16:36 "," 17:28 "," 18:15 "," 19:16 "," 20:16 "," 20:40 "," 21:13 "," 22:21 "," 23:11 "," 11:25 "};
        String DAPODIUp[] =       {" 0:26 "," 5:01 "," 6:01 "," 6:46 "," 7:06 "," 8:21 "," 9:13 "," 10:11 "," 11:30 "," 12:31 "," 13:16 "," 15:16 "," 15:56 "," 16:41 "," 17:33 "," 18:20 "," 19:21 "," 20:21 "," 20:45 "," 21:18 "," 22:26 "," 23:16 "," 11:30 "};
        String KASARWADIUp[] =    {" 0:30 "," 5:05 "," 6:05 "," 6:50 "," 7:10 "," 8:25 "," 9:17 "," 10:15 "," 11:34 "," 12:35 "," 13:20 "," 15:20 "," 16:00 "," 16:45 "," 17:37 "," 18:24 "," 19:25 "," 20:25 "," 20:49 "," 21:22 "," 22:30 "," 23:20 "," 11:35 "};
        String PIMPRIUp[] =       {" 0:33 "," 5:08 "," 6:08 "," 6:53 "," 7:13 "," 8:28 "," 9:20 "," 10:18 "," 11:36 "," 12:38 "," 13:23 "," 15:23 "," 16:03 "," 16:48 "," 17:41 "," 18:27 "," 19:28 "," 20:28 "," 20:52 "," 21:25 "," 22:33 "," 23:23 "," 11:40 "};
        String CHINCHWADUp[] =    {" 0:37 "," 5:12 "," 6:12 "," 6:57 "," 7:17 "," 8:32 "," 9:24 "," 10:22 "," 11:41 "," 12:42 "," 13:27 "," 15:27 "," 16:07 "," 16:52 "," 17:45 "," 18:31 "," 19:32 "," 20:32 "," 20:56 "," 21:29 "," 22:37 "," 23:27 "," 11:45 "};
        String AKURDIUp[] =       {" 0:41 "," 5:16 "," 6:16 "," 7:01 "," 7:21 "," 8:36 "," 9:28 "," 10:26 "," 11:45 "," 12:46 "," 13:31 "," 15:31 "," 16:18 "," 16:56 "," 17:50 "," 18:35 "," 19:36 "," 20:36 "," 21:00 "," 21:33 "," 22:41 "," 23:31 "," 11:50 "};
        String DEHUROADUp[] =    {" 0:46 "," 5:21 "," 6:21 "," 7:06 "," 7:26 "," 8:41 "," 9:33 "," 10:31 "," 11:50 "," 12:51 "," 13:36 "," 15:36 "," 16:16 "," 17:01 "," 17:55 "," 18:40 "," 19:41 "," 20:41 "," 21:05 "," 21:38 "," 22:46 "," 23:36 "," 11:55 "};
        String BEGDEWADIUp[] =   {" 0:50 "," 5:25 "," 6:25 "," 7:10 "," 7:30 "," 8:45 "," 9:37 "," 10:35 "," 11:54 "," 12:55 "," 13:40 "," 15:40 "," 16:20 "," 17:05 "," 17:59 "," 18:44 "," 19:45 "," 20:45 "," 21:09 "," 21:42 "," 22:50 "," 23:40 "," 12:00 "};
        String GORAWADIUp[] =    {" 0:53 "," 5:28 "," 6:28 "," 7:13 "," 7:33 "," 8:48 "," 9:40 "," 10:38 "," 11:57 "," 12:58 "," 13:43 "," 15:43 "," 16:23 "," 17:08 "," 18:02 "," 18:47 "," 19:48 "," 20:48 "," 21:12 "," 21:45 "," 22:53 "," 23:43 "," 12:10 "};
        String TALEGAONUp[] =    {" 0:58 "," 5:33 "," 6:33 "," 7:18 "," 7:40 "," 8:53 "," 9:47 "," 10:43 "," 12:02 "," 13:03 "," 13:48 "," 15:48 "," 16:30 "," 17:13 "," 18:07 "," 18:52 "," 19:53 "," 20:53 "," 21:17 "," 21:50 "," 22:58 "," 23:50 "," 12:25 "};
        String WADGAONUp[] =     {" 1:02 "," 5:37 "," 6:37 "," 7:22 "," 7:40T "," 8:57 "," 9:47T "," 10:47 "," 12:06 "," 13:07 "," 13:52 "," 15:52 "," 16:30T "," 17:17 "," 18:11 "," 18:56 "," 19:57 "," 20:57 "," 21:21 "," 21:54 "," 23:02 "," 23:50T "," 12:30 "};
        String KANHEUp[] =       {" 1:06 "," 5:41 "," 6:41 "," 7:26 "," 7:40T "," 9:01 "," 9:47T "," 10:51 "," 12:10 "," 13:11 "," 13:56 "," 15:56 "," 16:30T "," 17:21 "," 18:16 "," 19:01 "," 20:01 "," 21:01 "," 21:25 "," 21:59 "," 23:06 "," 23:50T "," 12:35 "};
        String KAMSHETUp[] =     {" 1:10 "," 5:45 "," 6:45 "," 7:30 "," 7:40T "," 9:05 "," 9:47T "," 10:55 "," 12:14 "," 13:21 "," 14:00 "," 16:00 "," 16:30T "," 17:25 "," 18:20 "," 19:05 "," 20:05 "," 21:05 "," 21:29 "," 22:02 "," 23:10 "," 23:50T "," 12:40 "};
        String MALWALIUp[] =     {" 1:17 "," 5:52 "," 6:52 "," 7:37 "," 7:40T "," 9:12 "," 9:47T "," 11:02 "," 12:21 "," 13:31 "," 14:07 "," 16:10 "," 16:30T "," 17:32 "," 18:28 "," 19:13 "," 20:12 "," 21:12 "," 21:36 "," 22:12 "," 23:17 "," 23:50T "," 12:48 "};
        String LONAWALAUp[] =    {" 1:30 "," 6:05 "," 7:05 "," 7:50 "," 7:40T "," 9:25 "," 9:47T "," 11:15 "," 12:34 "," 13:50 "," 14:20 "," 16:32 "," 16:30T "," 17:45 "," 18:40 "," 19:27 "," 20:25 "," 21:25 "," 21:50 "," 22:23 "," 23:30 "," 23:50T "," 13:00 "};


        Train train;
        for(int i=0;i<22;i++) {
            if(stationName!=null){
                tvUpMiddleHeading.setText(""+stationName);
                tvDownMiddleHeading.setText(""+stationName);
            }
            else{
                tvDownMiddleHeading.setText("Dehuroad");
                tvUpMiddleHeading.setText("Dehuroad");
            }
            if(stationName.contains("MALWALI"))
            {
                train = new Train(LONAWALADown[i], MALWALIDown[i], PUNESTATIONDown[i],PUNEUp[i], MALWALIUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("KAMSHET"))
            {
                train = new Train(LONAWALADown[i], KAMSHETDown[i], PUNESTATIONDown[i],PUNEUp[i], KAMSHETUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("KANHE"))
            {
                train = new Train(LONAWALADown[i], KANHEDown[i], PUNESTATIONDown[i],PUNEUp[i], KANHEUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("WADGAON"))
            {
                train = new Train(LONAWALADown[i], WADGAONDown[i], PUNESTATIONDown[i],PUNEUp[i], WADGAONUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("TALEGAON"))
            {
                train = new Train(LONAWALADown[i], TALEGAONDown[i], PUNESTATIONDown[i],PUNEUp[i], TALEGAONUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("GOREWADI"))
            {
                train = new Train(LONAWALADown[i], GORAWADIDown[i], PUNESTATIONDown[i],PUNEUp[i], GORAWADIUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("BEGDEWADI"))
            {
                train = new Train(LONAWALADown[i], BEGDEWADIDown[i], PUNESTATIONDown[i],PUNEUp[i], BEGDEWADIUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("DEHUROAD"))
            {
                train = new Train(LONAWALADown[i], DEHUROADDown[i], PUNESTATIONDown[i],PUNEUp[i], DEHUROADUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("AKURDI"))
            {
                train = new Train(LONAWALADown[i], AKURDIDown[i], PUNESTATIONDown[i],PUNEUp[i], AKURDIUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("CHINCHWAD"))
            {
                train = new Train(LONAWALADown[i], CHINCHWADDown[i], PUNESTATIONDown[i],PUNEUp[i], CHINCHWADUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("PIMPRI"))
            {
                train = new Train(LONAWALADown[i], PIMPRIDown[i], PUNESTATIONDown[i],PUNEUp[i], PIMPRIUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("KASARWADI"))
            {
                train = new Train(LONAWALADown[i], KASARWADIDown[i], PUNESTATIONDown[i],PUNEUp[i], KASARWADIUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("DAPODI"))
            {
                train = new Train(LONAWALADown[i], DAPODIDown[i], PUNESTATIONDown[i],PUNEUp[i], DAPODIUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("KHADKI"))
            {
                train = new Train(LONAWALADown[i], KHADKIDown[i], PUNESTATIONDown[i],PUNEUp[i], KHADKIUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else if(stationName.contains("SHIVAJINAGAR"))
            {
                train = new Train(LONAWALADown[i], SHIVAJINAGARDown[i], PUNESTATIONDown[i],PUNEUp[i], SHIVAJINAGARUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }
            else
            {
                train = new Train(LONAWALADown[i], DEHUROADDown[i], PUNESTATIONDown[i],PUNEUp[i], DEHUROADUp[i], LONAWALAUp[i]);
                trainList.add(train);
            }

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
        if (id == R.id.action_select_station) {
            //Intent i = new Intent(TrainActivity.this, SearchStationActivity.class);
            Intent i = new Intent(TrainActivity.this, TrainUpDown.class);
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
            Intent i = new Intent(TrainActivity.this, checktask.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(TrainActivity.this, SearchStationActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_add_train) {
            Intent i = new Intent(TrainActivity.this, AddTrain.class);
            startActivity(i);
        } else if (id == R.id.nav_add_station) {
            Intent i = new Intent(TrainActivity.this, MVCView.class);
            startActivity(i);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
