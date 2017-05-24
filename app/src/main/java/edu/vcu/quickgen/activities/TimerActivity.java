package edu.vcu.quickgen.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import edu.vcu.quickgen.R;
import edu.vcu.quickgen.adapters.ScoreSaveAdapter;
import edu.vcu.quickgen.database.DatabaseHelper;
import edu.vcu.quickgen.fragments.ScrambleDisplayFragment;
import edu.vcu.quickgen.fragments.StatsFragment;
import edu.vcu.quickgen.fragments.TimerFragment;
import edu.vcu.quickgen.utils.Constants;
import edu.vcu.quickgen.components.scramblers.base.RandomGenerator;
import edu.vcu.quickgen.components.scramblers.three.ThreeRandomState;

public class TimerActivity extends AppCompatActivity
        implements ScrambleDisplayFragment.OnFragmentInteractionListener, TimerFragment.ScrambleController{
    // This may need some restructuring
    private ScrambleDisplayFragment scramFrag;
    private TimerFragment timeFrag;
    private StatsFragment statsFrag;
    //instantiate database helper
    public static ScoreSaveAdapter adapter;
    public static DatabaseHelper dbHelper;

        @Override
    // Add the Scramble fragment when the timer is not running
    public void scrambleOn() {
        // TODO: Still need to check if fragment is already added, but I can't figure that out
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, scramFrag).commit();
        getFragmentManager().beginTransaction()
                .add(R.id.stats_fragment, statsFrag).commit();
    }

    @Override
    // Remove the Scramble fragment from the activity when the timer is running
    public void scrambleOff() {
        // TODO: Still to check if fragment is already removed
        getFragmentManager().beginTransaction()
                .remove(scramFrag).commit();
        getFragmentManager().beginTransaction()
                .remove(statsFrag).commit();
    }

    /* This code generates a scramble and creates a new scramble fragment with the
     *  generated string as the argument. In the future, we will have to change which
     *  scrambler we use based on which cube the user chooses.
     */
    @Override
    public void newScramble() {
        RandomGenerator gen = new ThreeRandomState();
        String genScramble = gen.getScramble(21);
        scramFrag = ScrambleDisplayFragment.newInstance(genScramble);

        Bundle args = getIntent().getExtras();
        String cubeType = args.getString(Constants.CUBE_NAME_INTENT_EXTRA);
        statsFrag = StatsFragment.newInstance(dbHelper, cubeType);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        adapter = new ScoreSaveAdapter(this);
        dbHelper =  new DatabaseHelper((this));
        timeFrag = (TimerFragment) getFragmentManager()
                .findFragmentById(R.id.timerFragment);

        Bundle args = getIntent().getExtras();
        String cubeType = args.getString(Constants.CUBE_NAME_INTENT_EXTRA);

        timeFrag.setType(cubeType);
        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }

            newScramble();

            // Add the scramble fragment to the activity
            scrambleOn();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed(){
        //If the timer is running, stop it and re-display the scramble
        if(timeFrag != null && timeFrag.isTimerRunning()){
            timeFrag.resetTimer(false);
            scrambleOn();
        } else {
            super.onBackPressed();
        }
    }
}
