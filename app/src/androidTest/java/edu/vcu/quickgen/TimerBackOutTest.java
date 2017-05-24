package edu.vcu.quickgen;

import android.content.SharedPreferences;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import edu.vcu.quickgen.activities.MainActivity;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Chase on 10/1/2015.
 * Test for Happy Path: Proper Inspection and Timing for Feature - Timer Function
 */

public class TimerBackOutTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    public TimerBackOutTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        getActivity();
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        Log.d("TEARDOWN", "TEARDOWN");
        super.tearDown();
    }

    public void testTimerBackOut(){
        SharedPreferences sharedPrefs = getDefaultSharedPreferences(this.getInstrumentation().getTargetContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        onView(withText("2x2")).perform(click());
        ViewInteraction scramble = onView(withId(R.id.textScramble));
        onView(withId(R.id.timerFragment)).perform(click(ViewActions.pressBack()));
        scramble.check(ViewAssertions.matches(isDisplayed()));
    }
}
