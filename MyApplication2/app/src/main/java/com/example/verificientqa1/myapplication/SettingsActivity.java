package com.example.verificientqa1.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ActionsDataSource datasource;
    TextView tv2;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private Button dateBtn;
    static final int DATE_DIALOG_ID = 1;
    ArrayList<String> lista;
    ArrayAdapter<String> adaptor;
    ListView listView, listView2;
    TextView[] tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        listView = (ListView) findViewById(R.id.list);
        listView2 = (ListView) findViewById(R.id.list2);
        Calendar c = Calendar.getInstance();
        int hour_int = c.get(Calendar.HOUR_OF_DAY);
        String hour_string="";
        if(hour_int<10)
            hour_string="0"+hour_int;
        else
            hour_string=""+hour_int;

        String[] to_pune = new String[] {
                "05:59",
                "07:14",
                "08:01T-",
                "08:09",
                "08:59",
                "10:08TS",
                "10:49",
                "12:09",
                "14:39",
                "15:34",
                "16:24",
                "16:47T-",
                "17:00-K",
                "18:04",
                "18:59",
                "19:39-S",
                "20:14",
                "21:19",
                "22:14",
                "22:44",
                "23:14",
                "00:36"
        };

        String[] from_pune = new String[] {
                "00:10",
                "04:45",
                "05:45",
                "06:30",
                "06:50-T",
                "07:20-M",
                "08:05",
                "08:57-T",
                "09:55",
                "11:15-K",
                "11:20S-",
                "12:15",
                "13:00",
                "15:00",
                "15:40-T",
                "16:25",
                "17:15",
                "18:02",
                "19:05",
                "20:05",
                "20:35S-",
                "21:02",
                "22:10",
                "23:30"
        };

        int current_to_pune=0, current_from_pune=0;


        for(int i=1;i<to_pune.length;i++)
        {
            if(Integer.valueOf(to_pune[i].substring(0,2))<Integer.valueOf(hour_string.substring(0,2)))
            {
                current_to_pune=i;
            }
            else
                break;
        }

        for(int i=1;i<from_pune.length;i++)
        {
            if(Integer.valueOf(from_pune[i].substring(0,2))<Integer.valueOf(hour_string.substring(0,2)))
            {
                current_from_pune=i;
            }
            else
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, to_pune);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, from_pune);

        // Assign adapter to ListView
        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);
        listView.setSelection(current_to_pune);
        listView2.setSelection(current_from_pune);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    public void onClick(View view) {
    }
    //adapter.notifyDataSetChanged();
    private String round(float countAction) {
        // TODO Auto-generated method stub
        return null;
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
        getMenuInflater().inflate(R.menu.main, menu);
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
            Intent i = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(SettingsActivity.this, RTOActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(SettingsActivity.this, LocalActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(SettingsActivity.this, SettingsActivity.class);
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
