package com.example.sumitvar.snotes.helper;

/**
 * Created by sumitvar on 28/09/15.
 */
public class Note {
    private String noteTitle;
    private String noteContent;

    public Note(String noteTitle, String noteContent) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }
}
