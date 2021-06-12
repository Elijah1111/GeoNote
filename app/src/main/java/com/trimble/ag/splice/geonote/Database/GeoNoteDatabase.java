package com.trimble.ag.splice.geonote.Database;

import android.content.Context;
import android.util.Log;

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

import kotlin.jvm.Synchronized;

@Database(entities = {GeoNote.class}, version = 1, exportSchema = false)
@TypeConverters(GeoNoteTypeConverter.class)
public abstract class GeoNoteDatabase extends RoomDatabase {
    private static final String TAG = "GeoNoteDatabase";
    public abstract GeoNoteDao GeoNoteDao();
    private static final String DATABSE_NAME  = "geonoteDatabase";
    private static GeoNoteDatabase INSTANCE = null;
    @NonNull
    @Synchronized
    public static GeoNoteDatabase getInstance(Context context){
            GeoNoteDatabase instance =INSTANCE;
            if(instance==null) {
                Log.i(TAG, "Null Database instance encountered");
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        GeoNoteDatabase.class,
                        DATABSE_NAME)
                        .build();
                INSTANCE = instance;
                Log.i(TAG, "Reset Database INSTANCE");
            }
            return instance;
        }
    // marking the instance as volatile to ensure atomic access to the variable
   // private static volatile GeoNoteDatabase INSTANCE;
    //private static final int NUMBER_OF_THREADS = 4;
    /*     static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREAD
    /*    Migration migration = new Migration(1,2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE history ADD COLUMN note TEXT DEFAULT NULL");
                database.execSQL("ALTER TABLE history ADD COLUMN noteImage TEXT DEFAULT NULL");
            }
        };*/
/*    static GeoNoteDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GeoNoteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GeoNoteDatabase.class, "GeoNote_database")
                            .addCallback(geoNoteDataCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback geoNoteDataCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };*/


}

