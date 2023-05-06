package com.example.glukometr;

import java.util.Collections;
import java.util.List;

public class CheckResults {
    public static String defineWaving(List<Double> measurements){
        double max = Collections.max(measurements);
        double min = Collections.min(measurements);
        if(max-min>=0 && max-min<2){
            return "Низкий";
        }else if(max-min>=2 && max-min<5){
            return "Средний";
        } else{
            return "Высокий";
        }
    }
    public static String defineAverage(List<Double> measurements){
        double sum = measurements.stream().mapToDouble(Double::doubleValue).sum();
        double averageValue = sum/measurements.size();
        if(averageValue>=0 && averageValue<1.5){
            return "Сильно ниже нормы";
        }else if(averageValue>=1.5 && averageValue<3){
            return "Ниже нормы";
        }else if(averageValue>=3 && averageValue<=6){
            return "Норма";
        } else if (averageValue>6 && averageValue<=8){
            return "Выше нормы";
        }else{
            return "Сильно выше нормы";
        }
    }
}
