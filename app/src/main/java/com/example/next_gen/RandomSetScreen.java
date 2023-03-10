package com.example.next_gen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSetScreen extends AppCompatActivity implements MyRecycleViewAdapter.ItemClickListener {


    String second_color, log, season, fin_col, style, tempstr;
    String[] third_color = new String[2];
    ArrayList<ClothRecord> list_final = new ArrayList<ClothRecord>();


    ArrayList<ClothRecord> imageArry = new ArrayList<ClothRecord>();
    ArrayList<ClothRecord> example = new ArrayList<ClothRecord>();
    List<String> season_check = new ArrayList<String>();
    ClothRecord temporary = new ClothRecord();
    List<String> id = new ArrayList<String>();
    Button accept, decline;
    int temp;
    Context context;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("SuspiciousIndentation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_set_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        tempstr = intent.getStringExtra("style");
        style = tempstr;
        Intent intent2 = getIntent();
        temp = intent2.getIntExtra("temp",30);
        MyRecycleViewAdapter adapter;
       context = getApplicationContext();
        accept = (Button) findViewById(R.id.accept);
        decline = (Button) findViewById(R.id.decline);
        DatabaseManager db = new DatabaseManager(this);

        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        List<String> list3 = new ArrayList<String>();
        List<Bitmap> list4 = new ArrayList<Bitmap>();




        Log.d("Wyrzut:", " temp " +temp);

            if (temp > 22) season = "Lato";
            else if (temp > 10 && temp < 22) season = "Wiosna/Jesie??";
            else if (temp < 10) season = "Zima";

        List<ClothRecord> check = db.getAllContacts();
        for (ClothRecord top : check) {
            example.add(top);
            if (top.getHeatlvl()==season)
            season_check.add(top.getHeatlvl());
        }

        if (example.isEmpty()){
            Toast.makeText(context, "Powiniene?? najpierw doda?? kilka ubra?? pasuj??cych do obecnych warunk??w pogodowych.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();}

        Random r = new Random();
        int random_item = r.nextInt(1);

          // if(random_item == 1)
                twocoloured();
              // else threecoloured();

        for (ClothRecord cn : list_final) {
                String log = "ID:" + cn.getID() + " Name: " + cn.getName()
                        + " ,Image: " + cn.getImage() + " Colour " + cn.getColour()
                        + " Category " + cn.getCategory() + " Heatlvl " + cn.getHeatlvl();

                id.add(cn.getImage());
                File imgFile = new File(cn.getImage());

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                list1.add(cn.getName());
                list2.add(cn.getCategory());
                list3.add(cn.getHeatlvl());
                list4.add(myBitmap);
                // Writing Contacts to log
                Log.d("Result: ", log);
                // add contacts data in arrayList
                imageArry.add(cn);

            }

            RecyclerView recyclerView = findViewById(R.id.example);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new MyRecycleViewAdapter(this, list1, list2, list4);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);

            accept.setOnClickListener(view -> {

                db.addSetRecord(new SetRecord(id.get(0), id.get(1), id.get(2), id.get(3)));
                Intent i = new Intent(RandomSetScreen.this, Sets.class);
                startActivity(i);
                finish();
            });

            decline.setOnClickListener(view -> {
                Intent i = new Intent(RandomSetScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            });
        }
    public String findSecondColor(String color){

        switch (color){
            case "Bia??y":
                second_color="Czarny";
                break;
            case "Czarny":
                second_color="Bia??y";
                break;
            case "Zielony":
                second_color="Czerwony";
                break;
            case "??????ty":
                second_color="Fioletowy";
                break;
            case "Pomara??czowy":
                second_color="Niebieski";
                break;
            case "Czerwony":
                second_color="Zielony";
                break;
            case "R??zowy":
                second_color="Zielony";
                break;
            case "Fioletowy":
                second_color="??????ty";
                break;
            case "Niebieski":
                second_color="Pomara??czowy";
                break;
            case "Br??zowy":
                second_color="Bia??y";
                break;
            case "Szary":
                second_color="Czarny";
                break;
        }
        return second_color;
    }
    public String[] findThirdColor(String color){

        switch (color){
            case "Bia??y":
                third_color[0]="Czarny";
                third_color[1]="Szary";
                break;
            case "Czarny":
                third_color[0]="Bia??y";
                third_color[1]="Szary";
                break;
            case "Zielony":
                third_color[0]="Pomara??czowy";
                third_color[1]="Fioletowy";
                break;
            case "??????ty":
                third_color[0]="Niebieski";
                third_color[1]="Czerwony";
                break;
            case "Pomara??czowy":
                third_color[0]="Niebieski";
                third_color[1]="Czerwony";
                break;
            case "Czerwony":
                third_color[0]="??????ty";
                third_color[1]="Niebieski";
                break;
            case "R????owy":
                third_color[0]="Pomara??czowy";
                third_color[1]="Niebieski";
                break;
            case "Fioletowy":
                third_color[0]="Pomara??czowy";
                third_color[1]="Zielony";
                break;
            case "Niebieski":
                third_color[0]="??????ty";
                third_color[1]="Czerwony";
                break;
            case "Br??zowy":
                third_color[0]="Pomara??czowy";
                third_color[1]="Niebieski";
                break;
            case "Szary":
                third_color[0]="Czarny";
                third_color[1]="Bia??y";
        }
        return third_color;
    }


    @Override
    public void onItemClick(View view, int position) {

    }

    public void  twocoloured(){

        ArrayList<ClothRecord> list_temp = new ArrayList<ClothRecord>();
        list_temp.clear();
        DatabaseManager db = new DatabaseManager(this);
        List<ClothRecord> absolute = db.getSeasonalClothes("anything", "G??ra", season, style);
        for (ClothRecord top : absolute) {
            list_temp.add(top);
                                         }
        if(list_temp.isEmpty()){
            Toast.makeText(context, "Brakuje pasuj??cych ubra?? na g??rn?? cz?????? cia??a. Dodaj jakie?? i spr??buj ponownie", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();}
        absolute.clear();
        Random r = new Random();
        int random_item = r.nextInt(list_temp.size());
        Log.d("Array size", "wynosi "+list_temp.size());
        Log.d("Integer size", "wynosi "+random_item);
        list_final.add(list_temp.get(r.nextInt(list_temp.size())));
        Log.d("TOP ", "is" + list_final);
        temporary = list_final.get(0);
        String color = temporary.getColour();
        String color2nd = findSecondColor(color);

        list_temp.clear();
        int dec = r.nextInt(2);
        if (dec==1) fin_col = color; else fin_col = color2nd;
        Log.d("Random color", fin_col);

        absolute = db.getSeasonalClothes(fin_col, "D????",season, style);
        if (absolute.isEmpty()) {
            if (fin_col==color2nd) {fin_col=color; absolute = db.getSeasonalClothes(fin_col, "D????",season, style);}
            else fin_col=color2nd; absolute = db.getSeasonalClothes(fin_col, "D????",season, style);}
        for (ClothRecord top : absolute) {
            log = "ID:" + top.getID() + " Name: " + top.getName()
                    + " ,Image: " + top.getImage() + " Colour " + top.getColour()
                    + " Category " + top.getCategory() + " Heatlvl " + top.getHeatlvl();

            list_temp.add(top);
            Log.d("D???? ", "is " + log);}


        list_final.add(list_temp.get(r.nextInt(list_temp.size())));
        list_temp.clear();


        absolute.clear();
        dec = r.nextInt(2);
        if (dec==1) fin_col = color; else fin_col = color2nd;
        absolute = db.getSeasonalClothes(fin_col, "Okrycie wierzchnie",season, style);
        if (absolute.isEmpty()) {
            if (fin_col==color2nd) {fin_col=color; absolute = db.getSeasonalClothes(fin_col, "Okrycie wierzchnie",season, style);}
            else fin_col=color2nd; absolute = db.getSeasonalClothes(fin_col, "Okrycie wierzchnie",season, style);
        }
        for (ClothRecord top : absolute) {
            log = "ID:" + top.getID() + " Name: " + top.getName()
                    + " ,Image: " + top.getImage() + " Colour " + top.getColour()
                    + " Category " + top.getCategory() + " Heatlvl " + top.getHeatlvl();

            list_temp.add(top);
            Log.d("Okrycie wierzchnie ", "is" + log);}

        if (list_temp.isEmpty() == false)
        {
        list_final.add(list_temp.get(r.nextInt(list_temp.size())));
        list_temp.clear();
        }

        absolute.clear();
        dec = r.nextInt(2);
        if (dec==1) fin_col = color; else fin_col = color2nd;
        absolute = db.getSeasonalClothes(fin_col, "Obuwie",season, style);
        if (absolute.isEmpty() && fin_col == color) {fin_col=color2nd;  absolute.clear(); absolute = db.getSeasonalClothes(fin_col, "Obuwie",season, style);}
        else if (absolute.isEmpty() && fin_col == color2nd) {fin_col=color;  absolute.clear(); absolute = db.getSeasonalClothes(fin_col, "Obuwie",season, style);}
        for (ClothRecord top : absolute) {
            log = "ID:" + top.getID() + " Name: " + top.getName()
                    + " ,Image: " + top.getImage() + " Colour " + top.getColour()
                    + " Category " + top.getCategory() + " Heatlvl " + top.getHeatlvl();

            list_temp.add(top);
            Log.d("Obuwie ", "is" + log);}
        if (list_temp.isEmpty() == false)
        {
        list_final.add(list_temp.get(r.nextInt(list_temp.size())));
        }

    if(list_final.isEmpty()){
        Toast.makeText(context, "Przykro mi. Nie masz ??adnych ubra?? pasuj??cych do obecnych warunk??w.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    } else if(list_final.size() < 4)  {Toast.makeText(context, "Nie masz zbyt du??o ubra?? do dwukolorowego dopasowania. Spr??buj doda?? wi??cej.", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, Wardrobe.class));}}

    public void  threecoloured(){

        ArrayList<ClothRecord> list_temp = new ArrayList<ClothRecord>();
        DatabaseManager db = new DatabaseManager(this);

        List<ClothRecord> absolute = db.getSeasonalClothes("anything", "G??ra", season, style);

        for (ClothRecord top : absolute) {
            log = "ID:" + top.getID() + " Name: " + top.getName()
                    + " ,Image: " + top.getImage() + " Colour " + top.getColour()
                    + " Category " + top.getCategory() + " Heatlvl " + top.getHeatlvl();
            list_temp.add(top);
            Log.d("TOP ", "is" + log);
        }
        if(list_temp.isEmpty()==true){
            Toast.makeText(context, "Brakuje pasuj??cych ubra?? na g??rn?? cz?????? cia??a. Dodaj jakie?? i spr??buj ponownie", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        absolute.clear();
        Random r = new Random();

        list_final.add(list_temp.get(r.nextInt(list_temp.size())));
        temporary = list_final.get(0);
        String color = temporary.getColour();
        Log.d("Kolor glowny ", " " +color);
        String[] colorset = new String[2];
        colorset = findThirdColor(color);
        list_temp.clear();
        Log.d("Zestaw kolorow","  "+colorset[0] + colorset[1]);

        int dec = r.nextInt(3);
        if (dec==1) fin_col = color; else if (dec==2) fin_col = colorset[0]; else fin_col = colorset[1];
        Log.d("Random color", fin_col);

        absolute = db.getSeasonalClothes(fin_col, "D????",season, style);
        if (absolute.isEmpty()) {
            if (fin_col==color || fin_col==colorset[1]) {fin_col=colorset[0]; absolute = db.getSeasonalClothes(fin_col, "D????",season, style);}
            else {fin_col=colorset[1]; absolute = db.getSeasonalClothes(fin_col, "D????",season, style);}
        }

        if (absolute.isEmpty()) fin_col=color; absolute = db.getSeasonalClothes(fin_col, "D????",season, style);

        for (ClothRecord top : absolute) {
            log = "ID:" + top.getID() + " Name: " + top.getName()
                    + " ,Image: " + top.getImage() + " Colour " + top.getColour()
                    + " Category " + top.getCategory() + " Heatlvl " + top.getHeatlvl();

            list_temp.add(top);
            Log.d("Bottom ", "is" + log);}
        if (list_temp.isEmpty() == false){
        list_final.add(list_temp.get(r.nextInt(list_temp.size())));
        list_temp.clear();}
        absolute.clear();
        dec = r.nextInt(2);
        if (dec==1) fin_col = color; else if (dec==2) fin_col = colorset[0]; else fin_col = colorset[1];
        absolute = db.getSeasonalClothes(fin_col, "Okrycie wierzchnie",season, style);

        if (absolute.isEmpty()) {
            if (fin_col==color || fin_col==colorset[1]) {fin_col=colorset[0]; absolute = db.getSeasonalClothes(fin_col, "Okrycie wierzchnie",season, style);}
            else {fin_col=colorset[1]; absolute = db.getSeasonalClothes(fin_col, "Okrycie wierzchnie",season, style);}
        }

            if (absolute.isEmpty()) fin_col=color; absolute = db.getSeasonalClothes(fin_col, "Okrycie wierzchnie",season, style);

        for (ClothRecord top : absolute) {
            log = "ID:" + top.getID() + " Name: " + top.getName()
                    + " ,Image: " + top.getImage() + " Colour " + top.getColour()
                    + " Category " + top.getCategory() + " Heatlvl " + top.getHeatlvl();

            list_temp.add(top);
            Log.d("Okrycie wierzchnie ", "is" + log);}
        if (list_temp.isEmpty() == false)
        {
        list_final.add(list_temp.get(r.nextInt(list_temp.size())));
        list_temp.clear();
        }

        absolute.clear();
        dec = r.nextInt(2);

        if (dec==1) fin_col = color;
        else if (dec==2) fin_col = colorset[0];
        else fin_col = colorset[1];
        Log.d("Kolor butow"," to " +fin_col);
        absolute = db.getSeasonalClothes(fin_col, "Obuwie",season, style);

        if (absolute.isEmpty()) {
            if (fin_col==color || fin_col==colorset[1]) {fin_col=colorset[0]; absolute = db.getSeasonalClothes(fin_col, "Obuwie",season, style);}
            else {fin_col=colorset[1]; absolute = db.getSeasonalClothes(fin_col, "Obuwie",season, style);}
        }

        if (absolute.isEmpty()) fin_col=color; absolute = db.getSeasonalClothes(fin_col, "Obuwie",season, style);

        for (ClothRecord top : absolute) {
            log = "ID:" + top.getID() + " Name: " + top.getName()
                    + " ,Image: " + top.getImage() + " Colour " + top.getColour()
                    + " Category " + top.getCategory() + " Heatlvl " + top.getHeatlvl();

            list_temp.add(top);
            Log.d("Obuwie ", "is" + log);}
        if (list_temp.isEmpty() == false){
        list_final.add(list_temp.get(r.nextInt(list_temp.size())));
        list_temp.clear();}
        if(list_final.isEmpty()){
            Toast.makeText(context, "Wybacz. Nie masz zbyt wiele ubra?? pasuj??cych do generatora.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();}
        else if (list_final.size() < 4) {Toast.makeText(context, "Nie masz zbyt du??o ubra?? do tr??jkolorowego dopasowania. Dodaj jakies i spr??buj ponownie.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Wardrobe.class));}}
}
