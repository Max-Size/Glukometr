package com.example.glukometr;

public class Measuring {
    private String fullDate;
    private String waving;
    private String averageValue;

    public Measuring(String fullDate,String waving,String averageValue){
        this.fullDate = fullDate;
        this.waving = waving;
        this.averageValue = averageValue;
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
