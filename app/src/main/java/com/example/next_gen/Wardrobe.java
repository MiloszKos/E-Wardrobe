package com.example.next_gen;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Wardrobe extends AppCompatActivity implements MyRecycleViewAdapter.ItemClickListener,AdapterView.OnItemSelectedListener{
    MyRecycleViewAdapter adapter;
    Button camera, gallery;
    DatabaseManager db;
    ArrayList<ClothRecord> imageArry = new ArrayList<ClothRecord>();
    Spinner filter_spin;
    String filtr;
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
                startActivity(new Intent(this, Sets.class));
                finish();
                break;
            case R.id.item3:
                onStop();
                Intent i = new Intent(Wardrobe.this, Market.class);
                startActivity(i);
                finish();
                break;
            case R.id.item4:
                onStop();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }

        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wardrobe_activity);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        filter_spin = findViewById(R.id.category_filtr);

            db = new DatabaseManager(this);
            List<String> list1 = new ArrayList<String>();
            List<String> list2 = new ArrayList<String>();
            List<String> list3 = new ArrayList<String>();
            List<Bitmap> list4 = new ArrayList<Bitmap>();


            List<ClothRecord> clothlist = db.getAllContacts();
            for (ClothRecord cn : clothlist) {
                File imgFile = new File(cn.getImage());

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                list1.add(cn.getName());
                list2.add(cn.getCategory());
                list3.add(cn.getHeatlvl());
                list4.add(myBitmap);
                imageArry.add(cn);

            }

            RecyclerView recyclerView = findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new MyRecycleViewAdapter(this, list1, list2, list4);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.categoriesforspinner, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter_spin.setAdapter(adapter1);
        filter_spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        camera = (Button) findViewById(R.id.camera);
        gallery = (Button) findViewById(R.id.gallery);

        camera.setOnClickListener(view -> {
            Intent i = new Intent(Wardrobe.this, AddNewCloth_CameraFragment.class);
            startActivity(i);
            this.onPause();
        });

        gallery.setOnClickListener(view -> {
            Intent j = new Intent(Wardrobe.this, AddNewCloth_GalleryFragment.class);
            startActivity(j);
            this.onPause();
        });


    }
    public void onItemClick(View view, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Potwierdzenie");
        builder.setMessage("Czy na pewno chcesz usunąć ten strój?");
        Log.d("Pozycja", " set: " +position);
        builder.setPositiveButton("TAK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                int recordposition = position+1;
                String str;
                str = Integer.toString(recordposition);
                db.deleteClothRecord(str);

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NIE", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3) {
        int id = parent.getId();
        switch (id) {
            case R.id.category_filtr:
                filtr = parent.getItemAtPosition(position).toString();

                if(filtr.equals("Góra") || filtr.equals("Dół") || filtr.equals("Okrycie wierzchnie") || filtr.equals("Obuwie") || filtr.equals("Komplet")){
                db = new DatabaseManager(this);
                List<String> list1 = new ArrayList<String>();
                List<String> list2 = new ArrayList<String>();
                List<String> list3 = new ArrayList<String>();
                List<Bitmap> list4 = new ArrayList<Bitmap>();


                List<ClothRecord> clothlist = db.getFilteredClothes(filtr);
                for (ClothRecord cn : clothlist) {
                    File imgFile = new File(cn.getImage());

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                    list1.add(cn.getName());
                    list2.add(cn.getCategory());
                    list3.add(cn.getHeatlvl());
                    list4.add(myBitmap);
                    imageArry.add(cn);

                }

                RecyclerView recyclerView = findViewById(R.id.list);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new MyRecycleViewAdapter(this, list1, list2, list4);
                adapter.setClickListener(this);
                recyclerView.setAdapter(adapter);}
                break;
        }}

    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}