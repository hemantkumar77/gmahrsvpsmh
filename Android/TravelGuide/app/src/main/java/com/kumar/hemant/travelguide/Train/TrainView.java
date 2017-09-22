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
import android.widget.TextView;

import com.kumar.hemant.travelguide.CheckList.ChecklistActivity;
import com.kumar.hemant.travelguide.R;
import com.kumar.hemant.travelguide.RTO.RTOActivity;

import java.util.List;

public class TrainView extends Activity implements NavigationView.OnNavigationItemSelectedListener
{
    public static final String APP_TAG = "com.hemant.kumar";
    private ListView lvTask;
    private Button btNewTask;
    private EditText etNewTask;
    private EditText etNewTask2;
    private EditText etNewTask3;
    private EditText etNewTask4;
    private EditText etNewTask5;
    private TrainController controller;
    @Override
    public void onCreate(final Bundle bundle)
    {
        super.onCreate(bundle);
        this.setContentView(R.layout.app_bar_train_view);
        this.controller = new TrainController(this);
        this.lvTask = (ListView) this.findViewById(R.id.lvTask);
        this.btNewTask = (Button) this.findViewById(R.id.btNewTask);
        this.etNewTask = (EditText) this.findViewById(R.id.etNewTask);
        this.etNewTask2 = (EditText) this.findViewById(R.id.etNewTask2);
        this.etNewTask3 = (EditText) this.findViewById(R.id.etNewTask3);
        this.etNewTask4 = (EditText) this.findViewById(R.id.etNewTask4);
        this.etNewTask5 = (EditText) this.findViewById(R.id.etNewTask5);
        this.btNewTask.setOnClickListener(this.handleNewTaskEvent);
        this.populateTrains();
    }

    private void populateTrains()
    {
        final List<String> tasks = this.controller.getTrains();
        Log.d(TrainView.APP_TAG, String.format("%d found tasks ", tasks.size()));
        this.lvTask.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks.toArray(new String[] {})));
        this.lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id)
            {
                Log.d(TrainView.APP_TAG, String.format("task id: %d and position: %d", id, position));
                final TextView v = (TextView) view;
                TrainView.this.controller.deleteTrain (v.getText().toString());
                TrainView.this.populateTrains();
            }
        });
    }

    private final View.OnClickListener handleNewTaskEvent = new View.OnClickListener()
    {
        @Override
        public void onClick(final View view)
        {
            Log.d(APP_TAG, "New Task button added");
            TrainView.this.controller.addTrain(TrainView.this.etNewTask.getText().toString(), TrainView.this.etNewTask2.getText().toString(), TrainView.this.etNewTask3.getText().toString(), TrainView.this.etNewTask4.getText().toString(), TrainView.this.etNewTask5.getText().toString());
            TrainView.this.populateTrains();
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
            Intent i = new Intent(TrainView.this, TrainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(TrainView.this, ChecklistActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(TrainView.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(TrainView.this, RTOActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(TrainView.this, SearchStationActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_add_train) {
            Intent i = new Intent(TrainView.this, TrainView.class);
            startActivity(i);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
