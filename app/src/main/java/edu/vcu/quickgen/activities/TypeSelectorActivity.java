package edu.vcu.quickgen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.vcu.quickgen.R;
import edu.vcu.quickgen.fragments.CubeListFragment;
import edu.vcu.quickgen.utils.Constants;

public class TypeSelectorActivity extends AppCompatActivity
        implements CubeListFragment.OnFragmentInteractionListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_selector);
    }


    @Override
    public void onFragmentInteraction(String id) {
        String cubeType = "placeholder cube type";
        Intent cubeStart = new Intent(this, StatisticsActivity.class);
        switch (id){
            case "0":
                cubeType = "2x2";
                cubeStart.putExtra(Constants.CUBE_NAME_INTENT_EXTRA, cubeType);
                break;
            case "1":
                cubeType = "3x3";
                cubeStart.putExtra(Constants.CUBE_NAME_INTENT_EXTRA, cubeType);
                break;
            case "2":
                cubeType = "4x4";
                cubeStart.putExtra(Constants.CUBE_NAME_INTENT_EXTRA, cubeType);
                break;
            case "3":
                cubeType = "5x5";
                cubeStart.putExtra(Constants.CUBE_NAME_INTENT_EXTRA, cubeType);
                break;
            case "4":
                cubeType = "6x6";
                cubeStart.putExtra(Constants.CUBE_NAME_INTENT_EXTRA, cubeType);
                break;
            case "5":
                cubeType = "7x7";
                cubeStart.putExtra(Constants.CUBE_NAME_INTENT_EXTRA, cubeType);
                break;
            case "6":
                cubeType = "Pyraminx";
                cubeStart.putExtra(Constants.CUBE_NAME_INTENT_EXTRA, cubeType);
                break;
            case "7":
                cubeType = "Square-1";
                cubeStart.putExtra(Constants.CUBE_NAME_INTENT_EXTRA, cubeType);
                break;
            case "8":
                cubeType = "MegaMinx";
                cubeStart.putExtra(Constants.CUBE_NAME_INTENT_EXTRA, cubeType);
                break;
            case "9":
                cubeType = "Skewb";
                cubeStart.putExtra(Constants.CUBE_NAME_INTENT_EXTRA, cubeType);
                break;
        }
        cubeStart.putExtra(Constants.CUBE_NAME_INTENT_EXTRA, cubeType);
        startActivity(cubeStart);
    }
}
