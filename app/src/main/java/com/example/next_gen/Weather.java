package com.example.next_gen;
public class Weather {
    private String weatherCondition;
    private String weatherDescription;
    private String weatherIconStr;
    private float temperature;
    private int temp;

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIconStr() {
        return weatherIconStr;
    }

    public void setWeatherIconStr(String weatherIconStr) {
        this.weatherIconStr = weatherIconStr;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setTemp(int temp) {this.temp = temp;}

    public int getTemp() {return temp;}
    }
