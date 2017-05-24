package edu.vcu.quickgen.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.vcu.quickgen.R;
import edu.vcu.quickgen.activities.NotationGuideActivity;
import edu.vcu.quickgen.utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScrambleDisplayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScrambleDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScrambleDisplayFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ScrambleDisplayFragment";
    private String mScramble;
    private boolean isEnglish = false;
    private String generatedTranslation;
    Button notationGuideButton;

    @Bind(R.id.textScramble) TextView textView;

    private OnFragmentInteractionListener mListener;
    SharedPreferences preferences;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ScrambleDisplayFragment.
     */
    public static ScrambleDisplayFragment newInstance(String scramble) {
        ScrambleDisplayFragment fragment = new ScrambleDisplayFragment();
        Bundle args = new Bundle();
        args.putString(Constants.SCRAMBLE_DISPLAY_ARGUMENT, scramble);
        Log.d(TAG, scramble);
        fragment.setArguments(args);
        return fragment;
    }

    public ScrambleDisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mScramble = getArguments().getString(Constants.SCRAMBLE_DISPLAY_ARGUMENT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scramble_display, container, false);
        ButterKnife.bind(this, view);
        final Boolean hideTranslatePref = preferences.getBoolean("hideTranslate", false);
        final Button translateButton = (Button) view.findViewById(R.id.translateButton);
        if (hideTranslatePref){
            translateButton.setVisibility(View.GONE);
        }
        notationGuideButton = (Button) view.findViewById(R.id.notationGuideButton);
        notationGuideButton.setVisibility(View.GONE);

        translateButton.setOnClickListener(this);
        notationGuideButton.setOnClickListener(this);
        if (mScramble != null){
            textView.setText(mScramble);
            Log.d(TAG, textView.getText().toString());
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void showTranslation(){
        if (generatedTranslation == null) {
            String[] symbols = mScramble.split(" ");
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < symbols.length; i++) {
                sb.append(translate(symbols[i]) + "\n");
            }

            generatedTranslation = sb.toString();
        }
        textView.setText(generatedTranslation);


    }

    private String translate(String symbol){
        StringBuilder sb = new StringBuilder();
        switch (symbol.charAt(0)) {
            case 'F':
                sb.append("Front ");
                break;
            case 'R':
                sb.append("Right ");
                break;
            case 'U':
                sb.append("Up ");
                break;
            case 'B':
                sb.append("Back ");
                break;
            case 'L':
                sb.append("Left ");
                break;
            case 'D':
                sb.append("Down ");
                break;
            default:
                break;
        }
        if (symbol.contains("'")){
            sb.append("counter-clockwise");
        }
        else if (symbol.contains("2")){
            sb.append("twice");
        }
        else {
            sb.append("clockwise");
        }
        return sb.toString();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle out){
        //TODO: Implement this, as the current way the fragment is implemented is a tad confusing
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.translateButton){
            if (notationGuideButton.getVisibility() == View.GONE) {
                notationGuideButton.setVisibility(View.VISIBLE);
            }
            else {
                notationGuideButton.setVisibility(View.GONE);
            }
            if (isEnglish){
                textView.setText(mScramble);
                isEnglish = false;
            }
            else {
                showTranslation();
                isEnglish = true;
            }
        }
        else if (v.getId() == R.id.notationGuideButton){
            Intent guide = new Intent(getActivity(), NotationGuideActivity.class);
            startActivity(guide);
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
