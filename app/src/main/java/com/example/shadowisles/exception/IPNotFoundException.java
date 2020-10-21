package com.example.shadowisles.exception;

public class IPNotFoundException extends Exception {

    public IPNotFoundException(){
        super("IP Address couldn't be found in this network");
    }
}
