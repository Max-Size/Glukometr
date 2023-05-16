package com.example.glukometr.data_layer;

import com.example.glukometr.R;
import com.example.glukometr.ui_layer.RecentListActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListItemCreate {
    private static void create(List<Double> lastMeasures,Client client){
        String wavingValue = CheckResults.defineWaving(lastMeasures);
        String averageValue = CheckResults.defineAverage(lastMeasures);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", new Locale("ru"));
        String date = simpleDateFormat.format(new Date());
        String URi;
        if(client.connectToServer()){
            client.post(lastMeasures);
            URi = client.getPath();
        }
        else{
            URi = "https://disk.yandex.ru/d/sKmNcjXvoNgU";
        }
        RecentListActivity.measurings.add(new Measuring(date, wavingValue, averageValue,URi));
        lastMeasures.clear();
    }
    public static void checkingAdding(List<Double> lastMeasures,Client client){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (lastMeasures.size() == 20) {
                        ListItemCreate.create(lastMeasures, client);
                    }
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e){
                        throw new RuntimeException();
                    }
                }
            }
        });
        thread.start();
    }
}
