package edu.vcu.quickgen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import edu.vcu.quickgen.utils.LimitedQueue;

/**
 * Created by Kyle Sutherland on 9/28/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    public static final String TABLE_NAME = "scores";
    public static final String COL_ENTRYID = "entryId";
    public static final String COL_TIMESTAMP = "timestamp";
    public static final String COL_SCORE = "cubeTime";
    public static final String COL_TYPE = "PuzzleType";
    public static final String COL_SUBTYPE = "subtype";
    public static final String COL_NAME = "name";

    //update version number with database changes
    private static final int DATABASE_VERSION = 2;
    public static final  String DATABASE_NAME = "scores";

    private static DatabaseHelper databaseHelperInstance;

    public static synchronized DatabaseHelper getDatabaseHelperInstance(Context context){
        if(databaseHelperInstance ==  null)
            databaseHelperInstance = new DatabaseHelper(context);

        return databaseHelperInstance;
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScoreSaveContract.ScoreEntry.CREATE_TABLE);
    }

    //clears the database
    public void wipe(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(ScoreSaveContract.ScoreEntry.DELETE_TABLE);
        db.execSQL(ScoreSaveContract.ScoreEntry.CREATE_TABLE);

    }

    //returns a list of the last 5 scores
    public List<Long> getLastFive(){
        List<Long> scoreList = new LimitedQueue<Long>(5);
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ENTRYID, COL_TIMESTAMP,
                        COL_SCORE, COL_TYPE, COL_SUBTYPE, COL_NAME}, "",
                new String[]{}, null, null, null, null);

        //TODO: Finish this method, remove magic numbers
        if (cursor.moveToFirst()){
            do{
                scoreList.add(Long.parseLong(cursor.getString(2)));
            }while(cursor.moveToNext());
        }
        cursor.close();

        Log.d("DatabaseHelper", scoreList.toString());
        return scoreList;
    }

    //updated getlastfive with cubetype
    public List<Long> getLastFive(String cubeType){
        List<Long> scoreList = new LimitedQueue<Long>(5);
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ENTRYID, COL_TIMESTAMP,
                        COL_SCORE, COL_TYPE, COL_SUBTYPE, COL_NAME}, "",
                new String[]{}, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                if(cursor.getString(3).equals(cubeType)){
                    scoreList.add(Long.parseLong(cursor.getString(2)));
                }
            }while(cursor.moveToNext());
        }
        cursor.close();

        Log.d("DatabaseHelper", scoreList.toString());
        return scoreList;
    }

    //finds the average of any generic list of type Long
    public long average(List<Long> list) {
        if ((list.size())!=0)  {
            long sum = 0;
            for (Long time : list) {
                sum += time;
            }
            long size = (long)list.size();
            long average =  sum/size;
            int i = 1;

            return average;
        }
        else {
            return 0L;
        }
    }

    //adding a time to the database
    public void addTime(long finalTime){
        //this is the problem line
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //add value for time
        values.put(ScoreSaveContract.ScoreEntry.COL_ENTRYID, "temp");
        values.put(ScoreSaveContract.ScoreEntry.COL_TIMESTAMP, System.currentTimeMillis());
        values.put(ScoreSaveContract.ScoreEntry.COL_SCORE, finalTime);
        values.put(ScoreSaveContract.ScoreEntry.COL_TYPE, "No Type Available");
        values.put(ScoreSaveContract.ScoreEntry.COL_SUBTYPE, "temp3");
        values.put(ScoreSaveContract.ScoreEntry.COL_NAME, "temp4");
        //insert row
        long itemRow = db.insert(ScoreSaveContract.ScoreEntry.TABLE_NAME, null, values);
        Log.d("DatabaseHelper","Inserting value: " + values+" with row value " + itemRow);
        db.close();
    }

    //adds time with type
    public void addTime(long finalTime,String cubeType){
        //this is the problem line
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //add value for time
        values.put(ScoreSaveContract.ScoreEntry.COL_ENTRYID, "temp");
        values.put(ScoreSaveContract.ScoreEntry.COL_TIMESTAMP, System.currentTimeMillis());
        values.put(ScoreSaveContract.ScoreEntry.COL_SCORE, finalTime);
        values.put(ScoreSaveContract.ScoreEntry.COL_TYPE, cubeType);
        values.put(ScoreSaveContract.ScoreEntry.COL_SUBTYPE, "temp3");
        values.put(ScoreSaveContract.ScoreEntry.COL_NAME, "temp4");
        //insert row
        long itemRow = db.insert(ScoreSaveContract.ScoreEntry.TABLE_NAME, null, values);
        Log.d("DatabaseHelper","Inserting value: " + values+" with row value " + itemRow);
        db.close();
    }

    //return list that contains all scores in database
    public List<Long> getAllScores(){
        List<Long> scoreList = new LinkedList<Long>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ENTRYID, COL_TIMESTAMP,
                        COL_SCORE, COL_TYPE, COL_SUBTYPE, COL_NAME}, "",
                new String[]{}, null, null, null, null);

        //TODO: Finish this method, remove magic numbers
        if (cursor.moveToFirst()){
            do{
                scoreList.add(Long.parseLong(cursor.getString(2)));
            }while(cursor.moveToNext());
        }
        cursor.close();

        Log.d("DatabaseHelper", scoreList.toString());
        return scoreList;
    }

    //updated getAllScores with type
    public List<Long> getAllScores(String cubeType){
        List<Long> scoreList = new LinkedList<Long>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ENTRYID, COL_TIMESTAMP,
                        COL_SCORE, COL_TYPE, COL_SUBTYPE, COL_NAME}, "",
                new String[]{}, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                if(cursor.getString(3).equals(cubeType)){
                    scoreList.add(Long.parseLong(cursor.getString(2)));
                }
            }while(cursor.moveToNext());
        }
        cursor.close();

        Log.d("DatabaseHelper",scoreList.toString());
        return scoreList;
    }

    //reverses score list order and outputs as string list
    public List<String> displayAllScores(){
        List<String> scoreList = new LinkedList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ENTRYID, COL_TIMESTAMP,
                        COL_SCORE, COL_TYPE, COL_SUBTYPE, COL_NAME}, "",
                new String[]{}, null, null, null, null);

        //TODO: Finish this method, remove magic numbers
        if (cursor.moveToFirst()){
            do{
                String score = "";
                Long time = Long.parseLong(cursor.getString(2));
                score = "Type:  " + cursor.getString(3) + ".  Time:  " + time.toString();
                scoreList.add(0,score);
            }while(cursor.moveToNext());
        }
        cursor.close();

        Log.d("DatabaseHelper",scoreList.toString());
        return scoreList;
    }

    //displayAllScores with type parameter
    public List<String> displayAllScores(String cubeType){
        List<String> scoreList = new LinkedList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ENTRYID, COL_TIMESTAMP,
                        COL_SCORE, COL_TYPE, COL_SUBTYPE, COL_NAME}, "",
                new String[]{}, null, null, null, null);

        //TODO: Finish this method, remove magic numbers
        if (cursor.moveToFirst()){
            do{
                if(cursor.getString(3).equals(cubeType)) {
                    String score = "";
                    Long time = Long.parseLong(cursor.getString(2));
                    score = "Type:  " + cursor.getString(3) + ".  Time:  " + time.toString();
                    scoreList.add(0, score);
                }
            }while(cursor.moveToNext());
        }
        cursor.close();

        Log.d("DatabaseHelper",scoreList.toString());
        return scoreList;
    }

    //return most recent solve
    public String getLastSolve(){
        List<Long> scores = this.getAllScores();
        if (scores.size()==0){
            return "No Stats Available";
        }
        if (scores.size()>=1){
            Long last = scores.get(scores.size()-1);
            return last.toString();
        }
        Long last = scores.get(scores.size()-2);
        return last.toString();
    }

    //getLastSolve with type parameter
    public String getLastSolve(String cubeType){
        List<Long> scores = this.getAllScores(cubeType);
        if (scores.size()==0){
            return "No Stats Available";
        }
        if (scores.size()>=1){
            Long last = scores.get(scores.size()-1);
            return last.toString();
        }
        Long last = scores.get(scores.size()-2);
        return last.toString();
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ScoreSaveContract.ScoreEntry.DELETE_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}