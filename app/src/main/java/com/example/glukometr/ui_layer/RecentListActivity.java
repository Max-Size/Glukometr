package com.example.glukometr.ui_layer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.glukometr.R;
import com.example.glukometr.data_layer.Measuring;
import com.example.glukometr.data_layer.MeasuringAdapter;

import java.util.ArrayList;

public class RecentListActivity extends AppCompatActivity {
    public static ArrayList<Measuring> measurings = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_list);
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        MeasuringAdapter measuringAdapter = new MeasuringAdapter(this,measurings);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(measuringAdapter);
    }
    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}