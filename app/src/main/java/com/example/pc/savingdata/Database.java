package com.example.pc.savingdata;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by voke on 4/29/2018.
 */

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movies_db";
    public static final String TABLE_NAME = "movies";
    public static final String COL_1 = "id";
    public static final String COL_2 = "title";
    public static final String COL_3 = "year";
    public static final String COL_4 = "category";
    //public static final String COL_5 = "MOBILE";
    //public static final String COL_6 = "EMAIL";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,year TEXT,category TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData (String title,String year,String category)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,year);
        contentValues.put(COL_4,category);
        long result=db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)

            return false;
        else
            return true;
    }

    public Cursor getAllData ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME,null);
        return res;
    }

    public Cursor getOneItem (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME+" Where id="+id,null);
        return res;
    }

    public int countRecords ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME,null);
        return res.getCount();
    }

    public boolean updateData (String id, String title,String year,String category)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,year);
        contentValues.put(COL_4,category);


        db.update(TABLE_NAME,contentValues,"id = ?",new String[]{id});
        return true;
    }

    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"id = ?",new String[]{id});
    }
}