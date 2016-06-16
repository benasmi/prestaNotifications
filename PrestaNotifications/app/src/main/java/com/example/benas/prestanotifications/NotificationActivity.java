package com.example.benas.prestanotifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecycleAdapter adapter;
    private ArrayList<InfoHolder> data = new ArrayList<InfoHolder>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        data.add(new InfoHolder("255", "1", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));
        data.add(new InfoHolder("255", "0", "sss", "sss", "ps", "spsp", "ssss", "sss", true));


        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        adapter = new RecycleAdapter(this, data);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.Callback callback = new RecyclerViewHelper(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

    }
}