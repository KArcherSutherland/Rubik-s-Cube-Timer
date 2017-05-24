package edu.vcu.quickgen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.vcu.quickgen.R;
import edu.vcu.quickgen.adapters.MainTabAdapter;
import edu.vcu.quickgen.fragments.CubeListFragment;
import edu.vcu.quickgen.fragments.ToolsListFragment;
import edu.vcu.quickgen.utils.Constants;

public class MainActivity extends AppCompatActivity implements CubeListFragment.OnFragmentInteractionListener, ToolsListFragment.OnToolsFragmentInteractionListener {

    @Bind(R.id.pager) ViewPager mViewPager;

    private MainTabAdapter mTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTabAdapter = new MainTabAdapter(getFragmentManager());
        mViewPager.setAdapter(mTabAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {
        String cubeType = "placeholder cube type";
        Intent cubeStart = new Intent(this, TimerActivity.class);
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

    @Override
    public void onToolsFragmentInteraction(String id) {
        switch (id){
            case "0":
                Intent statsStart = new Intent(this, TypeSelectorActivity.class);
                startActivity(statsStart);
                break;
            case "1":
                Intent guideStart = new Intent(this, NotationGuideActivity.class);
                startActivity(guideStart);
                break;
            case "2":
                Intent typeGuideListStart = new Intent(this, TypeGuideActivity.class);
                startActivity(typeGuideListStart);
                break;
            case "3":
                Intent settingsStart = new Intent(this, SettingsActivity.class);
                startActivity(settingsStart);
                break;
        }
    }
}
