package com.trimble.ag.splice.geonote;

import android.content.Context;

import androidx.room.Room;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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
