package edu.vcu.quickgen;

/**
 * Created by Tim on 10/26/2015.
 * Test for Happy Path:  Hit the back button from the notation guide screen - Notation Guide
 */

import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import edu.vcu.quickgen.activities.MainActivity;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class NotationBackTest extends
        ActivityInstrumentationTestCase2<MainActivity>{

    public NotationBackTest() {
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

    public void testTimerStarted() throws InterruptedException {
        SharedPreferences sharedPrefs = getDefaultSharedPreferences(this.getInstrumentation().getTargetContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        onView(withText("2x2")).perform(swipeLeft());
        SystemClock.sleep(2000);
        ViewInteraction notationButton = onView(withText("Notation Guide"));
        notationButton.perform(click());
        onView(withId(R.id.guideFragment)).perform(pressBack());
        onView(withText("Notation Guide")).check(matches(isDisplayed()));

    }

}
