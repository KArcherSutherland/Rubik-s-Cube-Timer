package edu.vcu.quickgen.adapters;

/**
 * Created by Kyle Sutherland on 9/29/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.vcu.quickgen.database.DatabaseHelper;


public class ScoreSaveAdapter {
    SQLiteDatabase database;
    DatabaseHelper dbHelper;
    Context context;

    public ScoreSaveAdapter(Context c){
        context = c;
    }
    public ScoreSaveAdapter openToRead() {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getReadableDatabase();
        return this;

    }
    public ScoreSaveAdapter openToWrite(){
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void Close(){
        database.close();
    }

    public long addTime(long finalTime) {
        ContentValues values = new ContentValues();
        //add value for time
        openToWrite();
        values.put(dbHelper.COL_SCORE, 0L);
        long val = database.insert(dbHelper.TABLE_NAME, null, values);
        Close();
        return val;
    }

    public Cursor queryName() {
        String[] cols = {dbHelper.COL_ENTRYID, dbHelper.COL_TIMESTAMP,
                dbHelper.COL_SCORE, dbHelper.COL_TYPE,
                dbHelper.COL_SUBTYPE, dbHelper.COL_NAME};
        openToWrite();
        Cursor c = database.query(dbHelper.TABLE_NAME, cols, null, null, null, null, null);
        return c;
    }

    public Cursor queryAll(int nameID) {
        String[] cols = {dbHelper.COL_ENTRYID, dbHelper.COL_TIMESTAMP,
                dbHelper.COL_SCORE, dbHelper.COL_TYPE,
                dbHelper.COL_SUBTYPE, dbHelper.COL_NAME};
        openToWrite();
        Cursor c = database.query(dbHelper.TABLE_NAME, cols, dbHelper.COL_ENTRYID + "=" + nameID, null, null, null, null);
        return c;
    }



    public int deletOneRecord(int rowId) {
        openToWrite();
        int val = database.delete(dbHelper.TABLE_NAME,
                dbHelper.COL_ENTRYID + "=" + rowId, null);
        Close();
        return val;
    }
}
