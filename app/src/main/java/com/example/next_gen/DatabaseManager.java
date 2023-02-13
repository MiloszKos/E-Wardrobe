package com.example.next_gen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 18;

    private static final String DATABASE_NAME = " Camera_imagedb";

    private static final String TABLE_CLOTHS = "Cloths_table";
    private static final String TABLE_SETS = "Sets_table";

    private static final String KEY_ID = " id";
    private static final String KEY_NAME = " name";
    private static final String KEY_IMAGE = " image";
    private static final String KEY_COLOUR = " colour";
    private static final String KEY_HEATLVL = " heatlvl";
    private static final String KEY_CATEGORY = " category";
    private static final String KEY_IMAGE2 = " image2";
    private static final String KEY_IMAGE3 = " image3";
    private static final String KEY_IMAGE4 = " image4";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CLOTHES_TABLE = "CREATE TABLE " + TABLE_CLOTHS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_IMAGE + " TEXT,"
                + KEY_COLOUR + " TEXT,"
                + KEY_CATEGORY + " TEXT,"
                + KEY_HEATLVL +")";
        db.execSQL(CREATE_CLOTHES_TABLE);


        String CREATE_SETS_TABLE = "CREATE TABLE " + TABLE_SETS + "("
                + KEY_ID+ " INTEGER PRIMARY KEY,"
                + KEY_IMAGE + " TEXT,"
                + KEY_IMAGE2 + " TEXT,"
                + KEY_IMAGE3 + " TEXT,"
                + KEY_IMAGE4+ " TEXT" + ")";
        db.execSQL(CREATE_SETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLOTHS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETS);

        onCreate(db);
    }

    public void addClothRecord(ClothRecord clothRecord) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, clothRecord.getName());
        values.put(KEY_IMAGE, clothRecord.getImage());
        values.put(KEY_COLOUR, clothRecord.getColour());
        values.put(KEY_CATEGORY, clothRecord.getCategory());
        values.put(KEY_HEATLVL, clothRecord.getHeatlvl());

        db.insert(TABLE_CLOTHS, null, values);
        db.close();
    }

    public void addSetRecord(SetRecord setRecord) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE, setRecord.getImage());
        values.put(KEY_IMAGE2, setRecord.getImage2());
        values.put(KEY_IMAGE3, setRecord.getImage3());
        values.put(KEY_IMAGE4, setRecord.getImage4());

        db.insert(TABLE_SETS, null, values);
        db.close();
    }

    ClothRecord getClothRecord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLOTHS, new String[] { KEY_ID,
                        KEY_NAME, KEY_IMAGE, KEY_COLOUR, KEY_CATEGORY, KEY_HEATLVL,}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ClothRecord clothRecord = new ClothRecord(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

       return clothRecord;
    }


        public void deleteClothRecord(String id) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_CLOTHS, KEY_ID + " = ?",
                    new String[] { String.valueOf(id) });
            db.close();
        }

    public void deleteSetRecord(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SETS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }


    SetRecord getSetRecord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SETS, new String[] { KEY_ID,
                        KEY_IMAGE, KEY_IMAGE2, KEY_IMAGE3, KEY_IMAGE4 }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        SetRecord setRecord = new SetRecord(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return setRecord;
    }

    ClothRecord getNextClothRecord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLOTHS, new String[] { KEY_ID,
                        KEY_NAME, KEY_IMAGE, KEY_COLOUR, KEY_CATEGORY, KEY_HEATLVL}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToNext();

        ClothRecord clothRecord = new ClothRecord(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(1), cursor.getString(1), cursor.getString(1), cursor.getString(1));

        return clothRecord;
    }

    public List<ClothRecord> getSeasonalClothes(String color, String category, String season, String style) {
        List<ClothRecord> clothlist = new ArrayList<ClothRecord>();
        String[] args;
        String selectQuery = "";
Log.d("baza danych ", " styl : " +style);
        if (color == "anything")
            {
                if (style.equals("Casual")) {
                    selectQuery = "SELECT * FROM Cloths_table WHERE category = ? AND heatlvl = ? AND name IN ('Top', 'Podkoszulek', 'Bluza', 'Sweter', 'Spodnie', 'Spodenki', 'Spódnica', 'Sukienka', 'Kurtka', 'Adidasy', 'Tenisówki', 'Kozaki')";
                } else if ("Business Casual".equals(style)) {
                    selectQuery = "SELECT * FROM Cloths_table WHERE category = ? AND heatlvl = ? AND name IN ('Koszula', 'Sweter', 'Spodnie', 'Spódnica', 'Kurtka', 'Botki', 'Płaszcz', 'Kozaki', 'Półbuty')";
                } else if ("Smart Casual".equals(style)) {
                    selectQuery = "SELECT * FROM Cloths_table WHERE category = ? AND heatlvl = ? AND name IN ('Koszula', 'Sweter', 'Spodnie', 'Spódnica', 'Płaszcz', 'Botki', 'Marynarka', 'Żakiet', 'Półbuty')";
                } else if ("Semi-Formal".equals(style)) {
                    selectQuery = "SELECT * FROM Cloths_table WHERE category = ? AND heatlvl = ? AND name IN ('Koszula', 'Spodnie', 'Suknia', 'Płaszcz', 'Marynarka', 'Żakiet', 'Półbuty', 'Czółenka')";
                }
                args = new String[]{category, season};
            }

        else {

            if (style.equals("Casual")) {
                selectQuery = "SELECT * FROM Cloths_table WHERE category = ? AND heatlvl = ? AND colour = ? AND name IN ('Top', 'Podkoszulek', 'Bluza', 'Sweter', 'Spodnie', 'Spodenki', 'Spódnica', 'Sukienka', 'Kurtka', 'Adidasy', 'Tenisówki', 'Kozaki')";
            } else if ("Business Casual".equals(style)) {
                selectQuery = "SELECT * FROM Cloths_table WHERE category = ? AND heatlvl = ? AND colour = ? AND name IN ('Koszula', 'Sweter', 'Spodnie', 'Spódnica', 'Kurtka', 'Botki', 'Płaszcz', 'Kozaki', 'Półbuty')";
            } else if ("Smart Casual".equals(style)) {
                selectQuery = "SELECT * FROM Cloths_table WHERE category = ? AND heatlvl = ? AND colour = ? AND name IN ('Koszula', 'Sweter', 'Spodnie', 'Spódnica', 'Płaszcz', 'Botki', 'Marynarka', 'Żakiet', 'Półbuty')";
            } else if ("Semi-Formal".equals(style)) {
                selectQuery = "SELECT * FROM Cloths_table WHERE category = ? AND heatlvl = ? AND colour = ? AND name IN ('Koszula', 'Spodnie', 'Suknia', 'Płaszcz', 'Marynarka', 'Żakiet', 'Półbuty', 'Czółenka')";
            }
            args = new String[]{category, season, color};
            }
        Log.d("SQL ", " Query : " +selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, args);
        if (cursor.moveToFirst()) {
            do {
                ClothRecord clothRecord = new ClothRecord();
                clothRecord.setID(Integer.parseInt(cursor.getString(0)));
                clothRecord.setName(cursor.getString(1));
                clothRecord.setImage(cursor.getString(2));
                clothRecord.setColour(cursor.getString(3));
                clothRecord.setCategory(cursor.getString(4));
                clothRecord.setHeatlvl(cursor.getString(5));
                clothlist.add(clothRecord);
            } while (cursor.moveToNext());
        }

        db.close();

        return clothlist;
    }

    public List<ClothRecord> getFilteredClothes(String category) {
        List<ClothRecord> clothlist = new ArrayList<ClothRecord>();
        String[] args = new String[]{category};
        String selectQuery = "SELECT * FROM Cloths_table WHERE category = ? ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, args);

        if (cursor.moveToFirst()) {
            do {
                ClothRecord clothRecord = new ClothRecord();
                clothRecord.setID(Integer.parseInt(cursor.getString(0)));
                clothRecord.setName(cursor.getString(1));
                clothRecord.setImage(cursor.getString(2));
                clothRecord.setColour(cursor.getString(3));
                clothRecord.setCategory(cursor.getString(4));
                clothRecord.setHeatlvl(cursor.getString(5));
                clothlist.add(clothRecord);
            } while (cursor.moveToNext());
        }

        db.close();

        return clothlist;
    }


    public List<ClothRecord> getAllContacts() {
        List<ClothRecord> clothlist = new ArrayList<ClothRecord>();

        String selectQuery = "SELECT  * FROM Cloths_table";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ClothRecord clothRecord = new ClothRecord();
                clothRecord.setID(Integer.parseInt(cursor.getString(0)));
                clothRecord.setName(cursor.getString(1));
                clothRecord.setImage(cursor.getString(2));
                clothRecord.setColour(cursor.getString(3));
                clothRecord.setCategory(cursor.getString(4));
                clothRecord.setHeatlvl(cursor.getString(5));
                clothlist.add(clothRecord);
            } while (cursor.moveToNext());
        }

        db.close();

        return clothlist;
    }

    public List<SetRecord> getAllSets() {
        List<SetRecord> setlist = new ArrayList<SetRecord>();

        String selectQuery = "SELECT  * FROM Sets_table";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SetRecord setRecord = new SetRecord();
                setRecord.setID(Integer.parseInt(cursor.getString(0)));
                setRecord.setImage(cursor.getString(1));
                setRecord.setImage2(cursor.getString(2));
                setRecord.setImage3(cursor.getString(3));
                setRecord.setImage4(cursor.getString(4));

                setlist.add(setRecord);
            } while (cursor.moveToNext());
        }

        db.close();

        return setlist;
    }

    public List<ClothRecord> getAllDefinied(String colour, String heat, String category) {
        List<ClothRecord> clothlist = new ArrayList<ClothRecord>();

        String selectQuery = "SELECT  * FROM Cloths_table " +
                "WHERE KEY_COLOUR=colour " +
                "AND KEY_CATEGORY=category " +
                "AND KEY_HEAT=heat";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ClothRecord clothRecord = new ClothRecord();
                clothRecord.setID(Integer.parseInt(cursor.getString(0)));
                clothRecord.setName(cursor.getString(1));
                clothRecord.setImage(cursor.getString(2));
                clothRecord.setColour(cursor.getString(3));
                clothRecord.setCategory(cursor.getString(4));
                clothRecord.setHeatlvl(cursor.getString(5));
                clothlist.add(clothRecord);
            } while (cursor.moveToNext());
        }

        db.close();

        return clothlist;
    }

}