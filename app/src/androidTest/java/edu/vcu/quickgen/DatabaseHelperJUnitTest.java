package edu.vcu.quickgen;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import java.util.List;

import edu.vcu.quickgen.activities.MainActivity;
import edu.vcu.quickgen.database.DatabaseHelper;

/**
 * Created by Chase on 11/20/2015.
 */
public class DatabaseHelperJUnitTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private static DatabaseHelper dbHelper;

    public DatabaseHelperJUnitTest() {
        super(MainActivity.class);
    }

    @Test
    public void testGetLastSolve() {
        before();
        assertEquals("10", dbHelper.getLastSolve());
    }

    @Test
    public void testGetLastFive(){
        before();
        List<Long> lastFive = dbHelper.getLastFive();
        for(int i = 0; i < lastFive.size() ; i++){
            assertEquals(new Long(10-i), lastFive.get(i));
        }
    }

    @Test
    public void testGetLastFive_CubeType(){
        before();
        List<Long> lastFive = dbHelper.getLastFive("Test");
        for(int i = 0; i < lastFive.size() ; i++){
            assertEquals(new Long(5-i), lastFive.get(i));
        }
    }

    @Test
    public void testAverage(){
        before();
        assertEquals(new Double(5.0), new Double(dbHelper.average(dbHelper.getAllScores())));
    }

    @Test
    public void testGetAllScores(){
        before();
        List<Long> allScores = dbHelper.getAllScores();
        assertEquals(10, allScores.size());
        for(int i = 0; i < allScores.size(); i++){
            assertEquals(new Long(i+1), allScores.get(i));
        }
    }

    @Test
    public void testGetAllScores_CubeType(){
        before();
        List<Long> allScores = dbHelper.getAllScores("Test");
        assertEquals(5, allScores.size());
        for(int i = 0; i < allScores.size(); i++){
            assertEquals(new Long(i+1), allScores.get(i));
        }
    }

    @Test
    public void testWipe(){
        before();
        dbHelper.wipe();
        assertEquals(0, dbHelper.getAllScores().size());
    }

    private void before(){
        dbHelper = new DatabaseHelper(this.getActivity());
        dbHelper.wipe();
        dbHelper.addTime(1, "Test");
        dbHelper.addTime(2, "Test");
        dbHelper.addTime(3, "Test");
        dbHelper.addTime(4, "Test");
        dbHelper.addTime(5, "Test");
        dbHelper.addTime(6);
        dbHelper.addTime(7);
        dbHelper.addTime(8);
        dbHelper.addTime(9);
        dbHelper.addTime(10);
    }
}
