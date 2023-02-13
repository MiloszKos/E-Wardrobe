package com.example.next_gen;

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

public class Sets extends AppCompatActivity implements SetsRecycleViewAdapter.ItemClickListener, AdapterView.OnItemSelectedListener{

    Button add, generate;
    Spinner style_spin;
    String style;
    int temp;
    SetsRecycleViewAdapter adapter;
    DatabaseManager db;
    ArrayList<SetRecord> imageArry = new ArrayList<SetRecord>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

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
                Intent i = new Intent(this, Market.class);
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
        setContentView(R.layout.sets_activity);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        add = (Button) findViewById(R.id.add);
        generate = (Button) findViewById(R.id.generate);
        db = new DatabaseManager(this);
        int temperature = 0;
        style_spin = findViewById(R.id.style_s);

        Intent j = getIntent();
        temp = j.getIntExtra("temp",20);

        List<Bitmap> list1 = new ArrayList<Bitmap>();
        List<Bitmap> list2 = new ArrayList<Bitmap>();
        List<Bitmap> list3 = new ArrayList<Bitmap>();
        List<Bitmap> list4 = new ArrayList<Bitmap>();

        List<SetRecord> setlist = db.getAllSets();
        for (SetRecord cn : setlist) {
            String log = "ID:" + cn.getID() + " Image: " + cn.getImage()
                    + " ,Image2: " + cn.getImage2() + " Image3: " + cn.getImage3()
                    + " Image4: " + cn.getImage4();

            Log.d("Sets list", log);

            File imgFile = new  File(cn.getImage());
            File imgFile2 = new  File(cn.getImage2());
            File imgFile3 = new  File(cn.getImage3());
            File imgFile4 = new  File(cn.getImage4());

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Bitmap myBitmap2 = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());
            Bitmap myBitmap3 = BitmapFactory.decodeFile(imgFile3.getAbsolutePath());
            Bitmap myBitmap4 = BitmapFactory.decodeFile(imgFile4.getAbsolutePath());

            list1.add(myBitmap);
            list2.add(myBitmap2);
            list3.add(myBitmap3);
            list4.add(myBitmap4);
            // Writing Contacts to log
            Log.d("Result: ", log);
            // add contacts data in arrayList
            imageArry.add(cn);

        }


        RecyclerView recyclerView = findViewById(R.id.sets);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new SetsRecycleViewAdapter(this, list1, list2, list3, list4);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.style, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        style_spin.setAdapter(adapter1);
        style_spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        add.setOnClickListener(view -> {
            Intent i = new Intent(this, ClothSelection.class);
            startActivity(i);
            this.onStop();
        });

        final int finaltemperature = temperature;

        generate.setOnClickListener(view -> {
            Intent i = new Intent(this, RandomSetScreen.class);
            i.putExtra("style", style);
            i.putExtra("temp",temp);
            startActivity(i);
            this.onPause();
        });
    }


    @Override
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
                db.deleteSetRecord(str);

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
            case R.id.style_s:

                style = parent.getItemAtPosition(position).toString();
                break;
        }}

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
