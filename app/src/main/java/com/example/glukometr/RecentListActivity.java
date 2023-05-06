package com.example.glukometr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;

public class RecentListActivity extends AppCompatActivity {
    public static ArrayList<Measuring> measurings = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_list);
        //setInitialData();
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        MeasuringAdapter measuringAdapter = new MeasuringAdapter(this,measurings);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(measuringAdapter);
        //notifying(measuringAdapter);
    }
    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void notifying(MeasuringAdapter measuringAdapter){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                measuringAdapter.notifyItemInserted(measurings.size()-1);
                handler.post(this);
            }
        },20000);
    }
    private void setInitialData(){
        measurings.add(new Measuring("20 April 2023 10.23","High","OK"));
    }
}