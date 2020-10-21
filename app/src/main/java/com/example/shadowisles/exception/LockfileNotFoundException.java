package com.example.shadowisles.exception;

import java.io.FileNotFoundException;

public class LockfileNotFoundException extends FileNotFoundException {

    public LockfileNotFoundException(){
        super("League Client Lockfile couldn't be found");
    }
}
