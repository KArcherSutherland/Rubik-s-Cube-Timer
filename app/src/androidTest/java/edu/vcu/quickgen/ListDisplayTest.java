package edu.vcu.quickgen;

import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import edu.vcu.quickgen.activities.MainActivity;
import edu.vcu.quickgen.database.DatabaseHelper;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Kyle Sutherland on 10/1/2015.
 *
 * Test for Sad path: No times available for Feature - Time Statistics
 */


public class ListDisplayTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public ListDisplayTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        getActivity();
        super.setUp();
    }

    public void tearDown() throws Exception {
        Log.d("TEARDOWN", "TEARDOWN");
        super.tearDown();
    }

     
    public void testListDisplay() {
        
        SharedPreferences sharedPrefs = getDefaultSharedPreferences(this.getInstrumentation().getTargetContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        dbHelper.wipe();
        dbHelper.addTime(543, "2x2");
        dbHelper.addTime(200,"2x2");
        dbHelper.addTime(60,"2x2");
        dbHelper.addTime(1000,"2x2");

        onView(withText("2x2")).perform(swipeLeft());
        SystemClock.sleep(2000);
        onView(withText("Statistics")).perform(click());
        SystemClock.sleep(2000);
        ViewInteraction clicker = onView(withText("2x2"));
        clicker.perform(click());
      //  ViewInteraction stats = onView(withId(R.id.statsListFragment));
    //    stats.check(ViewAssertions.matches(isDisplayed()));
    }
}

