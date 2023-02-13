package com.example.next_gen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

public class AddNewCloth_GalleryFragment extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    String temp = "";
    @SuppressLint("WrongThread")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Date currentTime = Calendar.getInstance().getTime();
                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File folder = cw.getDir("imageDir", Context.MODE_PRIVATE);
                File file = new File(folder, currentTime + ".jpg");

                if (!file.exists()) {
                    Log.d("path", file.toString());
                    temp = file.toString();
                    FileOutputStream fos = null;

                    try {
                        fos = new FileOutputStream(file);
                        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(AddNewCloth_GalleryFragment.this,
                            ClothCustomization.class);
                    i.putExtra("temp", temp);
                    this.finish();
                    startActivity(i);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }}


