package com.example.shadowisles.exception;

public class LobbyNotCreatableException extends Exception{

    public LobbyNotCreatableException(){
        super("This Lobby cannot be created");
    }
}
