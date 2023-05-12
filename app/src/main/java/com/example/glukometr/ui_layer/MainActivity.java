package com.example.glukometr.ui_layer;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.glukometr.data_layer.Client;
import com.example.glukometr.data_layer.Measuring;
import com.example.glukometr.R;
import com.example.glukometr.data_layer.CheckResults;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    int prevDegrees=90;
    double currentResult= 4.5;
    private final List<Double> lastMeasures = new ArrayList<>();
    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(){
            @Override
            public void handleMessage( Message msg) {
                ImageView arrow = findViewById(R.id.arrow);
                int newDegrees = msg.what;
                RotateAnimation rotateAnimation = new RotateAnimation(prevDegrees,newDegrees, Animation.RELATIVE_TO_SELF,1F,Animation.RELATIVE_TO_SELF,0.5F);
                prevDegrees=newDegrees;
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setDuration(1000);
                rotateAnimation.setRepeatCount(0);
                arrow.startAnimation(rotateAnimation);
            }
        };
        generateRandomMeasuring();
        Client client = new Client();
        client.connectToServer();
    }
    public void onClickRotate(View view){
        ImageView arrow = findViewById(R.id.arrow);
        RotateAnimation rotateAnimation = new RotateAnimation(0,180, Animation.RELATIVE_TO_SELF,(float)1,Animation.RELATIVE_TO_SELF,(float)0.5);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(0);
        arrow.startAnimation(rotateAnimation);
    }
    public void RecentOnClick(View view){
        Intent intent = new Intent(this, RecentListActivity.class);
        startActivity(intent);
    }
    public void onClickSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    public void generateRandomMeasuring(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    currentResult = CheckResults.changeCurrentResult(currentResult);
                    int newDegrees = CheckResults.fromNumberToDegree(currentResult);
                    if (newDegrees > 180) {
                        newDegrees = 180;
                    }
                    if (newDegrees < 0) {
                        newDegrees = 0;
                    }
                    lastMeasures.add(currentResult);
                    if (lastMeasures.size() == 20) {
                        String wavingValue = CheckResults.defineWaving(lastMeasures);
                        String averageValue = CheckResults.defineAverage(lastMeasures);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", new Locale("ru"));
                        String date = simpleDateFormat.format(new Date());
                        RecentListActivity.measurings.add(new Measuring(date, wavingValue, averageValue));
                        lastMeasures.clear();
                    }
                    handler.sendEmptyMessage(newDegrees);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
    }
}