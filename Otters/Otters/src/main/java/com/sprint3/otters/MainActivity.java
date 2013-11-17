package com.sprint3.otters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                /*
                * Creating some sample test data to see what the layout looks like.
                * You should eventually delete this.
                */
        TitleFeedItem item1 = new TitleFeedItem("SMALL", "small");
        TitleFeedItem item2 = new TitleFeedItem("MEDIUM", "medium");
        TitleFeedItem item3 = new TitleFeedItem("BIG", "big");
        final List<TitleFeedItem> sampleData = new ArrayList<TitleFeedItem>();
        sampleData.add(item1);
        sampleData.add(item2);
        sampleData.add(item3);

        // Set up the ArrayAdapter for the feedList
        TitleFeedListAdapter feedListAdapter = new TitleFeedListAdapter(this.getApplicationContext(), sampleData);
        ListView feedList = (ListView) findViewById(R.id.titles);
        feedList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

                Intent i = new Intent(getApplicationContext(), MakeTask.class); // creates a new intent i, which is how Android passes information between activities, and defines this intent as a way to navigate to the SecondActivity
                i.putExtra("size", sampleData.get(pos).size);
                startActivity(i); // tells Android to make the intent active

                return true;
            }
        });
        feedList.setAdapter(feedListAdapter);
    }
}
    

