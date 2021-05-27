package com.trimble.ag.splice.geonote.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.trimble.ag.splice.geonote.GeoNote;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {GeoNote.class}, version = 2)
@TypeConverters(GeoNoteTypeConverter.class)
public abstract class GeoNoteDatabase extends RoomDatabase {
    abstract GeoNoteDao GeoNoteDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile GeoNoteDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    Migration migration = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE history ADD COLUMN note TEXT DEFAULT NULL");
            database.execSQL("ALTER TABLE history ADD COLUMN noteImage TEXT DEFAULT NULL");
        }
    };
    static GeoNoteDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GeoNoteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GeoNoteDatabase.class, "GeoNote_database")
                            .addMigrations(INSTANCE.migration)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}

