package com.example.next_gen;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddNewCloth_CameraFragment extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    DatabaseManager db;
    ArrayList<ClothRecord> imageArry = new ArrayList<ClothRecord>();
    ImageView imageView;

    String temp="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseManager(this);

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //String encode = ImageBase64.encode(i);
        try {
            startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
    }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Date currentTime = Calendar.getInstance().getTime();
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File folder = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File file = new File(folder, currentTime + ".jpg");

            if (!file.exists()) {
                Log.d("path", file.toString());
                temp = file.toString();
                FileOutputStream fos = null;

                try {
                    fos = new FileOutputStream(file);
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(AddNewCloth_CameraFragment.this,
                        ClothCustomization.class);
                i.putExtra("temp", temp);
                this.finish();
                startActivity(i);

            }

        }
    }
    }

