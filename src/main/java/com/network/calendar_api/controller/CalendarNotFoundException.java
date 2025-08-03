package com.network.calendar_api.controller;

public class CalendarNotFoundException extends RuntimeException {
    public CalendarNotFoundException(String e) {
        super(e);
    }
}
