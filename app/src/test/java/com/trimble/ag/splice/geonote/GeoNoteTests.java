package com.trimble.ag.splice.geonote;

import androidx.appcompat.content.res.AppCompatResources;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeoNoteTests {
    GeoNote note = new GeoNote("Note",R.drawable.crop,GeoNoteType.ANIMAL,5.4,6.2);
    @Test
    public void NameTests() {
        assertEquals("Note",note.name);
        note.name = "Cows";
        assertEquals("Cows",note.name);
    }
    @Test
    public void UUIDTest(){
        GeoNote[] notes = new GeoNote[100];
        for(int i = 0; i<100; i++){
            notes[i] = new GeoNote("Note",R.drawable.crop,GeoNoteType.ANIMAL,0.0,0.0);
        }
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(i==j) {
                    assertEquals(notes[i].uid, notes[j].uid);//verify its own uid is equal to its self
                    continue;
                }
                assertNotEquals(notes[i].uid,notes[j].uid);//verify UUIDs are unique
            }
        }
    }
    @Test
    public void PictureTest() {
        String path1="/app/media/pic1.jpg", path2="/app/media/pic2.jpg";
        note.addImages(path1,path2);
        String[] images = note.pictures;
        assertEquals(path1,images[0]);
        assertEquals(path2,images[1]);
    }
    @Test
    public void AudioTest() {
        String path="/app/media/audio.mp4";
        note.addAudio(path);
        assertEquals(path,note.audio);
    }
    @Test
    public void IconTest(){
        assertEquals(R.drawable.crop,note.icon);
    }
    @Test
    public void TypeTest(){
        assertEquals(GeoNoteType.ANIMAL,note.type);
        note.type = GeoNoteType.EQUIPMENT;
        assertNotEquals(GeoNoteType.ANIMAL,note.type);
        assertEquals(GeoNoteType.EQUIPMENT,note.type);
    }
    @Test
    public void PosTest(){
        double[] pos = note.pos;
        assertEquals(5.4,pos[0],0.00001);
        assertEquals(6.2,pos[1],0.00001);
    }
}
