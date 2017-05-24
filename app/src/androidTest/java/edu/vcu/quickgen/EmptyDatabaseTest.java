package edu.vcu.quickgen;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import edu.vcu.quickgen.activities.MainActivity;
import edu.vcu.quickgen.database.DatabaseHelper;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Kyle Sutherland on 10/1/2015.
 *
 * Test for Sad path: No times available for Feature - Time Statistics
 */


public class EmptyDatabaseTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public EmptyDatabaseTest() {
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

    public void testEmptyDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this.getActivity());
        dbHelper.wipe();
        onView(withText("2x2")).perform(click());
        ViewInteraction stats = onView(withId(R.id.statsInfo));
        stats.check(ViewAssertions.matches(withText("No Stats Available for this cube type")));
    }
}

