package edu.vcu.quickgen;

import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import edu.vcu.quickgen.activities.MainActivity;
import edu.vcu.quickgen.database.DatabaseHelper;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Tim Sisson
 * Test for Happy Path: Wipe Times
 */


public class WipeDatabaseTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public WipeDatabaseTest() {
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

    public void testWipeDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this.getActivity());
        dbHelper.addTime(123234);
        dbHelper.addTime(345345);
        onView(withText("2x2")).perform(swipeLeft());
        SystemClock.sleep(2000);
        onView(withText("Settings")).perform(click());
        onView(withText("Clear Saved Times")).perform(click());
        onView(withText("Yes")).perform(click());
        onView(withId(R.id.prefsFragment)).perform(pressBack());
        onView(withText("Settings")).perform(swipeRight());
        SystemClock.sleep(3000);
        onView(withText("2x2")).perform(click());
        ViewInteraction stats = onView(withId(R.id.statsInfo));
        stats.check(ViewAssertions.matches(withText("No Stats Available for this cube type")));
    }


}
