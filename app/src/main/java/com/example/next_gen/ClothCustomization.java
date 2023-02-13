package com.example.next_gen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ClothCustomization extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner_col, spinner_cat, spinner_heat, spinner_name;
    String color, category, name, heat, uritemp, style;

    ImageView current_cloth;
    DatabaseManager db;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customization_activity);
        DatabaseManager db = new DatabaseManager(this);

        spinner_cat = findViewById(R.id.spinner1);
        spinner_name = findViewById(R.id.spinner2);
        spinner_heat = findViewById(R.id.spinner4);
        confirm = (Button) findViewById(R.id.confirm);
        current_cloth = findViewById(R.id.cloth_preview);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.colour, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cat.setAdapter(adapter1);
        spinner_cat.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.heatlvl, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_heat.setAdapter(adapter3);
        spinner_heat.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.cloth_name, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_name.setAdapter(adapter4);
        spinner_name.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String filesrc = extras.getString("temp");
            File imgFile = new  File(filesrc);
            Log.d("Recovered path", filesrc);
            if(imgFile.exists()){
                Bitmap bmp = BitmapFactory.decodeFile(filesrc);
            current_cloth.setImageBitmap(bmp);
                Uri uri = Uri.parse(filesrc);
                uritemp = uri.toString();}

            confirm.setOnClickListener(view -> {
                switch(name) {
                    case "Podkoszulek":
                        color = "Góra";
                        break;
                    case "Top":
                        color = "Góra";
                        break;
                    case "Bluza":
                        color = "Góra";
                        break;
                    case "Sweter":
                        color = "Góra";
                        break;
                    case "Koszula":
                        color = "Góra";
                        break;
                    case "Spodnie":
                        color = "Dół";
                        break;
                    case "Spódnica":
                        color = "Dół";
                        break;
                    case "Spodenki":
                        color = "Dół";
                        break;
                    case "Marynarka":
                        color = "Góra";
                        break;
                    case "Sukienka":
                        color = "Komplet";
                        break;
                    case "Kurtka":
                        color = "Okrycie wierzchnie";
                        break;
                    case "Płaszcz":
                        color = "Okrycie wierzchnie";
                        break;
                    case "Garnitur":
                        color = "Komplet";
                        break;
                    case "Adidasy":
                        color = "Obuwie";
                        break;
                    case "Tenisówki":
                        color = "Obuwie";
                        break;
                    case "Botki":
                        color = "Obuwie";
                        break;
                    case "Kozaki":
                        color = "Obuwie";
                        break;
                    case "Półbuty":
                        color = "Obuwie";
                        break;
                    case "Czółenka":
                        color = "Obuwie";
                        break;
                    case "Suknia":
                        color = "Komplet";
                        break;
                }

                Log.d("Insert: ", "Inserting ..");

                db.addClothRecord(new ClothRecord(name, uritemp, category, color, heat));
                Intent i = new Intent(ClothCustomization.this,
                        Wardrobe.class);
                startActivity(i);
                finish();
            });
        }

    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3) {
        int id = parent.getId();
        switch (id) {
            case R.id.spinner1:

                category = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), category, Toast.LENGTH_SHORT).show();
                break;
            case R.id.spinner2:

                name = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), name, Toast.LENGTH_SHORT).show();
                break;
            case R.id.spinner4:

                heat = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), heat, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }



    public void onNothingSelected (AdapterView < ? > parent){

        }

}

