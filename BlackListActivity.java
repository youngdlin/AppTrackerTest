package com.example.young.apptrackertest;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by Young on 4/2/2016.
 */
public class BlackListActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.recycler_view);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<BlackListItem> blackListItems = new ArrayList<>();


        for (String appName : MainActivity.Blacklist) {
            blackListItems.add(new BlackListItem(appName,false));
        }

        BlackListAdapter blackListAdapter = new BlackListAdapter(getApplicationContext(), blackListItems);
        recyclerView.setAdapter(blackListAdapter);
    }
}
