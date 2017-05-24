package edu.vcu.quickgen.activities;

//import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import edu.vcu.quickgen.R;
import edu.vcu.quickgen.database.DatabaseHelper;
import edu.vcu.quickgen.fragments.StatsFragment;
import edu.vcu.quickgen.fragments.StatsListFragment;
import edu.vcu.quickgen.utils.Constants;

public class StatisticsActivity extends Activity {

    DatabaseHelper dbHelper;

    String cubeType = "arbitrary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Bundle args = getIntent().getExtras();
        cubeType = args.getString(Constants.CUBE_NAME_INTENT_EXTRA);
        dbHelper =  new DatabaseHelper((this));
        StatsFragment statsFrag = StatsFragment.newInstance(dbHelper, cubeType);
        StatsListFragment statsListFragment = StatsListFragment.newInstance(cubeType);
        getFragmentManager().beginTransaction().add(R.id.stats_fragment, statsFrag).commit();
        getFragmentManager().beginTransaction().add(R.id.statsListFragment, statsListFragment).commit();

    }

}
