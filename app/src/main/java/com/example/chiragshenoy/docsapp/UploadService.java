package com.example.chiragshenoy.docsapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Chirag Shenoy on 19-Jan-16.
 */
public class UploadService extends Service {

    static int syncedCount;

    int notif_id = 4;
    RealmResults<RealmMessage> journeyRealmResults;
    Realm realm;


    public UploadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (Helper.isOnlineOnWifi(getApplicationContext())) {

            realm = Realm.getInstance(this);


            journeyRealmResults = realm.where(RealmMessage.class)
                    .equalTo("isSynced", false)
                    .findAll();

            //Adding address and make string times to date times.
            realm.beginTransaction();


            //For each journey, get all road irregularties bounded by start and end time
        }

        return START_STICKY;

    }
}
