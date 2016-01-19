package com.example.chiragshenoy.docsapp;

/**
 * Created by Chirag Shenoy on 19-Jan-16.
 */
public class Post {

    private int success;
    private String error;
    private Message message;

    @Override
    public String toString() {
        return message.getMessage();
    }

    public Message getMessage() {
        return message;
    }
}
