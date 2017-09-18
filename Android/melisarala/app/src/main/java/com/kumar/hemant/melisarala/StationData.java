package com.kumar.hemant.melisarala;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.kumar.hemant.melisarala.Train.MVCController;

import java.util.List;

public class StationData extends Activity
{
    public static final String APP_TAG = "com.hemant.kumar";
    private ListView lvTask;
    private Button btNewTask;
    private EditText etNewTask;
    private EditText etNewTask2;
    private EditText etNewTask3;
    private EditText etNewTask4;
    private EditText etNewTask5;
    private MVCController controller;
    @Override
    public void onCreate(final Bundle bundle)
    {
        super.onCreate(bundle);
        this.setContentView(R.layout.app_bar_mvc_view);
        this.controller = new MVCController(this);
        this.lvTask = (ListView) this.findViewById(R.id.lvTask);
        this.btNewTask = (Button) this.findViewById(R.id.btNewTask);
        this.etNewTask = (EditText) this.findViewById(R.id.etNewTask);
        this.etNewTask2 = (EditText) this.findViewById(R.id.etNewTask2);
        this.etNewTask3 = (EditText) this.findViewById(R.id.etNewTask3);
        this.etNewTask4 = (EditText) this.findViewById(R.id.etNewTask4);
        this.etNewTask5 = (EditText) this.findViewById(R.id.etNewTask5);
        this.btNewTask.setOnClickListener(this.handleNewTaskEvent);
        this.populateTasks();
    }

    private void populateTasks()
    {
        final List<String> tasks = this.controller.getTrains();
        Log.d(StationData.APP_TAG, String.format("%d found tasks ", tasks.size()));
        this.lvTask.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks.toArray(new String[] {})));
        this.lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id)
            {
                Log.d(StationData.APP_TAG, String.format("task id: %d and position: %d", id, position));
                final TextView v = (TextView) view;
                StationData.this.controller.deleteTrain(v.getText().toString());
                StationData.this.populateTasks();
            }
        });
    }

    private final View.OnClickListener handleNewTaskEvent = new View.OnClickListener()
    {
        @Override
        public void onClick(final View view)
        {
            Log.d(APP_TAG, "New Task button added");
            StationData.this.controller.addTrain(StationData.this.etNewTask.getText().toString(), StationData.this.etNewTask2.getText().toString(), StationData.this.etNewTask3.getText().toString(), StationData.this.etNewTask4.getText().toString(), StationData.this.etNewTask5.getText().toString());
            StationData.this.populateTasks();
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
}
