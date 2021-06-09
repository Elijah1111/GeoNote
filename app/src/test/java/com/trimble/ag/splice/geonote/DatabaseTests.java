package com.trimble.ag.splice.geonote;

import android.content.Context;
import android.database.CrossProcessCursor;
import android.graphics.drawable.Drawable;

import androidx.lifecycle.Observer;
import androidx.room.Room;

import com.trimble.ag.splice.geonote.Database.GeoNoteDao;
import com.trimble.ag.splice.geonote.Database.GeoNoteDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DatabaseTests {
    private GeoNoteDao geoNoteDao;
    private GeoNoteDatabase db;
    private Context ApplicationProvider;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, GeoNoteDatabase.class).build();
        geoNoteDao = db.GeoNoteDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeandtestname() throws Exception {
        GeoNote geoNote = new GeoNote();
        geoNote.name = "Crop";
        geoNote.icon = R.drawable.crop;
        geoNoteDao.addGeoNote(geoNote);
        assertEquals(geoNote.name,geoNoteDao.getNote(geoNote.uid).name);
    }
}
