package com.example.chiragshenoy.docsapp;


import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Chirag Shenoy on 19-Jan-16.
 */
public class Message{
    private String chatBotName;
    private long chatBotId;
    private String message;
    private String emotion;
    private boolean isMe;


    public long getChatBotId() {
        return chatBotId;
    }

    public String getChatBotName() {
        return chatBotName;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setChatBotId(long chatBotId) {
        this.chatBotId = chatBotId;
    }

    public void setChatBotName(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean getisMe() {
        return isMe;
    }

    public void setisMe(boolean isMe) {
        this.isMe = isMe;
    }
}
