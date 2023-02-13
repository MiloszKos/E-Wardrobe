package com.example.next_gen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.next_gen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MyWeatherTaskListener {
    private ActivityMainBinding binding;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                onStop();
                startActivity(new Intent(this, Wardrobe.class));
                finish();
                break;
            case R.id.item2:
                onStop();
                Intent i = new Intent(MainActivity.this, Sets.class);
                i.putExtra("temp",temp);
                startActivity(i);
                finish();
                break;
            case R.id.item3:
                onStop();
                Intent j = new Intent(MainActivity.this, Market.class);
                startActivity(j);
                finish();
                break;
            case R.id.item4:
                onStop();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;

        }

        return true;
    }

int temp;
    private String mCity = "Warsaw";
    private final String mCountry = "Poland";
    String mApiKey = "eace0bd8251cf6ab043ab9858b796256";
    TextView opis,suggestion;
    String welcome, suggest;
    Button generate, manage;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        opis=findViewById(R.id.IntroTextView);
        suggestion=findViewById(R.id.SuggestionTextView);


        welcome = "Witaj w aplikacji! ";
                suggest = "Poniżej znajduje się prognoza pogody." + System.lineSeparator() + "Co chcesz teraz zrobić?";
        opis.setText(welcome);
        suggestion.setText(suggest);
        String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=" + mCity + "&APPID=" + mApiKey;
        new WeatherTask(this).execute(weatherURL);

        generate = (Button) findViewById(R.id.generate_main);
        manage = (Button) findViewById(R.id.wardrobe_main);

        generate.setOnClickListener(view1 -> {
            Intent i = new Intent(this, RandomSetScreen.class);
            i.putExtra("temp",temp);
            i.putExtra("style","Casual");
            startActivity(i);
            this.onPause();
        });

        manage.setOnClickListener(view1 -> {
            Intent j = new Intent(this, Wardrobe.class);
            startActivity(j);
            this.onPause();
        });
    }

    @Override
    public void onMyWeatherTaskPreExecute()
    {
        binding.myLoadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMyWeatherTaskPostExecute(Weather Weather)
    {
        if (Weather != null)
        {
            binding.cityTextView.setText(mCity);
            binding.countryTextView.setText(mCountry);

            binding.weatherConditionTextView.setText(Weather.getWeatherCondition());
            binding.weatherDescriptionTextView.setText(Weather.getWeatherDescription());

            temp = Math.round(Weather.getTemperature() - 273.15f);
            Intent i = new Intent(MainActivity.this,
                    RandomSetScreen.class);
            i.putExtra("temp", temp);
            String tempStr = String.valueOf(temp);
            binding.temperatureTextView.setText(tempStr);

            String imgUrl = "https://openweathermap.org/img/wn/" + Weather.getWeatherIconStr() + "@2x.png";

            Glide.with(MainActivity.this)
                    .asBitmap()
                    .load(imgUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(binding.weatherIconImageView);
        }
        binding.myLoadingLayout.setVisibility(View.GONE);

}}