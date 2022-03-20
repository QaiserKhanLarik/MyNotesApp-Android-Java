package com.example.mynotes;

import com.orm.SugarRecord;

public class Notes extends SugarRecord<Notes>{

    private String note_text, note_date, note_theme;

    public Notes() {
    }

    public Notes(String note_text, String note_date, String note_theme) {
        this.note_text = note_text;
        this.note_date = note_date;
        this.note_theme = note_theme;
    }

    public String getNote_theme() {
        return note_theme;
    }

    public void setNote_theme(String note_theme) {
        this.note_theme = note_theme;
    }

    public String getNote_text() {
        return note_text;
    }

    public void setNote_text(String note_text) {
        this.note_text = note_text;
    }

    public String getNote_date() {
        return note_date;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }
}
