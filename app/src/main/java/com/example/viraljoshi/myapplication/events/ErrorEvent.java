package com.example.viraljoshi.myapplication.events;

public class ErrorEvent {
    private final String errormessage;

    public ErrorEvent(String errormessage) {
        this.errormessage = errormessage;
    }
    public String getErrormessage(){return errormessage;}
}
