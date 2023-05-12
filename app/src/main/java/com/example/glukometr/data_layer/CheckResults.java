package com.example.glukometr.data_layer;

import java.util.Collections;
import java.util.List;

public class CheckResults {
    private static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
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
    public static int fromNumberToDegree(double number){
        if(number>= 3 && number<=6){
            return 72+ (int) ((number-3D)/(3D/36D));
        }else if(number >=0 && number<3) {
            return (int) (number/(3D/72D));
        }else if(number>6 && number<=11){
            return 108+ (int) ((number-6D)/(5D/72D));
        }
        return 180;
    }
    public static double changeCurrentResult(double currentResult){
        double change = rnd(-5,5);
        change/=10;
        if(currentResult+change<0){
            currentResult=0.5;
        }else if(currentResult+change>11){
            currentResult=10.5;
        }else {
            currentResult += change;
        }
        return currentResult;
    }
}
