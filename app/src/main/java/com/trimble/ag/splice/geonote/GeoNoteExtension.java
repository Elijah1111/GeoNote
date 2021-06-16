package com.trimble.ag.splice.geonote;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.trimble.ag.splice.Extension;
import com.trimble.ag.splice.SpliceSystem;
import com.trimble.ag.splice.geonote.GeoNoteDrawer.GeoNoteDrawerFragment;
import com.trimble.ag.splice.geonote.GeoNoteMap.GeoNoteFragment;
import com.trimble.ag.splice.location.Location;
import com.trimble.ag.splice.location.LocationListener;
import com.trimble.ag.splice.location.LocationSubsystem;
import com.trimble.ag.splice.ui.ActivityPage;
import com.trimble.ag.splice.ui.RunScreenDrawer;

public class GeoNoteExtension extends Extension implements ActivityPage {

    private static final String TAG = "GeoNoteExtension";

    //Location System/info
    private LocationSubsystem localSystem;
    private LocationListener localListener;
    private Location currentLocation;
    GeoNoteFragment fragment;

    public GeoNoteExtension(SpliceSystem system, Context context) {
        super(system, context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        localSystem = system.getLocationSubsystem();


        localListener = new LocationListener(){//Listen for location change
            @Override
            public void onLocationChanged(Location location) {
                if(location.getLocationSource().contains("vehicle")) {
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();
                    String eval = "javascript:updateLocation("+lon+","+lat+")";
                    //Log.d("aea3", eval);
                    try {
                        if(fragment != null) {
                            fragment.evaluateJavascript(eval);
                        }
                    }
                    catch(Exception e) {
                        Log.d("aea3", "", e);
                    }
                }
                currentLocation = location;
            }
        };
        if (localSystem != null) {
            localSystem.requestLocationUpdates(localListener, 1000);
        } else {
            Log.w(TAG, "LocationSubsystem was null");
        }

        system.getUISubsystem().addActivityPage(this);
        system.getUISubsystem().addRunScreenDrawer(new GeoNoteRunScreenDrawer(this));
    }

    @Override
    public String getName() { return "GeoNote"; }

    @NonNull
    @Override
    public Fragment getFragment() {

        if(fragment == null) {
            fragment = new GeoNoteFragment(this);
        }
        return fragment;
    }

    @NonNull
    @Override
    public Drawable getIcon() {
        return AppCompatResources.getDrawable(context, R.drawable.icon);
    }

    @NonNull
    @Override
    public String getIdentifier() {
        return this.getClass().getName();
    }

    private class GeoNoteRunScreenDrawer implements RunScreenDrawer{

        private final Extension extension;

        public GeoNoteRunScreenDrawer(Extension extension) {
            this.extension = extension;
        }

        @NonNull
        @Override
        public Fragment getFragment() {
            return new GeoNoteDrawerFragment(extension);
        }

        @NonNull
        @Override
        public Drawable getIcon() {
            return AppCompatResources.getDrawable(context, R.drawable.icon);
        }

        @NonNull
        @Override
        public String getTitle() {
            return "Create GeoNote";
        }

        @NonNull
        @Override
        public String getIdentifier() {
            return this.getClass().getName();
        }
    }

    @Override
    public void onDestroy() {//destructor
        try {//try to break off the location listener/subsystem
            if (localSystem != null) {
                localSystem.stopLocationUpdates(localListener);
            }
        } catch (Exception ex) {
            //Don't care
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
}
