package com.trimble.ag.splice.geonote.GeoNoteDrawer;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.trimble.ag.splice.geonote.Database.GeoNoteRepository;

import java.lang.reflect.InvocationTargetException;

public class GeoNoteDrawerViewModelFactory implements ViewModelProvider.Factory {
    private Context mContext;
    public GeoNoteDrawerViewModelFactory(Context context){
        mContext=context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        T model = null;
        try {
           model = modelClass.getConstructor(GeoNoteRepository.class).newInstance(GeoNoteRepository.getInstance(mContext));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assert model != null;
        return model;
    }
}
