package com.trimble.ag.splice.geonote;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.trimble.ag.splice.Extension;
import com.trimble.ag.splice.SpliceSystem;
import com.trimble.ag.splice.geonote.GeoNoteDrawer.GeoNoteDrawerFragment;
import com.trimble.ag.splice.ui.ActivityPage;
import com.trimble.ag.splice.ui.RunScreenDrawer;

public class GeoNoteExtension extends Extension implements ActivityPage {

    private static final String TAG = "GeoNoteExtension";


    public GeoNoteExtension(SpliceSystem system, Context context) {
        super(system, context);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        system.getUISubsystem().addActivityPage(this);
        system.getUISubsystem().addRunScreenDrawer(new GeoNoteRunScreenDrawer(this));
    }

    @Override
    public String getName() { return "GeoNote"; }

    @NonNull
    @Override
    public Fragment getFragment() {
        return new GeoNoteFragment(this);
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
}
