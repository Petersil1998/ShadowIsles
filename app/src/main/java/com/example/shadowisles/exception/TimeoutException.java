package com.example.shadowisles.exception;

public class TimeoutException extends Exception {

    public TimeoutException(){
        super("Timeout when trying to connect to the Client");
    }
}
