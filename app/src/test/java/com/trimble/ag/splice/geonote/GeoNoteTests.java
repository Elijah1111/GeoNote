package com.trimble.ag.splice.geonote;

import androidx.appcompat.content.res.AppCompatResources;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeoNoteTests {
    GeoNote note = new GeoNote("Note",R.drawable.crop,GeoNoteType.ANIMAL,5.4,6.2);
    @Test
    public void NameTests() {
        assertEquals("Note",note.getName());
        note.setName("Cows");
        assertEquals("Cows",note.getName());
    }
    @Test
    public void UUIDTest(){
        GeoNote[] notes = new GeoNote[100];
        for(int i = 0; i<100; i++){
            notes[i] = new GeoNote("Note",R.drawable.crop,GeoNoteType.ANIMAL,0.0,0.0);
        }
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(i==j)
                    continue;
                assertNotEquals(notes[i].getUid(),notes[j].getUid());//verify UUIDs are unique
            }
        }
    }
    @Test
    public void PictureTest() {
        String path1="/app/media/pic1.jpg", path2="/app/media/pic2.jpg";
        note.addImages(path1,path2);
        String[] images = note.getPictures();
        assertEquals(path1,images[0]);
        assertEquals(path2,images[1]);
    }
    @Test
    public void AudioTest() {
        String path="/app/media/audio.mp4";
        note.addAudio(path);
        assertEquals(path,note.getAudio());
    }
    @Test
    public void IconTest(){
        assertEquals(R.drawable.crop,note.getIcon());
    }
    @Test
    public void TypeTest(){
        assertEquals(GeoNoteType.ANIMAL,note.getType());
        note.setType(GeoNoteType.EQUIPMENT);
        assertNotEquals(GeoNoteType.ANIMAL,note.getType());
        assertEquals(GeoNoteType.EQUIPMENT,note.getType());
    }
    @Test
    public void PosTest(){
        double[] pos = note.getPos();
        assertEquals(5.4,pos[0],0.00001);
        assertEquals(6.2,pos[1],0.00001);
    }
}
