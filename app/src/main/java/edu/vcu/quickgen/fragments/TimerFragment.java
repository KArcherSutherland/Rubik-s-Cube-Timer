package edu.vcu.quickgen.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.vcu.quickgen.R;
import edu.vcu.quickgen.activities.SettingsActivity;
import edu.vcu.quickgen.activities.TimerActivity;


public class TimerFragment extends Fragment{


    private enum State {
        START, INSPECTING, TRANSITION, PENALTY, DNF, RUNNING, STOPPED
    }

    // Generated from settings
    private final int inspectPeriod = 15;
    private long penaltyTime = 2;

    // Start in the START state
    @Bind(R.id.timerValue) TextView timerValue;

    private String cubeType;
    private ScrambleController toggler;
    private Timer timer = null;
    private Handler customHandler = new Handler();
    SharedPreferences preferences;
    final String SKIP_INSPECTION_KEY = "skipInspection";

    public void setType(String type) {
        this.cubeType = type;
    }


    public interface ScrambleController {
        void scrambleOn();
        void scrambleOff();
        void newScramble();
    }


    public TimerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        ButterKnife.bind(this,view);


        final Boolean skipInspectionPref = preferences.getBoolean(SKIP_INSPECTION_KEY, false);
        // Create a new Timer, display the timer on the screen
        timer = new Timer(0);
        timer.displayTime();

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // A state machine
                switch (timer.getState()) {
                    // If there is a screen touch during start, go to inspect state
                    case START:
                        if (event.getAction() == MotionEvent.ACTION_DOWN){
                            toggler.scrambleOff();
                            if (skipInspectionPref==false) {
                                timer.setState(State.INSPECTING);
                                timer.updateStartTime();
                                customHandler.postDelayed(timer, 0);
                            }
                            else {
                                timer.setState(State.TRANSITION);
                            }

                        }
                        break;
                    case TRANSITION:
                        if (event.getAction() == MotionEvent.ACTION_UP){
                            timer.setState(State.RUNNING);
                            timer.updateStartTime();
                            customHandler.postDelayed(timer, 0);
                        }
                        break;
                    case INSPECTING:
                    // If there is a screen release during inspect, go to running state
                    case PENALTY:
                        if (event.getAction() == MotionEvent.ACTION_UP){
                            timer.setState(State.RUNNING);
                            // Reset the timer
                            timer.updateStartTime();
                            customHandler.postDelayed(timer, 0);
                        }
                        break;
                    case DNF:
                        timer.setState(State.START);
                        break;
                    // If there is a screen touch during running state, go to stopped state
                    case RUNNING:
                        timer.setState(State.STOPPED);
                        break;
                    case STOPPED:
                        toggler.newScramble();
                        toggler.scrambleOn();
                        timer.setState(State.START);
                        break;
                }
                return true;
            }
        });

        return view;
    }

    private class Timer implements Runnable {

        long startTime;
        long timeInMilliseconds = 0L;
        long timeInSeconds = 0L;
        int secs = 0;
        int mins;
        int milliseconds;
        long addedPenalty = 0;
        long finalTime = 0;
        String minFormat;
        String secFormat = "%02d";
        String milliFormat = "%03d";
        State state;

        public Timer(long startTime){
            this.startTime = startTime;
            state = State.START;
        }

        public void displayTime(){
            if (timeInSeconds >= 60) {
                timerValue.setText("" + mins + ":"
                        + String.format(secFormat, secs) + "."
                        + String.format(milliFormat, milliseconds));
            }
            else {
                timerValue.setText(""  + String.format(secFormat, secs) + "."
                        + String.format(milliFormat, milliseconds));
            }
        }

        public void run() {
            //TODO: Use built in classes to simplify this (Date classes provide all the functionality in a single object)
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            timeInSeconds = timeInMilliseconds / 1000;
            secs = (int) timeInSeconds % 60;
            mins = (int) (timeInSeconds / 60);
            milliseconds = (int) (timeInMilliseconds % 1000);

            switch (state) {
                // Display the 00:00:00 timer
                case START:
                    timerValue.setText(""  + String.format(secFormat, 0) + "."
                            + String.format(milliFormat, 0));
                    break;

                // Count down from 15 until 0, then go to penalty state
                case INSPECTING:
                    if (inspectPeriod-secs > 0)
                        timerValue.setText("" + String.format("%d", inspectPeriod-secs));
                    else {
                        state = State.PENALTY;
                    }
                    customHandler.postDelayed(this, 0);
                    break;

                // Display +2 and go to DNF state if there is no screen release
                case PENALTY:
                    addedPenalty = penaltyTime;
                    timerValue.setText("+" + (int) penaltyTime);
                    customHandler.postDelayed(this, 0);
                    if (timeInSeconds >= inspectPeriod + penaltyTime) {
                        state = State.DNF;
                    }
                    break;

                // Display DNF
                case DNF:
                    timerValue.setText(getResources().getString(R.string.dnf));
                    break;

                // Increment the timer
                case RUNNING:
                    displayTime();
                    customHandler.postDelayed(this, 0);
                    break;

                // Stop the timer and add the penalty time if there was a penalty
                case STOPPED:
                    timeInMilliseconds += addedPenalty*1000;
                    finalTime = timeInMilliseconds;
                    timeInSeconds = timeInMilliseconds / 1000;
                    secs = (int) timeInSeconds % 60;
                    mins = (int) timeInSeconds / 60;
                    milliseconds = (int) (timeInMilliseconds % 1000);
                    displayTime();
                    //TODO: write scores to database

                    // This print statement is just to show that data has reached fragment properly
                    System.out.println("Final time: " + finalTime + "; Cube Type: " + cubeType);
                    customHandler.removeCallbacks(this);
                    //write scores to database
                    TimerActivity.dbHelper.addTime(finalTime, cubeType);

                    break;
            }

        }

        public void updateStartTime(){
            startTime = SystemClock.uptimeMillis();
        }

        public State getState(){
            return state;
        }

        public void setState(State state){
            this.state = state;
        }
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        try {
            toggler = (ScrambleController) activity;
            System.out.println("attached");
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ScrambleController");
        }

    }

    public boolean isTimerRunning(){
        return !(timer.getState().equals(State.START) || timer.getState().equals(State.STOPPED));
    }

    /**
     * Resets Timer
     * @param generateScramble determines if a new scramble should be generated along with the updateStartTime
     */
    public void resetTimer(boolean generateScramble){
        if(generateScramble) {
            toggler.newScramble();
        }
        timer.setState(State.START);
        //Reset timer
        timer.updateStartTime();
    }
}
