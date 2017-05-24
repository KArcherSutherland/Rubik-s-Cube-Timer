package edu.vcu.quickgen.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import edu.vcu.quickgen.R;
import edu.vcu.quickgen.fragments.CubeListFragment;

public class TypeGuideActivity extends AppCompatActivity implements CubeListFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_guide);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_type_guide, menu);
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
    public void onFragmentInteraction(String id) {
        String[] types = getResources().getStringArray(R.array.puzzle_types);
        String[] typesGuide = getResources().getStringArray(R.array.type_guides);
        String[] cubeTypes = getResources().getStringArray(R.array.puzzle_types);
        int typeIndex = Integer.parseInt(id);
        int index = 0;
        for (int i = 0; i < cubeTypes.length; i++){
            if (types[typeIndex].equals(cubeTypes[i])){
                index = i;
                break;
            }
        }

        AlertDialog alertDialog = new AlertDialog.Builder(TypeGuideActivity.this).create();
        alertDialog.setTitle(types[index]);
        alertDialog.setMessage(typesGuide[index]);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }
}
