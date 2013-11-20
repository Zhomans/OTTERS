package com.sprint3.otters;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by wolflyra on 11/20/13.
 */
public class ListActivity extends Activity {

    String size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            size = extras.getString("size");
        } else {
            size = "small";
        }

        final TextView title = (TextView) findViewById(R.id.typeTextView);
        title.setText(size);

        DBHandler db = new DBHandler(this);
        db.open();
        ArrayList<Task> tasks = db.getTasksBySize(size);

        ListView taskList = (ListView) findViewById(R.id.taskList);

        TaskListAdapter taskListAdapter = new TaskListAdapter(this, R.layout.task_list_item, tasks);

        taskList.setAdapter(taskListAdapter);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(getApplicationContext(), ViewPagerActivity.class);
                in.putExtra("size", size);
                in.putExtra("start",i);
                startActivity(in);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                return true;
            case R.id.action_detail:
                Intent i = new Intent(getApplicationContext(), ViewPagerActivity.class);
                i.putExtra("size", size);
                i.putExtra("start", 0);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}