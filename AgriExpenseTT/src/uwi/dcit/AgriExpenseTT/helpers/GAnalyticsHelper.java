package uwi.dcit.AgriExpenseTT.helpers;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * The Google Analytics Helper will provide a wrapper for ensuring that the functionality defined can be resued across the various application states of the program
 */
public class GAnalyticsHelper {

    public static final String APP_TRACKER = "AgriExpense"; //Tracker used only in this app

    private Context context;
    private Tracker tracker;

    private static GAnalyticsHelper instance = null;

    private GAnalyticsHelper(Context context){
        this.context = context;
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this.context);
        this.tracker = analytics.newTracker(APP_TRACKER);
        tracker.enableAdvertisingIdCollection(true);
    }

    // Make sense to keep a singleton class to make sure trackers are not reinitialized every time its called
    public static GAnalyticsHelper getInstance(Context context){
        if (instance == null)instance = new GAnalyticsHelper(context);
        return instance;
    }

    public Tracker getTracker(){
        return this.tracker;
    }

     public void sendUserEvent(String eventName, String action){
         tracker.send(new HitBuilders.EventBuilder()
                 .setCategory("userevent")
                 .setAction("action")
                 .setLabel(eventName)
                 .setValue(1)
                 .build());
     }

    public void sendScreenView(String screenName){
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.AppViewBuilder().build());
    }

}