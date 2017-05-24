package edu.vcu.quickgen.database;

import android.provider.BaseColumns;

/**
 * Created by Kyle Sutherland on 9/28/2015.
 */
public final class ScoreSaveContract {

    private static final String TEXT_TYPE          = " TEXT";
    private static final String COMMA_SEP          = ",";

    //empty constructor
    private ScoreSaveContract(){}

    public static abstract class ScoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "scores";
        public static final String COL_ENTRYID = "entryId";
        public static final String COL_TIMESTAMP = "timestamp";
        public static final String COL_SCORE = "cubeTime";
        public static final String COL_TYPE = "PuzzleType";
        public static final String COL_SUBTYPE = "subtype";
        public static final String COL_NAME = "name";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COL_ENTRYID + TEXT_TYPE + COMMA_SEP +
                COL_TIMESTAMP + TEXT_TYPE + COMMA_SEP +
                COL_SCORE + TEXT_TYPE + COMMA_SEP +
                COL_TYPE + TEXT_TYPE + COMMA_SEP +
                COL_SUBTYPE + TEXT_TYPE + COMMA_SEP +
                COL_NAME + TEXT_TYPE  + " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }


}
