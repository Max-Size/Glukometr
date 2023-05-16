package com.example.glukometr.data_layer;

public class Measuring {
    private String fullDate;
    private String waving;
    private String averageValue;

    private String chartURi;

    public Measuring(String fullDate,String waving,String averageValue){
        this.fullDate = fullDate;
        this.waving = waving;
        this.averageValue = averageValue;
    }

    public Measuring(String fullDate,String waving,String averageValue,String chartURi){
        this.fullDate = fullDate;
        this.waving = waving;
        this.averageValue = averageValue;
        this.chartURi= chartURi;
    }

    public String getChartURi() {
        return chartURi;
    }

    public void setChartURi(String chartURi) {
        this.chartURi = chartURi;
    }
    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate(String fullDate) {
        this.fullDate = fullDate;
    }

    public String getWaving() {
        return waving;
    }

    public void setWaving(String waving) {
        this.waving = waving;
    }

    public String getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(String averageValue) {
        this.averageValue = averageValue;
    }
}
