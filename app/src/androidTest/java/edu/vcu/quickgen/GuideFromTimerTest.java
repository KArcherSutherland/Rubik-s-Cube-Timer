package edu.vcu.quickgen;

import android.content.SharedPreferences;
import android.support.test.espresso.ViewInteraction;
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
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Chase on 10/25/2015.
 * Test for Happy Path: TextToSymbol for Feature - Cube Notation Translator
 */
public class GuideFromTimerTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    public GuideFromTimerTest()  {
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

    public void testSymbolToText(){
        SharedPreferences sharedPrefs = getDefaultSharedPreferences(this.getInstrumentation().getTargetContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        onView(withText("2x2")).perform(click());
        ViewInteraction translateButton = onView(withId(R.id.translateButton));
        translateButton.perform(click());
        onView(withId(R.id.notationGuideButton)).perform(click());
        onView(withId(R.id.textGuide)).check(ViewAssertions.matches(isDisplayed()));

    }
}
