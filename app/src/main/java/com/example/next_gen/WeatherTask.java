package com.example.next_gen;
import android.os.AsyncTask;


public class WeatherTask extends AsyncTask<String, Void, Weather>
{
    private final MyWeatherTaskListener mListener;

    WeatherTask(MyWeatherTaskListener pListener)
    {
        this.mListener = pListener;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        mListener.onMyWeatherTaskPreExecute();
    }

    @Override
    protected Weather doInBackground(String... strings)
    {
        Weather myWeather = null;

        //Fetch Weather
        String jsonStr = WeatherClient.fetchWeather(strings[0]);

        //Parsing Weather
        if (jsonStr != null)
        {
            myWeather = JSONParser.getMyWeather(jsonStr);
        }
        return myWeather;
    }

    @Override
    protected void onPostExecute(Weather myWeather)
    {
        super.onPostExecute(myWeather);
        mListener.onMyWeatherTaskPostExecute(myWeather);
    }
}

interface MyWeatherTaskListener
{
    void onMyWeatherTaskPreExecute();
    void onMyWeatherTaskPostExecute(Weather myWeather);
}