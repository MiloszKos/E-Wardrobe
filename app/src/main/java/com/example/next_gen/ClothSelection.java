package com.example.next_gen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClothSelection extends AppCompatActivity implements ItemRecyclerViewAdapter.ItemClickListener {
    ItemRecyclerViewAdapter adapter;
    String[] source = new String[10];
    ArrayList<ClothRecord> list_final = new ArrayList<ClothRecord>();
    List<Bitmap> list = new ArrayList<Bitmap>();
    DatabaseManager db;
    Button done;
   List<String> id = new ArrayList<String>();
    ClothRecord temporary = new ClothRecord();
    List<Integer> cloth_id = new ArrayList<Integer>();

    ArrayList<SetRecord> sets = new ArrayList<SetRecord>();
    ArrayList<ClothRecord> absolute = new ArrayList<ClothRecord>();

    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.item_selection_fragment);
        super.onCreate(savedInstanceState);
        db = new DatabaseManager(this);
        done = (Button) findViewById(R.id.done);

        List<ClothRecord> absolute = db.getAllContacts();
        for (ClothRecord cn : absolute) {
            String log = cn.getImage();
            Log.d("obrazek: ", log);
            cloth_id.add(cn.getID());
            File imgFile = new File(log);

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Log.d("Result: ", log);
            list.add(myBitmap);


        }

        RecyclerView recyclerView = findViewById(R.id.cloth_selection);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        adapter = new ItemRecyclerViewAdapter(this, list);
        adapter.itemClickListener(this);
        recyclerView.setAdapter(adapter);

        done.setOnClickListener(view -> {
            Log.d("Insert: ", "Inserting set..");
            for (ClothRecord cn : list_final) {
                String log = "ID:" + cn.getID() + " Name: " + cn.getName()
                        + " ,Image: " + cn.getImage() + " Colour " + cn.getColour()
                        + " Category " + cn.getCategory() + " Heatlvl " + cn.getHeatlvl();
Log.d("Nowa baza", "To tak: " + log);

                id.add(cn.getImage());
            }

            db.addSetRecord(new SetRecord(id.get(0), id.get(1), id.get(2), id.get(3)));
            Intent i = new Intent(ClothSelection.this, Sets.class);
            startActivity(i);
            finish();
        });

    }


    public void onItemClick(View view, int position) {

int i=0;
if (i<4){
            list_final.add(db.getClothRecord(position+1));
            Log.d("Licznik", "pozycja " + temporary);
            Toast.makeText(this, "Licznik: " +position, Toast.LENGTH_SHORT).show();}
else {Toast.makeText(this, "Wybrales maksymalna liczbe elementow", Toast.LENGTH_SHORT).show();}


    }

}