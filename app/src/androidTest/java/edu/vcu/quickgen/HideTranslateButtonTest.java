package edu.vcu.quickgen;


import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.test.espresso.assertion.ViewAssertions;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;

import edu.vcu.quickgen.activities.MainActivity;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by Tim Sisson
 * Test for Happy Path: Hide Translate Button
 */


public class HideTranslateButtonTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public HideTranslateButtonTest() {
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

    public void testNoWipeDatabase() {
        SharedPreferences sharedPrefs = getDefaultSharedPreferences(this.getInstrumentation().getTargetContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        onView(withText("2x2")).perform(swipeLeft());
        SystemClock.sleep(2000);
        onView(withText("Settings")).perform(click());
        onView(withText("Hide Translate Button")).perform(click());
        onView(withId(R.id.prefsFragment)).perform(pressBack());
        onView(withText("Settings")).perform(swipeRight());
        SystemClock.sleep(2000);
        onView(withText("2x2")).perform(click());
        onView(withText("Translate")).check(ViewAssertions.matches(not(isDisplayed())));
    }


}