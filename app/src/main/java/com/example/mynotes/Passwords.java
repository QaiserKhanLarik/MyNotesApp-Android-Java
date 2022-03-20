package com.example.mynotes;

import com.orm.SugarRecord;

public class Passwords extends SugarRecord<Passwords> {

    private String password;
    private long note_id;

    public Passwords(String password, long note_id) {
        this.password = password;
        this.note_id = note_id;
    }

    public long getNote_id() {
        return note_id;
    }

    public void setNote_id(long note_id) {
        this.note_id = note_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
