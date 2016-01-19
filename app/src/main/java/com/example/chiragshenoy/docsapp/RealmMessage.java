package com.example.chiragshenoy.docsapp;

import io.realm.RealmObject;

/**
 * Created by Chirag Shenoy on 19-Jan-16.
 */
public class RealmMessage extends RealmObject {

    private String message;
    private boolean isMe;
    private boolean synced;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setisMe(boolean me) {
        isMe = me;
    }

    public boolean getisMe() {
        return isMe;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }

    public boolean getSynced() {
        return synced;
    }


}
