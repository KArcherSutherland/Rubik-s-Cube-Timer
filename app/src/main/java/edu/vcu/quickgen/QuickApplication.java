package edu.vcu.quickgen;

import android.app.Application;

import butterknife.ButterKnife;
import timber.log.Timber;

//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.Tracker;



public class QuickApplication extends Application{
  @Override
  public void onCreate() {

    super.onCreate();
    if (BuildConfig.DEBUG) {

      ButterKnife.setDebug(true);
      Timber.plant(new Timber.DebugTree());
    }
  }
}
