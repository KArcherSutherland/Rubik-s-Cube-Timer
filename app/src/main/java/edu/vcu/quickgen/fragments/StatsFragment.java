package edu.vcu.quickgen.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.vcu.quickgen.R;
import edu.vcu.quickgen.activities.StatisticsActivity;
import edu.vcu.quickgen.adapters.ScoreSaveAdapter;
import edu.vcu.quickgen.database.DatabaseHelper;

public class StatsFragment extends Fragment {
    private static final String TAG = "StatsFragment";

    public StatsFragment() {}

    private static final String TOTAL_AVERAGE = "Total average:  ";
    private static final String LAST_FIVE_AVERAGE= "Last Five average:  ";
    private static final String LAST_SOLVE = "Last Solve:  ";

    private static String cubeType;
    private static String lastTime;
    private static String fiveStats;
    private static String allStats;
    private static DatabaseHelper dbHelper;

    @Bind(R.id.statsInfo)
    TextView textView;


    public void setType(String type) {
        this.cubeType = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fiveStats = getArguments().getString(LAST_FIVE_AVERAGE);
        }

    }
    public static StatsFragment newInstance(DatabaseHelper db, String type) {
        dbHelper = db;
        cubeType = type;
        StatsFragment stats = new StatsFragment();

        List listOfFive = dbHelper.getLastFive(cubeType);
        List listOfAll = dbHelper.getAllScores(cubeType);

        lastTime = dbHelper.getLastSolve(cubeType);
        fiveStats = String.valueOf(dbHelper.average(listOfFive));
        allStats = String.valueOf(dbHelper.average(listOfAll));

        Log.d(TAG, LAST_SOLVE        +lastTime);
        Log.d(TAG, LAST_FIVE_AVERAGE + fiveStats);
        Log.d(TAG, TOTAL_AVERAGE     + allStats);

        return stats;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        ButterKnife.bind(this, view);
        if (allStats != null){
            textView.setText("Type:  " +cubeType + "  Last Solve: " + lastTime + "  Average of last 5: " + fiveStats + "  Overall Average: " + allStats);

            if (dbHelper.getAllScores(cubeType).size()==0){
                textView.setText("No Stats Available for this cube type");
            }
        }

        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            //mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
