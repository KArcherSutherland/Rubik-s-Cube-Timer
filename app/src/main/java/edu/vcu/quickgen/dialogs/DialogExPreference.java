package edu.vcu.quickgen.dialogs;

/**
 * Created by Tim Sisson on 11/21/2015.
 */
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.util.AttributeSet;

import edu.vcu.quickgen.database.DatabaseHelper;

public class DialogExPreference extends DialogPreference implements DialogInterface.OnClickListener{

    private Context context;

    public DialogExPreference(Context oContext, AttributeSet attrs)
    {
        super(oContext, attrs);
        context = oContext;
    }

    @Override
    public void onClick(DialogInterface dialog, int which){

    if(which == DialogInterface.BUTTON_POSITIVE) {
        DatabaseHelper dbHelper =  new DatabaseHelper(context);
        dbHelper.wipe();

    }else if(which == DialogInterface.BUTTON_NEGATIVE){
        // do your stuff to handle negative button
    }
}
}